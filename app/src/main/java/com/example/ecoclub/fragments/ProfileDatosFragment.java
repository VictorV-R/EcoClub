package com.example.ecoclub.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;
import com.example.ecoclub.Entities.Usuario;
import com.example.ecoclub.R;
import com.example.ecoclub.interfaces.AuthenticationActivityCallbacks;
import com.example.ecoclub.interfaces.MainActivityCallbacks;

public class ProfileDatosFragment extends Fragment {

   TextView txt_name, txt_lastName, txt_email, txt_phone;
   MainActivityCallbacks mainActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_datos, container, false);
        txt_name = view.findViewById(R.id.textViewNombre);
        txt_lastName = view.findViewById(R.id.textViewApellido);
        txt_email = view.findViewById(R.id.textViewEmail);
        txt_phone = view.findViewById(R.id.textViewCelular);

        loadProfileData();

        return view;

    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivityCallbacks){
            mainActivity = (MainActivityCallbacks) context;
        }
    }

    void loadProfileData(){
        Usuario userInfo =  mainActivity.sendCurrentUserDataFragment();
        txt_name.setText(userInfo.getName());
        txt_lastName.setText(userInfo.getLastName());
        txt_email.setText(userInfo.getEmail());
        txt_phone.setText(userInfo.getPhone());

    }

}