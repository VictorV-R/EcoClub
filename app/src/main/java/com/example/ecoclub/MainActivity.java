package com.example.ecoclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.ecoclub.comunity.ComunityBroadcastReceiver;
import com.example.ecoclub.fragments.CollaborateFragment;
import com.example.ecoclub.fragments.ComunityDescriptionFragment;
import com.example.ecoclub.fragments.ComunityFragment;
import com.example.ecoclub.fragments.HomeFragment;
import com.example.ecoclub.fragments.MapsFragment;
import com.example.ecoclub.fragments.MyComunityFragment;
import com.example.ecoclub.fragments.ProfileFragment;
import com.example.ecoclub.interfaces.MainActivityCallbacks;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements MainActivityCallbacks {

    BottomNavigationView bottomNavigationView;

    //Bottom Navigation-Fragments
    HomeFragment homeFragment = new HomeFragment();
    MapsFragment mapsFragment = new MapsFragment();
    ComunityFragment comunityFragment = new ComunityFragment();
    CollaborateFragment collaborateFragment = new CollaborateFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    //Otros-Fragments
    MyComunityFragment myComunityFragment = new MyComunityFragment();
    ComunityDescriptionFragment comunityDescriptionFragment = new ComunityDescriptionFragment();

    //broadcast Receiver
    protected  static ComunityBroadcastReceiver myBroadcastReceiver =
            new ComunityBroadcastReceiver();
    protected IntentFilter intentFilterDescriptionComunity =
            new IntentFilter("SOME_ACTION_DESCRIPTION_COMUNITY");

    String api="18f1b34a081148119e242db1fb37a8e9";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.maps:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, mapsFragment).commit();
                        return true;
                    case R.id.comunity:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, comunityFragment).commit();
                        return true;
                    case R.id.collaborate:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, collaborateFragment).commit();
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                        return true;
                }
                return false;
            }
        });

        //BROADCAST RECEIVER
        broadcastReceiverComunity();
    }

    //broadcast Receiver metodos*****************************************************************
    //metodo general donde se ve que fragment se llama
    private void broadcastReceiverComunity() {
        String mensajeIdComunity = "";

        //Devuelve verdadero si un valor extra está asociado con el nombre dado
        //Descripcion Comunity-Fragment
        if (getIntent().hasExtra("BroadcastReceiverDescriptionComunity")){
            mensajeIdComunity = getIntent().getStringExtra(
                    "BroadcastReceiverDescriptionComunity");
            //metodo del activity
            broadcastComunityDescriptionFragment(comunityDescriptionFragment, mensajeIdComunity);
        }
    }

    //habilitamos y enviamos informacio al fragment ComunityDescription
    private void broadcastComunityDescriptionFragment(
            ComunityDescriptionFragment comunityDescriptionFragment, String mensajeIdComunity) {
        //enviamos id al fragment.............
        Bundle enviarMensaje = new Bundle();
        enviarMensaje.putString("id", mensajeIdComunity);
        comunityDescriptionFragment.setArguments(enviarMensaje);
        //...................................
        getSupportFragmentManager().beginTransaction().replace(
                R.id.container, comunityDescriptionFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myBroadcastReceiver, intentFilterDescriptionComunity);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
    }
    //****************************************************************************************

    //Metodo llamado por el fragment
    @Override
    public void changeFragmentInMain(String destiny) {
        //ingresar fragment My Comunity
        if (destiny.equalsIgnoreCase(MyComunityFragment.DESTINY)){
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.container, myComunityFragment).commit();
        }
    }
}