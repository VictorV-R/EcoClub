package com.example.ecoclub.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecoclub.R;
import com.example.ecoclub.database.DBProcedures;
import com.example.ecoclub.interfaces.MainActivityCallbacks;
import com.google.android.gms.maps.model.LatLng;


public class CollaborateFragment extends Fragment {

    // DAtos
    private View view;
    private EditText nombreComunidad, descripcionComunidad;
    private Button btnCrearComunidad;
    private Fragment mapsFragment;
    FragmentTransaction transaction;
    private MainActivityCallbacks mainActivity;

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
            //DbComunidades dbComunidades = new DbComunidades();
            //dbComunidades.insertarComunidad(nombreComunidad.getText().toString(), descripcionComunidad.getText().toString(), ubicacion.latitude, ubicacion.longitude );

            DBProcedures dbProcedures = new DBProcedures();
            dbProcedures.insertarComunidadYUsuarioComunidad(mainActivity.sendCurrentUserDataFragment().getId()
                    ,"Activo", nombreComunidad.getText().toString(), descripcionComunidad.getText().toString(),
                    ubicacion.latitude, ubicacion.longitude, 2);

            Toast.makeText(getActivity(), "Se creo comunidad",
                    Toast.LENGTH_LONG).show();
        });
        return view;
    }

    public void recuperarUbicacion(LatLng dato){
        ubicacion = dato;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivityCallbacks){
            mainActivity = (MainActivityCallbacks) context;
        }
    }

}