package com.example.ecoclub.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ecoclub.R;
import com.example.ecoclub.interfaces.AuthenticationActivityCallbacks;

public class AuthenticationFragment extends Fragment {

    private final String [] NAME_FRAGMENT = {"LOGIN", "REGISTER"};

    private Button btn_login;
    private Button btn_register;
    private AuthenticationActivityCallbacks authenticationActivityCallbacks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_authentication, container, false);

        btn_login = view.findViewById(R.id.btn_login);
        btn_register = view.findViewById(R.id.btn_register);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticationActivityCallbacks.replaceFragmentAnotherFragment(NAME_FRAGMENT[0]);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticationActivityCallbacks.replaceFragmentAnotherFragment(NAME_FRAGMENT[1]);
            }
        });

        return view;

    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AuthenticationActivityCallbacks){
            authenticationActivityCallbacks = (AuthenticationActivityCallbacks) context;
        }
    }

}