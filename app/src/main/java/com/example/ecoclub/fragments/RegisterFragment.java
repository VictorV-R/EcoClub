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

public class RegisterFragment extends Fragment {

    private Button btn_register;
    EditText edt_name, edt_lastName, edt_email, edt_phone, edt_password;
    private ArrayList<String> data;

    private MainCallbacks mainCallbacks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        data = new ArrayList<String>();

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
                    data.add(edt_name.getText().toString());
                    data.add(edt_lastName.getText().toString());
                    data.add(edt_email.getText().toString());
                    data.add(edt_phone.getText().toString());
                    data.add(edt_password.getText().toString());

                    //mainCallbacks.checkEmptyFields(data);
                    mainCallbacks.checkUserEmail(data.get(2));
                    mainCallbacks.insertUser(data);
                    clearFields();
                    mainCallbacks.onLoadMainActivity();

                }//catch (BlankFieldsException b){}
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
        edt_name.setText("");
        edt_lastName.setText("");
        edt_email.setText("");
        edt_phone.setText("");
        edt_password.setText("");
    }
}