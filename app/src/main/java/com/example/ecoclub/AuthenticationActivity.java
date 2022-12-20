package com.example.ecoclub;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.amplifyframework.auth.AuthChannelEventName;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.InitializationStatus;
import com.amplifyframework.hub.HubChannel;
import com.example.ecoclub.Entities.Person;
import com.example.ecoclub.exceptions.BlankFieldsException;
import com.example.ecoclub.exceptions.PasswordException;
import com.example.ecoclub.fragments.AuthenticationFragment;
import com.example.ecoclub.fragments.ConfirmFragment;
import com.example.ecoclub.fragments.LoginFragment;
import com.example.ecoclub.fragments.RegisterFragment;
import com.example.ecoclub.interfaces.AuthenticationCognito;

import java.util.ArrayList;

public class AuthenticationActivity extends AppCompatActivity implements AuthenticationCognito {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

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
    public void checkEmptyFields (ArrayList<EditText> fields) throws BlankFieldsException {
        for (EditText e: fields) {
            if(e.getText().toString().equals("")) throw new BlankFieldsException(getApplicationContext());
        }
    }

    @Override
    public void clearFields (ArrayList<EditText> fields) {
        for (EditText e: fields) {
            e.setText("");
        }
    }

    @Override
    public void passwordValidation(EditText password) throws PasswordException {
        if(password.getText().length() < 8); throw new PasswordException(getApplicationContext());
    }

    @Override
    public void signUp(Person person){

        ArrayList<AuthUserAttribute> attributes = new ArrayList<>();
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.name(), person.getName()));
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.familyName(), person.getName()));
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.email(), person.getLastName()));
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.phoneNumber(), person.getPhone()));

        Amplify.Auth.signUp(
                person.getEmail(),
                person.getPassword(),
                AuthSignUpOptions.builder().userAttributes(attributes).build(),
                result -> {
                    Log.i("Prueba de Registro", result.toString());
                    loadConfirmFragment(person.getEmail());
                },
                error -> Log.e("Prueba de registro", error.toString())
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
        loadSesion();
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
                result -> {
                    AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
                    switch(cognitoAuthSession.getIdentityIdResult().getType()) {
                        case SUCCESS:
                            Log.i("AuthQuickStart", "IdentityId: " + cognitoAuthSession.getIdentityIdResult().getValue());
                            loadMainActivity();
                            break;
                        case FAILURE:
                            Log.i("AuthQuickStart", "IdentityId not present because: " + cognitoAuthSession.getIdentityIdResult().getError().toString());
                    }
                },
                error -> Log.e("AuthQuickStart", error.toString())
        );
    }
}