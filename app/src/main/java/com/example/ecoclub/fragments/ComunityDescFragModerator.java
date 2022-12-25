package com.example.ecoclub.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ecoclub.Entities.Usuario_Comunidad;
import com.example.ecoclub.R;
import com.example.ecoclub.comunity.AdapterComuDescModerator;
import com.example.ecoclub.database.DbUsuariosComunidades;
import com.example.ecoclub.dialog.MessageDialogAddUserToComunity;
import com.example.ecoclub.interfaces.ComunityDescriptionCallbacks;
import com.example.ecoclub.interfaces.MainActivityCallbacks;

import java.util.ArrayList;

public class ComunityDescFragModerator extends Fragment
        implements ComunityDescriptionCallbacks {

    private static final String ARG_PARAM1 = "idComunidad";
    private MainActivityCallbacks mainActivity;

    // TODO: Rename and change types of parameters
    private String idComunidad;

    //Recycler View================================
    private ArrayList<Usuario_Comunidad> listMembersComunity = new ArrayList<>();
    private RecyclerView recyclerComunityDescModerator;
    private AdapterComuDescModerator adapter = null;
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
                    DbUsuariosComunidades dbUsuariosComunidades = new DbUsuariosComunidades();
                    dbUsuariosComunidades.eliminarUsuarioComunidad(
                            mainActivity.sendCurrentUserDataFragment().getId(), Integer.parseInt(idComunidad));
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

                    //Todo:Dialogo para agregar miembro
                    // Todo: a traves de un correo electronico
                    new Thread(new Runnable() {
                        public void run() {
                            //Aquí ejecutamos nuestras tareas costosas
                            MessageDialogAddUserToComunity.newInstance(
                                    idComunidad).show(getActivity().
                                    getSupportFragmentManager(), null);
                        }
                    }).start();

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

                            //Todo:nos llevara a un formulario para crear actividad
                            fragmentCreateActivity = FragmentCreateActivity
                                    .newInstance(idComunidad);

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

        //desactivando scroll del recyclerview
        recyclerComunityDescModerator.setNestedScrollingEnabled(false);
        //para cargar una lista vertical
        recyclerComunityDescModerator.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false));

        actualizarAdaptador();

    }

    @Override
    public void actualizarAdaptador(){
        //llenando datos de miembros de comunidad
        llenarDatosMiembrosComunity();

        //enviamos los datos al adaptador de miembros de Comunidad
        int id_usuario = mainActivity.sendCurrentUserDataFragment().getId();

        new Thread(new Runnable() {
            public void run() {
                //Aquí ejecutamos nuestras tareas costosas
                adapter = new AdapterComuDescModerator(
                        listMembersComunity, getActivity(), id_usuario);
                //por ultimo al recycler le enviamos el adaptador de miembros de la Comunidad
                recyclerComunityDescModerator.setAdapter(adapter);
                ///===================================
            }
        }).start();
    }

    private void llenarDatosMiembrosComunity() {
        DbUsuariosComunidades dbUsuariosComunidades=new DbUsuariosComunidades();
        listMembersComunity = dbUsuariosComunidades.obtenerUsuariosComunidad(
                Integer.parseInt(idComunidad));
    }
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivityCallbacks){
            mainActivity = (MainActivityCallbacks) context;
        }
    }
}