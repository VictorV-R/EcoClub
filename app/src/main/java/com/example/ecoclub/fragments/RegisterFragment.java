package com.example.ecoclub.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ecoclub.MainActivity;
import com.example.ecoclub.R;
import com.example.ecoclub.database.DBUsers;
import com.example.ecoclub.exceptions.BlankFieldsException;
import com.example.ecoclub.exceptions.DataBasesException;

import java.net.PasswordAuthentication;

public class RegisterFragment extends Fragment {

    private Button btn_register;
    EditText edt_name, edt_lastName, edt_email, edt_phone, edt_password;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
                String name = edt_name.getText().toString();
                String lastName = edt_lastName.getText().toString();
                String email = edt_email.getText().toString();
                String phone = edt_phone.getText().toString();
                String pass = edt_password.getText().toString();

                DBUsers dbUsers = new DBUsers(getActivity());

                try {
                    checkEmptyFields(name,lastName,email,phone,pass);
                    dbUsers.checkUserEmail(email);
                    dbUsers.insertUser(name,lastName,email,phone,pass);
                    clearFields();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
                catch (BlankFieldsException b){}
                catch (DataBasesException d){}

            }
        });

        return view;
    }
    private void clearFields(){
        edt_name.setText("");
        edt_lastName.setText("");
        edt_email.setText("");
        edt_phone.setText("");
        edt_password.setText("");
    }

    private void checkEmptyFields (String name, String lastName,String email, String phone, String pass) throws BlankFieldsException {
        if(name.equals("") || lastName.equals("") || email.equals("") || phone.equals("") || pass.equals("")) throw new BlankFieldsException(getActivity(), "Complete todos los campos!!");

    }

}