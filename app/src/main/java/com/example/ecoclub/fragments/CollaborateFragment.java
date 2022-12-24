package com.example.ecoclub.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecoclub.R;
import com.example.ecoclub.database.DbComunidades;
import com.example.ecoclub.dialog.MessageDialogMemberComunity;
import com.google.android.gms.maps.model.LatLng;

import java.util.ResourceBundle;


public class CollaborateFragment extends Fragment {

    // DAtos
    private View view;
    private EditText nombreComunidad, descripcionComunidad;
    private Button btnCrearComunidad;
    private Fragment mapsFragment;
    FragmentTransaction transaction;
    private LatLng ubicacion;
    private String resultado = "NAda";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapsFragment = new MapsFragment();
        transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.maps_select_frame, mapsFragment).addToBackStack(null).commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_collaborate, container, false);

        nombreComunidad = view.findViewById(R.id.editTextNombreComunidad);
        descripcionComunidad = view.findViewById(R.id.editTextDescripcion);



        btnCrearComunidad = view.findViewById(R.id.buttonCrearComunity);

        btnCrearComunidad.setOnClickListener(view -> {
            DbComunidades dbComunidades = new DbComunidades();
            dbComunidades.insertarComunidad(nombreComunidad.getText().toString(), descripcionComunidad.getText().toString(), ubicacion.latitude, ubicacion.longitude );
        });
        return view;
    }

    public void recuperarUbicacion(LatLng dato){
        ubicacion = dato;
    }

}