package com.example.ecoclub.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.ecoclub.interfaces.MainActivityCallbacks;

import java.util.ArrayList;

public class ComunityDescFragMember extends Fragment {

    private static final String ARG_PARAM1 = "idComunidad";
    private MainActivityCallbacks mainActivity;

    // TODO: Rename and change types of parameters
    private String idComunidad;

    //Recycler View================================
    private ArrayList<Usuario_Comunidad> listMembersComunity;
    private RecyclerView recyclerComunityDescMember;
    //=============================================
    private Button btnSalirComunidad;

    //Todo:Fragments para salir de la comunidad==================
    private ComunityDescFragNotMember comunityDescFragNotMember;
    private ComunityDescriptionFragment comuDescFrag;
    //=========================================================

    public ComunityDescFragMember() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ComunityDescFragMember newInstance(String idComunidad) {
        ComunityDescFragMember fragment = new ComunityDescFragMember();
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
        //ocultamos el progress bar
        ((ComunityDescriptionFragment)
                ComunityDescFragMember.this.getParentFragment()).ocultarProgressBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comunity_desc_frag_member, container, false);

        btnSalirComunidad = view.findViewById(R.id.btnSalirMiComunidad);
        btnSalirComunidad.setOnClickListener(eventoSalirMiComunidad);
        //adaptador
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
                            ComunityDescFragMember.this.getParentFragment());

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
    //===============================================================

    private void referenciarAdaptador(View view) {

        //Recycler View=======================
        //Referenciamos el RecyclerView del layout
        recyclerComunityDescMember = view.findViewById(
                R.id.recyclerListaMiembrosMiComunidad);

        //desactivando scroll del recyclerview
        recyclerComunityDescMember.setNestedScrollingEnabled(false);

        //para cargar una lista vertical
        recyclerComunityDescMember.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false));
        //llenando datos de comunidad
        llenarDatosMiembrosComunity();

        //enviamos los datos al adaptador de Comunidad
        AdapterComunityDescription adapter = new AdapterComunityDescription(
                listMembersComunity, getActivity());
        //por ultimo al recycler le enviamos el adaptador de la Comunidad
        recyclerComunityDescMember.setAdapter(adapter);
        ///===================================
        
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