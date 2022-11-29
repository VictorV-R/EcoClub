package com.example.ecoclub;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ecoclub.database.DBUsers;
import com.example.ecoclub.exceptions.BlankFieldsException;
import com.example.ecoclub.exceptions.DataBasesException;
import com.example.ecoclub.fragments.AuthenticationFragment;
import com.example.ecoclub.fragments.LoginFragment;
import com.example.ecoclub.fragments.RegisterFragment;
import com.example.ecoclub.interfaces.MainCallbacks;

import java.util.ArrayList;

public class AuthenticationActivity extends AppCompatActivity implements MainCallbacks {

    private DBUsers dbUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        dbUsers = new DBUsers(getApplicationContext());

        AuthenticationFragment authenticationFragment = new AuthenticationFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, authenticationFragment).commit();

    }

    @Override
    public void onReplaceFragmentAnotherFragment(String nameFragment) {
        switch (nameFragment){
            case "LOGIN":
                LoginFragment loginFragment = new LoginFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,loginFragment).addToBackStack(null).commit();
                break;

            case "REGISTER":
                RegisterFragment registerFragment = new RegisterFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,registerFragment).addToBackStack(null).commit();
                break;
        }
    }

    @Override
    public void onLoadMainActivity() {

       Intent intent = new Intent(getApplicationContext(), MainActivity.class);
       startActivity(intent);

        //Toast.makeText(getApplicationContext(), "Mummm", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void checkEmptyFields (ArrayList<String> data) throws BlankFieldsException {
        for (String d: data) {
            if(d.length() == 0) throw new BlankFieldsException(getApplicationContext(), "Complete todos los campos!!");
        }
    }

    @Override
    public void checkUserEmailPassword(ArrayList<String> data) throws DataBasesException {
        dbUsers.checkUserEmailPassword(data);
    }

    @Override
    public void checkUserEmail(String email) throws DataBasesException {
        dbUsers.checkUserEmail(email);
    }

    @Override
    public void insertUser(ArrayList<String> data) throws DataBasesException {
        dbUsers.insertUser(data);
    }

}