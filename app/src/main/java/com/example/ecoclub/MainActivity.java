package com.example.ecoclub;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.example.ecoclub.fragments.CollaborateFragment;
import com.example.ecoclub.fragments.ComunityDescriptionFragment;
import com.example.ecoclub.fragments.ComunityFragment;
import com.example.ecoclub.fragments.HomeFragment;
import com.example.ecoclub.fragments.MapsFragment;
import com.example.ecoclub.fragments.MapsViewFragment;
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
    private MapsViewFragment mapsFragment = new MapsViewFragment();
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

        //ingresar fragment My Comunity
        if (destiny.equalsIgnoreCase(ComunityFragment.DESTINY)){
            //limpiamos el back stack
            //clearBackStack();
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, comunityFragment);
            //ft.setReorderingAllowed(true);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    //para limpiar el back stack*************************************************
    private void clearBackStack() {
        FragmentManager manager = getSupportFragmentManager();
        //solo dejamos el Home Fragment(index 1)
        if (manager.getBackStackEntryCount() > 1) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(1);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

    }

    //boton de atras
    // dialogo para salir de la app
    // y actualizacion de icono de bottom navigation view
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        FragmentManager manager;
        Fragment fragmentCurrent;

        if (keyCode == event.KEYCODE_BACK)
        {
            //cambiamos el icono del bottom navigation================================
            manager = getSupportFragmentManager();

            if (bottomNavigationView.getSelectedItemId() == R.id.home) {
                //Dialogo para salir de la app o no
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("¿Desea salir de NuNa?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Cerramos la aplicacion
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                //para que inicie con un fragment
                                bottomNavigationView.setSelectedItemId(R.id.home);
                            }
                        });
                builder.show();
            }


            //ACTIVAMOS EL ICONO DE HOME EN EL BOTTOM NAVIGATION
            //con el boton de atras se limpia el back stack
            fragmentCurrent = getSupportFragmentManager()
                    .findFragmentById(R.id.container);
            //Estos framgents se abren con el bottom navigation
            //En caso de que se cambie algun fragment en el menu cambiar tambien este
            if (fragmentCurrent instanceof ComunityFragment
                    || fragmentCurrent instanceof MapsFragment
                    || fragmentCurrent instanceof CollaborateFragment
                    || fragmentCurrent instanceof ProfileFragment) {
                bottomNavigationView.setSelectedItemId(R.id.home);
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}