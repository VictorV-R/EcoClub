package com.example.ecoclub.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecoclub.Entities.Participante_Actividad;
import com.example.ecoclub.Entities.Usuario_Comunidad;
import com.example.ecoclub.MainActivity;
import com.example.ecoclub.R;
import com.example.ecoclub.View.ViewTransparente;
import com.example.ecoclub.comunity.AdapterComunityDescription;
import com.example.ecoclub.database.DbParticipantesActividades;
import com.example.ecoclub.database.DbUsuariosComunidades;

import java.util.ArrayList;


public class ComunityDescriptionFragment extends Fragment {
    public static final String DESTINY = "Comunity_Description";
    //componentes
    private TextView msgDesFrag;
    private TextView msgDesFragName;
    //img description comunity
    private ViewTransparente imgDesCom;
    private Button btnAtras;

    private ComunityFragment comunityFragment = new ComunityFragment();
    //Fragments hijos================================
    private FragmentTransaction fragmentTransaction;
    //inicializarlos con newInstance es necesario para pasar parametros
    private ComunityDescFragMember comunityDescFragMember;
    private ComunityDescFragNotMember comunityDescFragNotMember;
    private ComunityDescFragModerator comunityDescFragModerator;
    //=============================================

    private static final String ARG_PARAM1 = "id";
    private static final String ARG_PARAM2 = "name";
    private static final String ARG_PARAM3 = "description";

    //Guardara la id del Comunity
    private String id;
    private String nombre;
    private String descripcion;

    public ComunityDescriptionFragment() {
        // Required empty public constructor
    }

    public static ComunityDescriptionFragment newInstance(
            String id, String nombre, String description) {

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, id);
        args.putString(ARG_PARAM2, nombre);
        args.putString(ARG_PARAM3, description);
        ComunityDescriptionFragment fragment = new ComunityDescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.id = getArguments().getString(ARG_PARAM1);
            this.nombre = getArguments().getString(ARG_PARAM2);
            this.descripcion = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comunity_description, container, false);

        ///////se colocaron nombres por defecto por ahora//////////////////////
        //name comunity
        this.msgDesFragName = view.findViewById(R.id.msgComunityDescriptionName);
        this.msgDesFragName.setText(this.nombre);

        //description comunity
        this.msgDesFrag = view.findViewById(R.id.msgComunityDescription);
        this.msgDesFrag.setText(this.descripcion);

        //event image description comunity
        this.imgDesCom = view.findViewById(R.id.imgDesCom);
        this.imgDesCom.setOnClickListener(eventImgDesCom);
        //boton Atras
        btnAtras = view.findViewById(R.id.btnAtrasDescriptionComunity);
        btnAtras.setOnClickListener(eventAtras);

        //para aliviar el fragment utilizamos un hilo
        new Thread(new Runnable() {
            public void run() {
                //Aquí ejecutamos nuestras tareas costosas
                //fragments hijos
                fragmentSegunRangoMiembro(view);
            }
        }).start();

        return view;
    }

    private void fragmentSegunRangoMiembro(View view) {
        //como se tiene el id de usuario y comunidad se podria filtrar
        //el rango para aperturar los siguientes fragments hijos
/*
        //Todo: Miembro
        //por default dejaremos el de miembro por mientras
        //le pasamos el id de la comunidad
        comunityDescFragMember = ComunityDescFragMember.newInstance(id);
        fragmentTransaction = getChildFragmentManager().beginTransaction();

        //para mostrarlo en la interface
        fragmentTransaction.replace(R.id.fragmentLayoutComunityDesc,
                comunityDescFragMember).commit();
*/
/*
        //Todo: No miembro
        comunityDescFragNotMember = ComunityDescFragNotMember.newInstance(id);
        fragmentTransaction = getChildFragmentManager().beginTransaction();

        //para mostrarlo en la interface
        fragmentTransaction.replace(R.id.fragmentLayoutComunityDesc,
                comunityDescFragNotMember).commit();
*/

        //Todo: Moderador
        comunityDescFragModerator = ComunityDescFragModerator.newInstance(id);
        fragmentTransaction = getChildFragmentManager().beginTransaction();

        //para mostrarlo en la interface
        fragmentTransaction.replace(R.id.fragmentLayoutComunityDesc,
                comunityDescFragModerator).commit();

    }

    //event image description comunity
    private View.OnClickListener eventImgDesCom = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Esta es una imagen con click", Toast.LENGTH_LONG).show();
        }
    };

    private View.OnClickListener eventAtras = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, comunityFragment).commit();
        }
    };
}