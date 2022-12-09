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

import com.example.ecoclub.Entities.Person;
import com.example.ecoclub.R;
import com.example.ecoclub.interfaces.AuthenticationCognito;

public class RegisterFragment extends Fragment {

    private Button btn_register;
    EditText edt_username, edt_name, edt_email, edt_phone, edt_password;
    private Person person;

    private AuthenticationCognito authenticationCognito;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        person = new Person();

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        edt_username = view.findViewById(R.id.edt_username);
        edt_name = view.findViewById(R.id.edt_name);
        edt_email = view.findViewById(R.id.edt_email);
        edt_phone = view.findViewById(R.id.edt_phone);
        edt_password = view.findViewById(R.id.edt_password);

        btn_register = view.findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                person.setUsername(edt_username.getText().toString());
                person.setName(edt_name.getText().toString());
                person.setEmail(edt_email.getText().toString());
                person.setPhone(edt_phone.getText().toString());
                person.setPassword(edt_password.getText().toString());

                authenticationCognito.signUp(person);
                clearFields();
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

    public void clearFields() {
        edt_username.setText("");
        edt_name.setText("");
        edt_email.setText("");
        edt_phone.setText("");
        edt_password.setText("");
    }
}