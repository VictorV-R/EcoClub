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

import com.example.ecoclub.Activities.AdapterActivity;
import com.example.ecoclub.Entities.Actividad;
import com.example.ecoclub.Entities.Comunidad;
import com.example.ecoclub.R;
import com.example.ecoclub.comunity.AdapterOthersComunity;
import com.example.ecoclub.database.DbViewsHelpers;
import com.example.ecoclub.interfaces.MainActivityCallbacks;

import java.util.ArrayList;


public class HomeActividadesFragment extends Fragment {


    //Recycler View
    private ArrayList<Actividad> listActividades;
    private RecyclerView recycler;
    private MainActivityCallbacks mainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_actividades, container, false);

        referenciarAdaptador(view);
        
        return view;
    }

    private void referenciarAdaptador(View view) {

        //Recycler View=======================
        recycler = view.findViewById(R.id.recycleviewActividades);
        //para cargar una lista vertical
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false));

        //llenando datos de mis actividades
        llenarDatosActividades();

        AdapterActivity adapter = new AdapterActivity(
                getActivity(), listActividades,mainActivity.sendCurrentUserDataFragment().getId());

        recycler.setAdapter(adapter);
    }
    private void llenarDatosActividades() {
        listActividades = new ArrayList<>();
        DbViewsHelpers dbViewsHelpers = new DbViewsHelpers();
        listActividades = dbViewsHelpers.obtenerActividadesDeComunidadesSegunUsuario(
                mainActivity.sendCurrentUserDataFragment().getId());
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivityCallbacks){
            mainActivity = (MainActivityCallbacks) context;
        }
    }
}