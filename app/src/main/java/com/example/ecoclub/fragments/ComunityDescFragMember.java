package com.example.ecoclub.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecoclub.Entities.Usuario_Comunidad;
import com.example.ecoclub.R;
import com.example.ecoclub.comunity.AdapterComunityDescription;
import com.example.ecoclub.database.DbUsuariosComunidades;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComunityDescFragMember#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComunityDescFragMember extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "idComunidad";

    // TODO: Rename and change types of parameters
    private String idComunidad;

    //Recycler View================================
    private ArrayList<Usuario_Comunidad> listMembersComunity;
    private RecyclerView recyclerComunityDescMember;
    //=============================================

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comunity_desc_frag_member, container, false);
        //adaptador
        referenciarAdaptador(view);
        
        return view;
    }

    private void referenciarAdaptador(View view) {

        //Recycler View=======================
        //Referenciamos el RecyclerView del layout
        recyclerComunityDescMember = view.findViewById(
                R.id.recyclerListaMiembrosMiComunidad);
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
}