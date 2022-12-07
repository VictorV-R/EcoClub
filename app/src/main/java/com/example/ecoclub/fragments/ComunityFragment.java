package com.example.ecoclub.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ecoclub.R;
import com.example.ecoclub.news.AdapterComunity;

import java.util.ArrayList;

public class ComunityFragment extends Fragment {

    //Recycler View
    private ArrayList<String> listNameComunity;
    private RecyclerView recycler;

    private ImageButton btnSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comunity, container, false);

        referenciarAdaptador(view);

        btnSearch = (ImageButton) view.findViewById(R.id.imgBtnSearch);
        btnSearch.setOnClickListener(btnEvent);
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
        listNameComunity = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            listNameComunity.add("Comunidad "+ (i+1));
        }
        //enviamos los datos al adaptador de Comunidad
        AdapterComunity adapter = new AdapterComunity(listNameComunity);
        //por ultimo al recycler le enviamos el adaptador de la Comunidad
        recycler.setAdapter(adapter);
        ///===================================
    }

    //Evento cuando se ejecuta el boton de buscar comunidad
    View.OnClickListener btnEvent = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "Si funciona el button image", Toast.LENGTH_LONG).show();
        }
    };
}