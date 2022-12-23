package com.example.ecoclub.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.example.ecoclub.Entities.Usuario;
import com.example.ecoclub.R;
import com.example.ecoclub.interfaces.AuthenticationActivityCallbacks;
import com.example.ecoclub.interfaces.MainActivityCallbacks;

public class ProfileDatosFragment extends Fragment {

   TextView txt_name, txt_lastName, txt_email, txt_phone;
   MainActivityCallbacks mainActivity;
   Button btn_logout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_datos, container, false);
        txt_name = view.findViewById(R.id.textViewNombre);
        txt_lastName = view.findViewById(R.id.textViewApellido);
        txt_email = view.findViewById(R.id.textViewEmail);
        txt_phone = view.findViewById(R.id.textViewCelular);

        btn_logout = view.findViewById(R.id.btn_cerrarSesion);

        loadProfileData();

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.logout();
            }
        });

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