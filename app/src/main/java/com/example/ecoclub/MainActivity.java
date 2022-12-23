package com.example.ecoclub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.example.ecoclub.Entities.Usuario;
import com.example.ecoclub.database.DbUsuarios;
import com.example.ecoclub.dialog.MessageDialogQuit;
import com.amplifyframework.core.Amplify;
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

    //Datos del Usuario Inicio Sesion
    private Usuario currentUser = null;

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

        requestCurrentUserDataInMain();

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

            if (bottomNavigationView.getSelectedItemId() == R.id.home) {
                //Dialogo para salir de la app o no

                //Dialogo para salir de la app o no
                MessageDialogQuit dialogoSalir= new MessageDialogQuit();
                dialogoSalir.show(getSupportFragmentManager()
                        .beginTransaction(), null);
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

    //recuperando datos del ussuario conectdo
    @Override
    public void requestCurrentUserDataInMain(){
        currentUser = new Usuario();
        DbUsuarios dbUsuarios = new DbUsuarios();

        Amplify.Auth.fetchUserAttributes(
                result -> {
                    currentUser.setName(result.get(2).getValue());
                    currentUser.setLastName(result.get(5).getValue());
                    currentUser.setPhone(result.get(4).getValue());
                    currentUser.setEmail(result.get(6).getValue());
                },
                error -> Log.e("AuthQuickStart", error.toString())
        );

        currentUser.setId(dbUsuarios.recuperarUsuarioID(currentUser.getEmail()));
        Log.i("IDuser","-> "+currentUser.getId());
    }

    @Override
    public Usuario sendCurrentUserDataFragment(){
        return currentUser;
    }
}