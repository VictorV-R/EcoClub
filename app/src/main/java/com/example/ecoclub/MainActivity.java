package com.example.ecoclub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

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

    private FragmentTransaction ft;
    private BottomNavigationView bottomNavigationView;
    //Bottom Navigation-Fragments
    private HomeFragment homeFragment = new HomeFragment();
    private MapsFragment mapsFragment = new MapsFragment();
    private ComunityFragment comunityFragment = new ComunityFragment();
    private CollaborateFragment collaborateFragment = new CollaborateFragment();
    private ProfileFragment profileFragment = new ProfileFragment();
    //Otros-Fragments
    private MyComunityFragment myComunityFragment = new MyComunityFragment();
    private ComunityDescriptionFragment comunityDescriptionFragment = new ComunityDescriptionFragment();

    String api="18f1b34a081148119e242db1fb37a8e9";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, homeFragment);
        //ft.setReorderingAllowed(true);
        ft.addToBackStack(null); //agregado para que se pueda retroceder en los fragments
        ft.commit();

        //evento del bottom navigation view
        bottomNavigationView.setOnItemSelectedListener(eventoBottomNavigationView);

    }

    //evento del bottom navigation view*****************************************
    private NavigationBarView.OnItemSelectedListener eventoBottomNavigationView =
            new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {

            //limpiando back stack
            clearBackStack();

            ft = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()){
                case R.id.home:
                    ft.replace(R.id.container, homeFragment);
                    //ft.setReorderingAllowed(true);
                    ft.addToBackStack(null);
                    ft.commit();
                    return true;
                case R.id.maps:
                    ft.replace(R.id.container, mapsFragment);
                    //ft.setReorderingAllowed(true);
                    ft.addToBackStack(null);
                    ft.commit();
                    return true;
                case R.id.comunity:
                    ft.replace(R.id.container, comunityFragment);
                    //ft.setReorderingAllowed(true);
                    ft.addToBackStack(null);
                    ft.commit();
                    return true;
                case R.id.collaborate:
                    ft.replace(R.id.container, collaborateFragment);
                    //ft.setReorderingAllowed(true);
                    ft.addToBackStack(null);
                    ft.commit();
                    return true;
                case R.id.profile:
                    ft.replace(R.id.container, profileFragment);
                    //ft.setReorderingAllowed(true);
                    ft.addToBackStack(null);
                    ft.commit();
                    return true;
            }
            return false;
        }
    };

    //Metodo llamado por el fragment**********************************************
    @Override
    public void changeFragmentInMain(String destiny) {
        //ingresar fragment My Comunity
        if (destiny.equalsIgnoreCase(MyComunityFragment.DESTINY)){
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, myComunityFragment);
            //ft.setReorderingAllowed(true);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    //para limpiar el back stack*************************************************
    private void clearBackStack() {
        FragmentManager manager = getSupportFragmentManager();
        //solo dejamos el Home Fragment (> 1 e index 1)
        if (manager.getBackStackEntryCount() > 1) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(1);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

}