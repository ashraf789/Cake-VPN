package com.lazycoder.cakevpn.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.VpnService;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.lazycoder.cakevpn.CheckInternetConnection;
import com.lazycoder.cakevpn.R;
import com.lazycoder.cakevpn.databinding.FragmentMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.blinkt.openvpn.OpenVpnApi;
import de.blinkt.openvpn.core.OpenVPNService;
import de.blinkt.openvpn.core.OpenVPNThread;
import de.blinkt.openvpn.core.VpnStatus;

import static android.app.Activity.RESULT_OK;

public class MainFragment extends Fragment implements View.OnClickListener {

    private String ovpnServer = "japan.ovpn";
    private String ovpnUserName = "";
    private String ovpnUserPassword = "";
    private CheckInternetConnection connection;

    private OpenVPNThread vpnThread = new OpenVPNThread();
    private OpenVPNService vpnService = new OpenVPNService();
    boolean vpnStart = false;

    private FragmentMainBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        View view = binding.getRoot();
        initializeAll();

        return view;
    }

    private void initializeAll() {
        connection = new CheckInternetConnection();

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, new IntentFilter("connectionState"));

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.vpnBtn.setOnClickListener(this);
        isServiceRunning();//checking is vpn already running or not
        VpnStatus.initLogCache(getActivity().getCacheDir());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vpnBtn:
                prepareVpn();
                return;
        }
    }

    //prepare for vpn connect with required permission
    private void prepareVpn() {
        if (!vpnStart) {
            if (getInternetStatus()) {
                //checking permission for network monitor
                Intent intent = VpnService.prepare(getContext());
                if (intent != null) {
                    startActivityForResult(intent, 1);
                } else startVpn();//have already permission
                status("connecting");

            } else {
                showToast("you have no internet connection !!");
            }

        } else if (stopVpn()) {

            showToast("Disconnect Successfully");
        }
    }

    /**
     * Stop vpn
     * @return boolean
     */
    public boolean stopVpn() {
        try {
            vpnThread.stop();
            status("connect");
            vpnStart = false;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    //taking permission for network access
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            startVpn();

        } else {
            showToast("Permission Deny !! ");
        }
    }

    // internet connection status.
    public boolean getInternetStatus() {
        return connection.netCheck(getContext());
    }


    // checking is service is connected
    public void isServiceRunning() {
        setStatus(vpnService.getStatus());
    }

    // if all reburied permission is granted then start vpn
    private void startVpn() {
        try {

            InputStream conf = getActivity().getAssets().open(ovpnServer);// your own file in /assets/client.ovpn
            InputStreamReader isr = new InputStreamReader(conf);
            BufferedReader br = new BufferedReader(isr);
            String config = "";
            String line;
            while (true) {
                line = br.readLine();
                if (line == null) break;
                config += line + "\n";
            }
            br.readLine();
            OpenVpnApi.startVpn(getContext(), config, ovpnUserName, ovpnUserPassword);//"shieldedvpn","ZTHqKDMbzmZgl5PJ"
            binding.logTv.setText("Connecting...");

        } catch (IOException | RemoteException e) {
            e.printStackTrace();
        }
    }


    //status change with corresponding vpn connection status
    public void setStatus(String connectionState) {
        switch (connectionState) {
            case "DISCONNECTED":
                status("connect");
                vpnStart = false;
                vpnService.setDefaultStatus();
                binding.logTv.setText("");

                break;
            case "CONNECTED":
                vpnStart = true;// it will use after restart this activity
                status("connected");
                binding.logTv.setText("");
                break;
            case "WAIT":
                binding.logTv.setText("waiting for server connection!!");
                break;
            case "AUTH":
                binding.logTv.setText("server authenticating!!");
                break;
            case "RECONNECTING":
                status("connecting");
                binding.logTv.setText("Reconnecting...");
                break;
            case "NONETWORK":
                binding.logTv.setText("No network connection");
                break;
        }

    }

    //it will change button background color and text
    public void status(String status) {

        if (status.equals("connect")) {

            binding.vpnBtn.setText(getString(R.string.connect));
        } else if (status.equals("connecting")) {
            binding.vpnBtn.setText(getString(R.string.connecting));
        } else if (status.equals("connected")) {

            binding.vpnBtn.setText(getString(R.string.disconnect));

        } else if (status.equals("tryDifferentServer")) {

            binding.vpnBtn.setBackgroundResource(R.drawable.button_connected);
            binding.vpnBtn.setText("Try Different\nServer");
        } else if (status.equals("loading")) {
            binding.vpnBtn.setBackgroundResource(R.drawable.button);
            binding.vpnBtn.setText("Loading Server..");
        } else if (status.equals("invalidDevice")) {
            binding.vpnBtn.setBackgroundResource(R.drawable.button_connected);
            binding.vpnBtn.setText("Invalid Device");
        } else if (status.equals("authenticationCheck")) {
            binding.vpnBtn.setBackgroundResource(R.drawable.button_connecting);
            binding.vpnBtn.setText("Authentication \n Checking...");
        }

    }

    // receiving broadcast message
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                setStatus(intent.getStringExtra("state"));
            } catch (Exception e) {
                //when sendMessage(String state) call this method will be called
            }

            try{

                String duration = intent.getStringExtra("duration");
                String lastPacketReceive = intent.getStringExtra("lastPacketReceive");
                String byteIn = intent.getStringExtra("byteIn");
                String byteOut = intent.getStringExtra("byteOut");

                if (duration.equals(null)) duration = "00:00:00";
                if (lastPacketReceive.equals(null)) lastPacketReceive = "0";
                if (byteIn.equals(null)) byteIn = " ";
                if (byteOut.equals(null)) byteOut = " ";
                updateConnectionStatus(duration,lastPacketReceive,byteIn,byteOut);
            }catch (Exception e){
                //when sendMessage(String val,String val,..) called then it will be called
            }

        }
    };

    public void updateConnectionStatus(String duration,String lastPacketReceive,String byteIn,String byteOut){
        binding.durationTv.setText("Duration: "+duration);
        binding.lastPacketReceiveTv.setText("Packet Received: "+lastPacketReceive+" second ago");
        binding.byteInTv.setText("Bytes In: "+byteIn);
        binding.byteOutTv.setText("Bytes Out: "+byteOut);
    }

    // show toast message
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
