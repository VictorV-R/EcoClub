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
import com.example.ecoclub.interfaces.AuthenticationCognito;


public class ConfirmFragment extends Fragment {

    private Button btn_confirmation;
    private EditText edt_code;
    private String username;

    private AuthenticationCognito authenticationCognito;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_confirm, container, false);
        edt_code = view.findViewById(R.id.edt_code);
        btn_confirmation = view.findViewById(R.id.btn_confirmation);

        btn_confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = edt_code.getText().toString();
                authenticationCognito.confirmSignUp(username, code);
                clearFields();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AuthenticationCognito){
            authenticationCognito = (AuthenticationCognito) context;
        }
    }

    public void assignUsername(String username) {
        this.username = username;
    }

    public void clearFields() {
        edt_code.setText("");
    }
}