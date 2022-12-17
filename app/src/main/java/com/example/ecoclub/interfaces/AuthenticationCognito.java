package com.example.ecoclub.interfaces;

import android.widget.EditText;

import com.example.ecoclub.Entities.Person;
import com.example.ecoclub.exceptions.BlankFieldsException;
import com.example.ecoclub.exceptions.PasswordException;

import java.util.ArrayList;

public interface AuthenticationCognito {
    void replaceFragmentAnotherFragment(String nameFragment);
    void checkEmptyFields(ArrayList<EditText> fields) throws BlankFieldsException;
    void clearFields(ArrayList<EditText> fields);
    void passwordValidation(EditText password) throws PasswordException;
    void signUp(Person person);
    void confirmSignUp(String username, String code);
    void signIn(String username, String password);
}
