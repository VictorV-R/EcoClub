package com.example.ecoclub.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecoclub.MainActivity;
import com.example.ecoclub.R;
import com.example.ecoclub.View.ViewTransparente;
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
    //img description comunity
    private ViewTransparente imgDesCom;
    private Button btnAtras;

    //Recycler View================================
    private ArrayList<Member> listMembersComunity;
    private RecyclerView recyclerComunityDescription;
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
        //adaptador
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
        llenarDatosMiembrosComunity();

        //enviamos los datos al adaptador de Comunidad
        AdapterComunityDescription adapter = new AdapterComunityDescription(
                listMembersComunity, getActivity());
        //por ultimo al recycler le enviamos el adaptador de la Comunidad
        recyclerComunityDescription.setAdapter(adapter);
        ///===================================
    }

    //event image description comunity
    View.OnClickListener eventImgDesCom = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Esta es una imagen con click", Toast.LENGTH_LONG).show();
        }
    };

    private View.OnClickListener eventAtras = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ((MainActivity) getActivity()).changeFragmentInMain(
                    ComunityFragment.DESTINY);
        }
    };

    private void llenarDatosMiembrosComunity() {

        listMembersComunity = new ArrayList<>();
        ArrayList<String> listNombMiemComunity = new ArrayList<>();
        listNombMiemComunity.add("Juan Perez");
        listNombMiemComunity.add("Mario Palacios");
        listNombMiemComunity.add("Victor Pineda");
        listNombMiemComunity.add("Sandro Bustamante");
        listNombMiemComunity.add("Pedro Roque");
        listNombMiemComunity.add("Sandro Martinez");
        listNombMiemComunity.add("Brian Guerrero");
        listNombMiemComunity.add("Alex Ticona");
        listNombMiemComunity.add("Julio Lopez");
        listNombMiemComunity.add("Antonio Valdivia");

        for (int i = 0; i < listNombMiemComunity.size(); i++){
            listMembersComunity.add(new Member(
                    (i+1), listNombMiemComunity.get(i),
                    "Veterano", 21));
        }
    }
}