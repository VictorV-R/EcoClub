package com.example.ecoclub;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.example.ecoclub.Entities.Usuario;
import com.example.ecoclub.database.DbUsuarios;
import com.example.ecoclub.exceptions.BlankFieldsException;
import com.example.ecoclub.exceptions.PasswordException;
import com.example.ecoclub.fragments.AuthenticationFragment;
import com.example.ecoclub.fragments.ConfirmFragment;
import com.example.ecoclub.fragments.LoginFragment;
import com.example.ecoclub.fragments.RegisterFragment;
import com.example.ecoclub.interfaces.AuthenticationActivityCallbacks;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AuthenticationActivity extends AppCompatActivity implements AuthenticationActivityCallbacks {

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
            if(e.getText().toString().isEmpty()) throw new BlankFieldsException(getApplicationContext());
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
        String COMPLEX_PASSWORD_REGEX =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";

        Pattern PASSWORD_PATTERN = Pattern.compile(COMPLEX_PASSWORD_REGEX);

        String pass = password.getText().toString();
        if( !PASSWORD_PATTERN.matcher(pass).matches() ) throw new PasswordException(getApplicationContext());
    }

    @Override
    public void signUp(Usuario user){

        ArrayList<AuthUserAttribute> attributes = new ArrayList<>();
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.email(), user.getEmail()));
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.name(), user.getName()));
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.familyName(), user.getLastName()));
        attributes.add(new AuthUserAttribute(AuthUserAttributeKey.phoneNumber(), user.getPhone()));

        Amplify.Auth.signUp(
                user.getEmail(),
                user.getPassword(),
                AuthSignUpOptions.builder().userAttributes(attributes).build(),
                result -> {
                    Log.i("Registro Exitoso !!!", result.toString());
                    loadConfirmFragment(user.getEmail());
                },
                error -> Log.e("Error al registrase", error.toString())
        );
    }
    @Override
    public void confirmSignUp(String email, String code){

        Amplify.Auth.confirmSignUp(
                email,
                code,
                result -> {
                    Log.i("Confirm Email info", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
                    registerUserDB(email);
                    loadLoginFragment();
                },
                error -> Log.e("Confirm Email info", error.toString())
        );
    }
    @Override
    public void signIn(String email, String password){
        Amplify.Auth.signIn(
                email,
                password,
                result -> {
                    Log.i("Login info", result.isSignedIn() ? "Sign in succeeded" : "Sign in not complete");
                    loadMainActivity();
                },
                error -> Log.e("Login error", error.toString())
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

    public void registerUserDB(String email){
        DbUsuarios dbUsuarios = new DbUsuarios();
        dbUsuarios.insertarUsuario("",email);
        Log.i("Info Register", "Usuario Registrado exitosamente con correo "+email);
    }

}