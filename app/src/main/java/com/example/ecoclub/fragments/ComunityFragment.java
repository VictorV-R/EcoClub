package com.example.ecoclub.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ecoclub.MainActivity;
import com.example.ecoclub.R;
import com.example.ecoclub.comunity.AdapterComunity;
import com.example.ecoclub.comunity.ComunityContent;

import java.util.ArrayList;

public class ComunityFragment extends Fragment {

    //Activity
    private MainActivity main;

    //Recycler View
    private ArrayList<ComunityContent> listComunity;
    private RecyclerView recycler;

    private ImageButton btnSearch;
    private Button btnMyComunity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comunity, container, false);

        referenciarAdaptador(view);

        btnSearch = (ImageButton) view.findViewById(R.id.imgBtnSearch);
        btnSearch.setOnClickListener(btnEventSearch);

        btnMyComunity = (Button) view.findViewById(R.id.btnMyComunity);
        btnMyComunity.setOnClickListener(btnEventMyComunity);
        return view;
    }

    //para referenciar el adaptador y relacionarlo con el RecyclerView de Comunidades
    //también se genera la informacion
    private void referenciarAdaptador(View view) {
        //Recycler View=======================
        //Referenciamos el RecyclerView del layout
        recycler = view.findViewById(R.id.idRecyclerComunity);
        //para cargar una lista vertical
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false));
        //llenando datos de comunidad
        listComunity = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            listComunity.add(new ComunityContent((i+1),
                    "Comunidad "+ (i+1)));
        }
        //enviamos los datos al adaptador de Comunidad
        AdapterComunity adapter = new AdapterComunity(listComunity);
        //por ultimo al recycler le enviamos el adaptador de la Comunidad
        recycler.setAdapter(adapter);
        ///===================================
    }

    //Evento cuando se ejecuta el boton de buscar comunidad
    View.OnClickListener btnEventSearch = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "Si funciona el button buscar", Toast.LENGTH_LONG).show();
        }
    };

    //Evento cuando se ejecuta el boton de mis comunidad
    View.OnClickListener btnEventMyComunity = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "Mis Comunidades", Toast.LENGTH_LONG).show();
            main = (MainActivity) getActivity();
            //mensaje al main para cambiar al fragment MyComunity
            main.changeFragmentInMain(MyComunityFragment.DESTINY);
        }
    };
}