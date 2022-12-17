package com.example.ecoclub.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ecoclub.R;
import com.example.ecoclub.exceptions.BlankFieldsException;
import com.example.ecoclub.interfaces.AuthenticationCognito;

import java.util.ArrayList;

public class LoginFragment extends Fragment{

    private Button btn_login;
    private EditText edt_email, edt_password;

    private AuthenticationCognito authenticationCognito;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        edt_email = view.findViewById(R.id.edt_name);
        edt_password = view.findViewById(R.id.edt_password);
        btn_login = view.findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String username = edt_email.getText().toString();
                    String password = edt_password.getText().toString();

                    ArrayList<EditText> fields = new ArrayList<EditText>();
                    fields.add(edt_email);
                    fields.add(edt_password);

                    authenticationCognito.checkEmptyFields(fields);
                    authenticationCognito.signIn(username, password);
                    authenticationCognito.clearFields(fields);

                }catch (BlankFieldsException b){
                    b.getMsg();
                }
            }
        });

        return view;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AuthenticationCognito){
            authenticationCognito = (AuthenticationCognito) context;
        }
    }
}