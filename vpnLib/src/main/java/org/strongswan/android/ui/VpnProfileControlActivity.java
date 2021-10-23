
package org.strongswan.android.ui;

import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.VpnService;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.one.hotspot.vpn.free.master.R;
import com.one.hotspot.vpn.free.master.manager.VpnManager;
import org.strongswan.android.data.VpnProfile;
import org.strongswan.android.data.VpnProfileDataSource;
import org.strongswan.android.data.VpnType.VpnTypeFeature;
import org.strongswan.android.logic.VpnStateService;
import org.strongswan.android.logic.VpnStateService.State;

public class VpnProfileControlActivity extends AppCompatActivity {
    public static final String START_PROFILE = "org.strongswan.android.action.START_PROFILE";
    public static final String DISCONNECT = "org.strongswan.android.action.DISCONNECT";
    public static final String EXTRA_VPN_PROFILE_ID = "org.strongswan.android.VPN_PROFILE_ID";

    private static final int PREPARE_VPN_SERVICE = 0;
    private static final String WAITING_FOR_RESULT = "WAITING_FOR_RESULT";
    public  static final String PROFILE_NAME = "PROFILE_NAME";
    public static final String PROFILE_REQUIRES_PASSWORD = "REQUIRES_PASSWORD";
    public static final String COUNTRY = "COUNTRY";
    private static final String PROFILE_DISCONNECT = "DISCONNECT";
    private static VpnManager.OnVpnStateChange onVpnStateChange;
    private Bundle mProfileInfo;
    private boolean mWaitingForResult;
    private VpnStateService mService;
    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = ((VpnStateService.LocalBinder) service).getService();
            mService.setOnVpnChangeListener(onVpnStateChange);
            handleIntent();
        }
    };

    public static void registerOnVpnStateChange(VpnManager.OnVpnStateChange onVpnStateChange) {
        VpnProfileControlActivity.onVpnStateChange = onVpnStateChange;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mWaitingForResult = savedInstanceState.getBoolean(WAITING_FOR_RESULT, false);
        }

        this.bindService(new Intent(this, VpnStateService.class),
                mServiceConnection, Service.BIND_AUTO_CREATE);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(WAITING_FOR_RESULT, mWaitingForResult);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mService != null) {
            this.unbindService(mServiceConnection);
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

        if (mService != null) {
            handleIntent();
        }
    }


    protected void prepareVpnService(Bundle profileInfo) {
        Intent intent;

        if (mWaitingForResult) {
            mProfileInfo = profileInfo;
            return;
        }

        try {
            intent = VpnService.prepare(this);
        } catch (IllegalStateException ex) {
            return;
        } catch (NullPointerException ex) {
           return;
        }
       mProfileInfo = profileInfo;
        if (intent != null) {
            try {
                mWaitingForResult = true;
                startActivityForResult(intent, PREPARE_VPN_SERVICE);
            } catch (ActivityNotFoundException ex) {
                mWaitingForResult = false;
            }
        } else {
            onActivityResult(PREPARE_VPN_SERVICE, RESULT_OK, null);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PREPARE_VPN_SERVICE:
                mWaitingForResult = false;
                if (resultCode == RESULT_OK && mProfileInfo != null) {
                    if (mService != null) {
                        mService.connect(mProfileInfo, true);
                    }
                    finish();
                    overridePendingTransition(0, 0);
                } else {
                    if (getSupportFragmentManager().isStateSaved()) {
                        return;
                    }
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

      private boolean isConnected() {
        if (mService == null) {
            return false;
        }
        if (mService.getErrorState() != VpnStateService.ErrorState.NO_ERROR) {
            return false;
        }
        return (mService.getState() == State.CONNECTED || mService.getState() == State.CONNECTING);
    }

   public void startVpnProfile(VpnProfile profile) {
        Bundle profileInfo = new Bundle();
        profileInfo.putString(VpnProfileDataSource.KEY_UUID, profile.getUUID().toString());
        profileInfo.putString(VpnProfileDataSource.KEY_USERNAME, profile.getUsername());
        profileInfo.putString(VpnProfileDataSource.KEY_PASSWORD, profile.getPassword());
        profileInfo.putBoolean(PROFILE_REQUIRES_PASSWORD, profile.getVpnType().has(VpnTypeFeature.USER_PASS));
        profileInfo.putString(PROFILE_NAME, profile.getName());


        if (isConnected()) {
            onVpnStateChange.onStateChange(VpnManager.VpnState.CONNECTED);
            return;
        }
        startVpnProfile(profileInfo);
    }

    private void startVpnProfile(Bundle profileInfo) {
        prepareVpnService(profileInfo);
    }


    private void startVpnProfile(Intent intent) {
        VpnProfile profile = null;

        VpnProfileDataSource dataSource = new VpnProfileDataSource(this);
        dataSource.open();
        String profileUUID = intent.getStringExtra(EXTRA_VPN_PROFILE_ID);
        if (profileUUID != null) {
            profile = dataSource.getVpnProfile(profileUUID);
        } else {
            long profileId = intent.getLongExtra(EXTRA_VPN_PROFILE_ID, 0);
            if (profileId > 0) {
                profile = dataSource.getVpnProfile(profileId);
            }
        }
        dataSource.close();

        if (profile != null) {
            startVpnProfile(profile);
        } else {
            Toast.makeText(this, R.string.profile_not_found, Toast.LENGTH_LONG).show();
            finish();
            overridePendingTransition(0, 0);
        }
    }


    private void disconnect(Intent intent) {
        VpnProfile profile = null;


        String profileUUID = intent.getStringExtra(EXTRA_VPN_PROFILE_ID);
        if (profileUUID != null) {
            VpnProfileDataSource dataSource = new VpnProfileDataSource(this);
            dataSource.open();
            profile = dataSource.getVpnProfile(profileUUID);
            dataSource.close();
        }

        if (mService != null) {
            if (mService.getState() == State.CONNECTED ||
                    mService.getState() == State.CONNECTING) {
                mService.disconnect();
                finish();
                overridePendingTransition(0, 0);

            } else {
                finish();
            }
        }
    }


    private void handleIntent() {
        Intent intent = getIntent();
        if (START_PROFILE.equals(intent.getAction())) {
            startVpnProfile(intent);
        } else if (DISCONNECT.equals(intent.getAction())) {
            disconnect(intent);
        }
    }

    public void removeFragmentByTag(String tag) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment login = fm.findFragmentByTag(tag);
        if (login != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(login);
            ft.commit();
        }
    }

}
