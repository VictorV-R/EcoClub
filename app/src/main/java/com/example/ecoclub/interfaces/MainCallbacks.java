package com.example.ecoclub.interfaces;

import com.example.ecoclub.exceptions.BlankFieldsException;
import com.example.ecoclub.exceptions.DataBasesException;

import java.util.ArrayList;

public interface MainCallbacks {
    void onReplaceFragmentAnotherFragment(String nameFragment);
    void onLoadMainActivity();
    void checkEmptyFields(ArrayList<String> data) throws BlankFieldsException;
    void checkUserEmailPassword(ArrayList<String> data) throws DataBasesException;
    void checkUserEmail(String email) throws DataBasesException;
    void insertUser(ArrayList<String> data) throws DataBasesException;
}
