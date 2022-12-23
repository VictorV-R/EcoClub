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
import android.widget.Toast;

import com.example.ecoclub.Entities.Usuario_Comunidad;
import com.example.ecoclub.R;
import com.example.ecoclub.comunity.AdapterComunityDescription;
import com.example.ecoclub.database.DbUsuariosComunidades;

import java.util.ArrayList;

public class ComunityDescFragModerator extends Fragment {

    private static final String ARG_PARAM1 = "idComunidad";

    // TODO: Rename and change types of parameters
    private String idComunidad;

    //Recycler View================================
    private ArrayList<Usuario_Comunidad> listMembersComunity;
    private RecyclerView recyclerComunityDescModerator;
    //=============================================
    private Button btnSalirComunidad;
    private Button btnAgregarMiembro;
    private Button btnCrearActividad;

    //Todo:Fragments para salir de la comunidad==================
    private ComunityDescriptionFragment comuDescFrag;
    private ComunityDescFragNotMember comunityDescFragNotMember;
    //Todo:==========================================================
    private FragmentCreateActivity fragmentCreateActivity;

    public ComunityDescFragModerator() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ComunityDescFragModerator newInstance(String idComunidad) {
        ComunityDescFragModerator fragment = new ComunityDescFragModerator();
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
        View view = inflater.inflate(R.layout.fragment_comunity_desc_frag_moderator,
                container, false);

        //botones y eventos
        btnSalirComunidad = view.findViewById(R.id.btnSalirMiComunidadModerator);
        btnSalirComunidad.setOnClickListener(eventoSalirMiComunidad);

        btnAgregarMiembro = view.findViewById(R.id.btnAgregarMiembrosModerator);
        btnAgregarMiembro.setOnClickListener(eventoAgregarMiembro);

        btnCrearActividad = view.findViewById(R.id.btnCrearActividadModerator);
        btnCrearActividad.setOnClickListener(eventoCrearActividad);
        //Adaptador
        referenciarAdaptador(view);

        return view;
    }

    //eventos========================================================
    private View.OnClickListener eventoSalirMiComunidad =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Saliendo de mi comunidad",
                            Toast.LENGTH_LONG).show();

                    //Todo:Utilizar la base de datos aqui para salir****************
                    //**********************************************************

                    //para cambiar de interface a no miembro
                    new Thread(new Runnable() {
                        public void run() {
                            //para conectar con el padre fragment
                            comuDescFrag = ((ComunityDescriptionFragment)
                                    ComunityDescFragModerator.this.getParentFragment());

                            comunityDescFragNotMember = ComunityDescFragNotMember
                                    .newInstance(idComunidad);

                            //para cmbiar de interface
                            comuDescFrag.getChildFragmentManager().beginTransaction()
                                    .replace(R.id.fragmentLayoutComunityDesc,
                                            comunityDescFragNotMember).commit();
                        }
                    }).start();

                }
            };

    private View.OnClickListener eventoAgregarMiembro =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Agregando miembro",
                            Toast.LENGTH_LONG).show();

                    //Todo:Utilizar la base de datos aqui para agregar miembro
                    // Todo: a traves de un correo electronico

                }
            };

    private View.OnClickListener eventoCrearActividad =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Creando actividad",
                            Toast.LENGTH_LONG).show();

                    //para crear actividad
                    new Thread(new Runnable() {
                        public void run() {

                            //Todo:agregarle parametros mas adelante como el id
                            //Todo: de la comunidad para unirlo en el otro fragmento
                            //todo: para la base de datos
                            fragmentCreateActivity = new FragmentCreateActivity();

                            //para cmbiar de interface
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, fragmentCreateActivity)
                                    .addToBackStack(null).commit();
                        }
                    }).start();

                }
            };
    //===============================================================

    private void referenciarAdaptador(View view) {
        //Recycler View=======================
        //Referenciamos el RecyclerView del layout
        recyclerComunityDescModerator = view.findViewById(
                R.id.recyclerListaMiembrosComunidadModerator);
        //para cargar una lista vertical
        recyclerComunityDescModerator.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false));
        //llenando datos de comunidad
        llenarDatosMiembrosComunity();

        //enviamos los datos al adaptador de Comunidad
        AdapterComunityDescription adapter = new AdapterComunityDescription(
                listMembersComunity, getActivity());
        //por ultimo al recycler le enviamos el adaptador de la Comunidad
        recyclerComunityDescModerator.setAdapter(adapter);
        ///===================================
    }

    private void llenarDatosMiembrosComunity() {
        DbUsuariosComunidades dbUsuariosComunidades=new DbUsuariosComunidades();
        listMembersComunity = dbUsuariosComunidades.obtenerUsuariosComunidad(
                Integer.parseInt(idComunidad));
    }
}