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
import com.example.ecoclub.comunity.AdapterComunityDescription;
import com.example.ecoclub.database.DbUsuariosComunidades;
import com.example.ecoclub.interfaces.MainActivityCallbacks;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComunityDescFragNotMember#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComunityDescFragNotMember extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "idComunidad";

    // TODO: Rename and change types of parameters
    private String idComunidad;
    private MainActivityCallbacks mainActivity;

    //Recycler View================================
    private ArrayList<Usuario_Comunidad> listMembersComunity;
    private RecyclerView recyclerComunityDescNotMember;
    //=============================================
    private Button btnUnirseComunidad;

    //Todo:Fragments para salir de la comunidad==================
    private ComunityDescriptionFragment comuDescFrag;
    private ComunityDescFragMember comunityDescFragMember;
    //Todo:==========================================================

    public ComunityDescFragNotMember() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ComunityDescFragNotMember newInstance(String idComunidad) {
        ComunityDescFragNotMember fragment = new ComunityDescFragNotMember();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, idComunidad);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idComunidad = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comunity_desc_frag_not_member, container, false);

        btnUnirseComunidad = view.findViewById(R.id.btnUnirseComunidad);
        btnUnirseComunidad.setOnClickListener(eventoUnirseComunidad);
        //adaptador
        referenciarAdaptador(view);
        return view;
    }

    //eventos========================================================
    private View.OnClickListener eventoUnirseComunidad =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Uniendo a comunidad",
                            Toast.LENGTH_LONG).show();

                    //Todo:Utilizar la base de datos aqui para unirse****************
                    DbUsuariosComunidades dbUsuariosComunidades = new DbUsuariosComunidades();
                    dbUsuariosComunidades.insertarUsuarioComunidad(
                            mainActivity.sendCurrentUserDataFragment().getId(), Integer.parseInt(idComunidad),
                            "Activo", 3);
                    //**********************************************************

                    //para cambiar de interface a no miembro
                    new Thread(new Runnable() {
                        public void run() {
                            //para conectar con el padre fragment
                            comuDescFrag = ((ComunityDescriptionFragment)
                                    ComunityDescFragNotMember.this.getParentFragment());

                            comunityDescFragMember = ComunityDescFragMember
                                    .newInstance(idComunidad);

                            //para cmbiar de interface
                            comuDescFrag.getChildFragmentManager().beginTransaction()
                                    .replace(R.id.fragmentLayoutComunityDesc,
                                            comunityDescFragMember).commit();
                        }
                    }).start();

                }
            };
    //===============================================================

    private void referenciarAdaptador(View view) {
        //Recycler View=======================
        //Referenciamos el RecyclerView del layout
        recyclerComunityDescNotMember = view.findViewById(
                R.id.recyclerListaMiembrosComunidad);
        //para cargar una lista vertical
        recyclerComunityDescNotMember.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false));
        //llenando datos de comunidad
        llenarDatosMiembrosComunity();

        //enviamos los datos al adaptador de Comunidad
        AdapterComunityDescription adapter = new AdapterComunityDescription(
                listMembersComunity, getActivity());
        //por ultimo al recycler le enviamos el adaptador de la Comunidad
        recyclerComunityDescNotMember.setAdapter(adapter);
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