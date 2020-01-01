package com.lazycoder.cakevpn;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    private FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton menuRight = findViewById(R.id.navbar_right);


        Toolbar toolbar =  findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        menuRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawer(GravityCompat.END);
                } else {
                    drawer.openDrawer(GravityCompat.END);
                }
            }
        });

        initializeAll();//initialize all variable
        transaction.add(R.id.container,fragment);
        transaction.commit();
    }

    /**
     * Initialize all object, listener etc
     * */
    private void initializeAll() {
        fragment = new MainFragment();
    }



}
