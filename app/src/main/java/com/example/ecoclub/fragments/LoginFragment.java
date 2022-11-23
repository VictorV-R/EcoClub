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

public class LoginFragment extends Fragment {

    private Button btn_login;
    EditText edt_email, edt_password;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        edt_email = view.findViewById(R.id.edt_mail);
        edt_password = view.findViewById(R.id.edt_password);
        btn_login = view.findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_email.getText().toString();
                String pass = edt_password.getText().toString();

                DBUsers dbUsers = new DBUsers(getActivity());

                try {
                    checkEmptyFields(email, pass);
                    dbUsers.checkUserEmailPassword(email, pass);
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
        edt_email.setText("");
        edt_password.setText("");
    }

    private void checkEmptyFields (String email, String pass) throws BlankFieldsException {
        if(email.equals("") || pass.equals("")) throw new BlankFieldsException(getActivity(), "Complete todos los campos!!");

    }
}

