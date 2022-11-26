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
import android.widget.Toast;

import com.example.ecoclub.R;
import com.example.ecoclub.exceptions.BlankFieldsException;
import com.example.ecoclub.exceptions.DataBasesException;
import com.example.ecoclub.interfaces.MainCallbacks;

import java.util.ArrayList;

public class LoginFragment extends Fragment{

    private Button btn_login;
    private EditText edt_email, edt_password;
    private ArrayList<String> data;

    private MainCallbacks mainCallbacks;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        data = new ArrayList<String>();

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        edt_email = view.findViewById(R.id.edt_mail);
        edt_password = view.findViewById(R.id.edt_password);
        btn_login = view.findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try {
                    data.add(edt_email.getText().toString());
                    data.add(edt_password.getText().toString());

                    mainCallbacks.checkEmptyFields(data);
                    mainCallbacks.checkUserEmailPassword(data);
                    clearFields();
                    mainCallbacks.onLoadMainActivity();

                }catch (BlankFieldsException b){}
                catch (DataBasesException d){}
                Toast.makeText(getActivity(), "Porque falla, pipipi", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainCallbacks){
            mainCallbacks = (MainCallbacks) context;
        }
    }

    public void clearFields() {
        edt_email.setText("");
        edt_password.setText("");
    }
}

