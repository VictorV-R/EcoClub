package com.example.ecoclub.interfaces;

import android.widget.EditText;

import com.example.ecoclub.Entities.Usuario;
import com.example.ecoclub.exceptions.BlankFieldsException;
import com.example.ecoclub.exceptions.EmailExistsException;
import com.example.ecoclub.exceptions.PasswordException;

import java.util.ArrayList;

public interface AuthenticationActivityCallbacks {
    void replaceFragmentAnotherFragment(String nameFragment);
    void checkEmptyFields(ArrayList<EditText> fields) throws BlankFieldsException;
    void clearFields(ArrayList<EditText> fields);
    void passwordValidation(EditText password) throws PasswordException;
    void EmailExistsValidation(String email) throws EmailExistsException;
    void signUp(Usuario user);
    void confirmSignUp(Usuario user, String code);
    void signIn(String username, String password);
}
