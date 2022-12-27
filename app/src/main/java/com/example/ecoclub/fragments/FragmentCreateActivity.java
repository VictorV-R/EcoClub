package com.example.ecoclub.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ecoclub.MainActivity;
import com.example.ecoclub.Notifications.NotificacionConfirmation;
import com.example.ecoclub.R;
import com.example.ecoclub.database.DbActividades;
import com.example.ecoclub.database.DbComunidades;
import com.google.android.gms.maps.model.LatLng;

public class FragmentCreateActivity extends Fragment {

    private EditText nombreActividad;
    private EditText descripcionActividad;
    private EditText fechaActividad;
    private Button btnAtrasActividad;
    private Button btnCrearActividad;

    private Fragment mapsFragment;
    FragmentTransaction transaction;
    private static LatLng ubicacion;
    private String resultado = "NAda";

    private static final String ARG_PARAM1 = "idComunidad";

    // TODO: Rename and change types of parameters
    private String idComunidad;

    public FragmentCreateActivity() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentCreateActivity newInstance(String idComunidad) {
        FragmentCreateActivity fragment = new FragmentCreateActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, idComunidad);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //como asignar el mapa al formulario de crear actividad
        mapsFragment = new MapsFragment();
        transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.maps_select_frame_activity, mapsFragment)
                .addToBackStack(null).commit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.idComunidad = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_activity, container, false);

        this.nombreActividad = view.findViewById(R.id.editNomComCrearActividad);
        this.descripcionActividad = view.findViewById(R.id.editDesComCrearActividad);
        this.fechaActividad = view.findViewById(R.id.editFechaComCrearActividad);

        //botones y eventos
        this.btnAtrasActividad = view.findViewById(R.id.btnAtrasCrearActivity);
        this.btnAtrasActividad.setOnClickListener(eventoAtrasCrearActividad);

        this.btnCrearActividad = view.findViewById(R.id.btnCrearActivity);
        this.btnCrearActividad.setOnClickListener(eventoCrearActividad);

        return view;
    }

    private View.OnClickListener eventoAtrasCrearActividad =
            new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //para retroceder en el back stack
            getActivity().onBackPressed();

        }
    };

    private View.OnClickListener eventoCrearActividad =
            new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //Todo: agregando activity a la base de datos==============
            //Todo: Tiene que ser moderador para crear actividad
            //Todo: el activity no guarda el nombre de quien creo la
            //Todo: actividad

            DbActividades dbActividades = new DbActividades();
            dbActividades.insertarActividad(
                    nombreActividad.getText().toString(),
                    Integer.parseInt(idComunidad),
                    fechaActividad.getText().toString(),
                    descripcionActividad.getText().toString(),
                    ubicacion.latitude, ubicacion.longitude);

            Log.d("Crear Actividad", "Actividad: "+ ubicacion.latitude +"  "+ ubicacion.longitude);
            //todo===================================================

            Toast.makeText(getContext(), "Se creo actividad", Toast.LENGTH_SHORT).show();

            //todo:  enviamos una notificacion
            /*NotificacionConfirmation notificacionConfirmation =
                    new NotificacionConfirmation(getActivity(), "Actividad de Comunidad",
                            "Felicidades creaste una actividad de comunidad");
            notificacionConfirmation.enviarNotificacion();*/
        }
    };


    public static void recuperarUbicacion(LatLng dato){
        ubicacion = dato;
    }
}