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
import com.example.ecoclub.comunity.AdapterMyComunity;
import com.example.ecoclub.comunity.ComunityContent;

import java.util.ArrayList;

public class MyComunityFragment extends Fragment {

    public static final String DESTINY = "My Comunity";

    private ArrayList<ComunityContent> listMyComunity;

    private RecyclerView recyclerMyComunity;
    private ImageButton btnSearchMyComunity;

    public MyComunityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_comunity, container, false);

        btnSearchMyComunity = view.findViewById(R.id.imgBtnSearchMyComunity);
        btnSearchMyComunity.setOnClickListener(eventSearchMyComunity);

        referenciarAdaptadorMyComunity(view);
        return view;
    }

    private void referenciarAdaptadorMyComunity(View view) {
        recyclerMyComunity = view.findViewById(R.id.idRecyclerMyComunity);
        //para cargar una lista vertical
        recyclerMyComunity.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false));
        //llenando datos de comunidad
        listMyComunity = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            listMyComunity.add(new ComunityContent((i+1),
                    "Mi Comunidad "+ (i+1)));
        }
        //enviamos la data al adaptador
        AdapterMyComunity adapterMyComunity = new AdapterMyComunity(listMyComunity);
        //por ultimo al recycler le enviamos el adaptador de mi Comunidad
        recyclerMyComunity.setAdapter(adapterMyComunity);
    }

    //eventos
    private View.OnClickListener eventSearchMyComunity = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "Buscar mis Comunidades", Toast.LENGTH_LONG).show();
        }
    };
}