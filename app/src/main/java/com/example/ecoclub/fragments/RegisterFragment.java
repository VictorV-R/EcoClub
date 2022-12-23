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

import com.example.ecoclub.Entities.Usuario;
import com.example.ecoclub.R;
import com.example.ecoclub.exceptions.BlankFieldsException;
import com.example.ecoclub.exceptions.PasswordException;
import com.example.ecoclub.interfaces.AuthenticationActivityCallbacks;

import java.util.ArrayList;

public class RegisterFragment extends Fragment {

    private Button btn_register;
    EditText edt_name, edt_lastName, edt_email, edt_phone, edt_password;
    private Usuario user;

    private AuthenticationActivityCallbacks authenticationActivityCallbacks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        user = new Usuario();

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        edt_name = view.findViewById(R.id.edt_name);
        edt_lastName = view.findViewById(R.id.edt_lastName);
        edt_email = view.findViewById(R.id.edt_email);
        edt_phone = view.findViewById(R.id.edt_phone);
        edt_password = view.findViewById(R.id.edt_password);

        btn_register = view.findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    ArrayList<EditText> fields = new ArrayList<EditText>();
                    fields.add(edt_name);
                    fields.add(edt_lastName);
                    fields.add(edt_email);
                    fields.add(edt_phone);
                    fields.add(edt_password);

                    authenticationActivityCallbacks.checkEmptyFields(fields);
                    authenticationActivityCallbacks.passwordValidation(edt_password);

                    user.setEmail(edt_email.getText().toString());
                    user.setName(edt_name.getText().toString());
                    user.setLastName(edt_lastName.getText().toString());
                    user.setPhone("+51" + edt_phone.getText().toString());
                    user.setPassword(edt_password.getText().toString());

                    authenticationActivityCallbacks.signUp(user);
                    authenticationActivityCallbacks.clearFields(fields);

                }catch (BlankFieldsException b){
                    b.getMsg();
               }catch (PasswordException p){
                    p.getMsg();
                }
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