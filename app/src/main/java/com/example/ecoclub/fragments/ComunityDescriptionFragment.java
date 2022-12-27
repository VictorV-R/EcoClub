package com.example.ecoclub.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.TransitionInflater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecoclub.Entities.Rango;
import com.example.ecoclub.R;
import com.example.ecoclub.View.ViewTransparente;
import com.example.ecoclub.database.DbViewsHelpers;
import com.example.ecoclub.interfaces.MainActivityCallbacks;

import java.util.ArrayList;


public class ComunityDescriptionFragment extends Fragment {
    public static final String DESTINY = "Comunity_Description";
    //componentes
    private TextView msgDesFrag;
    private TextView msgDesFragName;
    //img description comunity
    private ViewTransparente imgDesCom;
    private Button btnAtras;
    private static ProgressBar progressBar;

    private FragmentTransaction fragmentTransaction;
    private MainActivityCallbacks mainActivity;
    //Fragmentos Hijos
    //inicializarlos con newInstance es necesario para pasar parametros
    private ComunityDescFragMember comunityDescFragMember;
    private ComunityDescFragNotMember comunityDescFragNotMember;
    private ComunityDescFragModerator comunityDescFragModerator;

    //Comunidad datos
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

        //name comunity
        this.msgDesFragName = view.findViewById(R.id.msgComunityDescriptionName);
        this.msgDesFragName.setText(this.nombre);

        //description comunity
        this.msgDesFrag = view.findViewById(R.id.msgComunityDescription);
        this.msgDesFrag.setText(this.descripcion);

        //event image description comunity
        ImageView imageView = view.findViewById(R.id.imageDescriptionComunity);
        Animation hyperspaceJump = AnimationUtils.loadAnimation(
                getContext(), R.anim.hyperspace_jump);
        imageView.startAnimation(hyperspaceJump);
        this.imgDesCom = view.findViewById(R.id.imgDesCom);
        this.imgDesCom.setOnClickListener(eventImgDesCom);
        //boton Atras
        btnAtras = view.findViewById(R.id.btnAtrasDescriptionComunity);
        btnAtras.setOnClickListener(eventAtras);
        //progressbar
        progressBar = view.findViewById(R.id.progress_circular_comunity_description);

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

    public static void ocultarProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void fragmentSegunRangoMiembro(View view) {

        //se obtiene el rango del usuario en la comunidad
        //para aperturar los siguientes fragments hijos

        DbViewsHelpers dbViewsHelpers = new DbViewsHelpers();
        Rango rangoUsuarioComunidad = dbViewsHelpers.obtenerRangoMedianteUsuarioComunidad(
                mainActivity.sendCurrentUserDataFragment().getId(),
                Integer.parseInt(id)
        );

        //se ve que fragments hijos abrir dependiendo del rango
        switch (rangoUsuarioComunidad.getId()){
            case 1:
                //Todo:Admin de momento no se considera
                break;
            case 2:
                //Todo: Moderador
                comunityDescFragModerator = ComunityDescFragModerator.newInstance(id);
                fragmentTransaction = getChildFragmentManager().beginTransaction();

                //para mostrarlo en la interface
                fragmentTransaction.replace(R.id.fragmentLayoutComunityDesc,
                        comunityDescFragModerator).commit();
                break;
            case 3:
                //Todo: Miembro
                //por default dejaremos el de miembro por mientras
                //le pasamos el id de la comunidad
                comunityDescFragMember = ComunityDescFragMember.newInstance(id);
                fragmentTransaction = getChildFragmentManager().beginTransaction();

                //para mostrarlo en la interface
                fragmentTransaction.replace(R.id.fragmentLayoutComunityDesc,
                        comunityDescFragMember).commit();
                break;
            default:
                //Todo: No miembro
                comunityDescFragNotMember = ComunityDescFragNotMember.newInstance(id);
                fragmentTransaction = getChildFragmentManager().beginTransaction();

                //para mostrarlo en la interface
                fragmentTransaction.replace(R.id.fragmentLayoutComunityDesc,
                        comunityDescFragNotMember).commit();
                break;
        }

    }

    //event image description comunity
    private View.OnClickListener eventImgDesCom = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Esta es una imagen con click", Toast.LENGTH_LONG).show();
            ComunityMapFragment comunityMapFragment = new ComunityMapFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, comunityMapFragment).addToBackStack(null).commit();
        }
    };

    private View.OnClickListener eventAtras = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //para retroceder en el back stack
            getActivity().onBackPressed();
        }
    };


    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivityCallbacks){
            mainActivity = (MainActivityCallbacks) context;
        }
    }
}