package com.example.ecoclub.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;
import com.example.ecoclub.R;

public class ProfileDatosFragment extends Fragment {

   TextView txt_name, txt_lastName, txt_email, txt_phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_datos, container, false);
        txt_name = view.findViewById(R.id.textViewNombre);
        txt_lastName = view.findViewById(R.id.textViewApellido);
        txt_email = view.findViewById(R.id.textViewEmail);
        txt_phone = view.findViewById(R.id.textViewCelular);

        loadProfile();

        return view;

    }

    public void loadProfile(){
        Amplify.Auth.fetchUserAttributes(
                result -> {
                    Log.i("AuthQuickStart", "Array -> " + result.toString());
                    txt_name.setText(result.get(2).getValue());
                    txt_lastName.setText(result.get(3).getValue());
                    txt_phone.setText(result.get(5).getValue());
                    txt_email.setText(result.get(6).getValue());

                },
                error -> Log.e("AuthQuickStart", error.toString())
        );
    }
    /*[AuthUserAttribute {key=AuthUserAttributeKey {attributeKey=sub}, value=ac1297fe-4d74-4c66-b3c1-42b43835f1a6},
    {key=AuthUserAttributeKey {attributeKey=email_verified}, value=true},
    AuthUserAttribute {key=AuthUserAttributeKey {attributeKey=name}, value=Walter Huaracha},
    AuthUserAttribute {key=AuthUserAttributeKey {attributeKey=phone_number_verified},value=false},
    AuthUserAttribute {key=AuthUserAttributeKey {attributeKey=phone_number},value=+51915909135},
    AuthUserAttribute {key=AuthUserAttributeKey {attributeKey=email}, value=whuaracha@unsa.edu.pe}]*/
}