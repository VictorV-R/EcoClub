package com.example.ecoclub;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.example.ecoclub.Entities.Person;
import com.example.ecoclub.database.DBUsers;
import com.example.ecoclub.exceptions.BlankFieldsException;
import com.example.ecoclub.exceptions.DataBasesException;
import com.example.ecoclub.fragments.AuthenticationFragment;
import com.example.ecoclub.fragments.ConfirmFragment;
import com.example.ecoclub.fragments.LoginFragment;
import com.example.ecoclub.fragments.RegisterFragment;
import com.example.ecoclub.interfaces.AuthenticationCognito;

import java.util.ArrayList;

public class AuthenticationActivity extends AppCompatActivity implements AuthenticationCognito {

    private DBUsers dbUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        dbUsers = new DBUsers(getApplicationContext());
        loadSesion();

        AuthenticationFragment authenticationFragment = new AuthenticationFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, authenticationFragment).commit();

    }

    @Override
    public void replaceFragmentAnotherFragment(String nameFragment) {
        switch (nameFragment){
            case "LOGIN":
                loadLoginFragment();
                break;

            case "REGISTER":
                loadRegisterFragment();
                break;
        }
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

    @Override
    public void signUp(Person person){

        ArrayList<AuthUserAttribute> attributes = new ArrayList<>();
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.email(), person.getEmail()));
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.phoneNumber(), person.getPhone()));
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.name(), person.getName()));

        Amplify.Auth.signUp(
                person.getUsername(),
                person.getPassword(),
                AuthSignUpOptions.builder().userAttributes(attributes).build(),
                result -> {
                    Log.i("AuthQuickstart", result.toString());
                    loadConfirmFragment(person.getUsername());
                },
                error -> Log.e("AuthQuickstart", error.toString())
        );
    }
    @Override
    public void confirmSignUp(String username, String code){

        Amplify.Auth.confirmSignUp(
                username,
                code,
                result -> {
                    Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
                    loadLoginFragment();
                },
                error -> Log.e("AuthQuickstart", error.toString())
        );
    }
    @Override
    public void signIn(String username, String password){
        Amplify.Auth.signIn(
                username,
                password,
                result -> {
                    Log.i("AuthQuickstart", result.isSignedIn() ? "Sign in succeeded" : "Sign in not complete");
                    loadMainActivity();
                },
                error -> Log.e("AuthQuickstart", error.toString())
        );
    }

    public void loadLoginFragment(){
        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,loginFragment).addToBackStack(null).commit();
    }

    public void loadConfirmFragment(String username){
        ConfirmFragment confirmFragment = new ConfirmFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,confirmFragment).addToBackStack(null).commit();
        confirmFragment.assignUsername(username);
    }

    public void loadRegisterFragment() {
        RegisterFragment registerFragment = new RegisterFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,registerFragment).addToBackStack(null).commit();
    }

    public void loadMainActivity() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

    public void loadSesion (){
        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );
    }
}