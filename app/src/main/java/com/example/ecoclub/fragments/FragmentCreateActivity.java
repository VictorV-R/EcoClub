package com.example.ecoclub.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ecoclub.MainActivity;
import com.example.ecoclub.R;

public class FragmentCreateActivity extends Fragment {

    private EditText nombreActividad;
    private EditText descripcionActividad;
    private EditText fechaActividad;
    private Button btnAtrasActividad;
    private Button btnCrearActividad;

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

            //Todo: agregar activity a la base de datos(idComunidad)
            //todo=========================================
            Toast.makeText(getActivity(), "Se creo su actividad",
                    Toast.LENGTH_LONG).show();

            //para no poder retroceder al formulario
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        }
    };
}