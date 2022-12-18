package com.example.ecoclub.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecoclub.R;
import com.example.ecoclub.comunity.AdapterComunity;
import com.example.ecoclub.comunity.AdapterComunityDescription;
import com.example.ecoclub.comunity.ComunityContent;
import com.example.ecoclub.comunity.Member;

import java.util.ArrayList;


public class ComunityDescriptionFragment extends Fragment {
    public static final String DESTINY = "Comunity_Description";
    //componentes
    private TextView msgDesFrag;
    private TextView msgDesFragName;

    //Recycler View================================
    private ArrayList<Member> listMembersComunity;
    private RecyclerView recyclerComunityDescription;
    //=============================================

    private static final String ARG_PARAM1 = "id";

    //Guardara la id del Comunity
    private String id;

    public ComunityDescriptionFragment() {
        // Required empty public constructor
    }

    public static ComunityDescriptionFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, id);
        ComunityDescriptionFragment fragment = new ComunityDescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(ARG_PARAM1);
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
        this.msgDesFragName.setText("Comunidad "+ id);

        //description comunity
        this.msgDesFrag = view.findViewById(R.id.msgComunityDescription);
        this.msgDesFrag.setText("Descripción de comunidad "+ id);

        referenciarAdaptador(view);

        return view;
    }

    //para configurar lo que falte para que el RecyclerView funcione bien
    private void referenciarAdaptador(View view) {
        //Recycler View=======================
        //Referenciamos el RecyclerView del layout
        recyclerComunityDescription = view.findViewById(R.id.idRecyclerComunityDescription);
        //para cargar una lista vertical
        recyclerComunityDescription.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false));
        //llenando datos de comunidad
        listMembersComunity = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            listMembersComunity.add(new Member((i+1),
                    "Miembro "+ (i+1)));
        }
        //enviamos los datos al adaptador de Comunidad
        AdapterComunityDescription adapter = new AdapterComunityDescription(
                listMembersComunity, getActivity());
        //por ultimo al recycler le enviamos el adaptador de la Comunidad
        recyclerComunityDescription.setAdapter(adapter);
        ///===================================
    }
}