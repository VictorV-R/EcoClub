package com.example.ecoclub.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ecoclub.R;
import com.google.android.gms.maps.model.LatLng;

public class ComunityMapFragment extends Fragment {

    private Button btnAtrasMapaComunidad;

    private MapsFragment mapsFragment;
    FragmentTransaction transaction;
    private static LatLng ubicacion;
    private String resultado = "NAda";

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ComunityMapFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ComunityMapFragment newInstance(String param1, String param2) {
        ComunityMapFragment fragment = new ComunityMapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //como asignar el mapa al formulario de crear actividad
        mapsFragment = new MapsFragment();
        transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.mapa_comunidad, mapsFragment)
                .addToBackStack(null).commit();

        //ubicacion = new LatLng(-16.343266354689543,-71.52994126081467);
        //mapsFragment.enviarUbicacion(ubicacion);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comunity_map, container, false);

        //botones y eventos
        this.btnAtrasMapaComunidad = view.findViewById(R.id.btnAtrasMapaComunidad);
        this.btnAtrasMapaComunidad.setOnClickListener(eventoAtrasMapaComunidad);

        return view;
    }

    private View.OnClickListener eventoAtrasMapaComunidad =
            new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //para retroceder en el back stack
            getActivity().onBackPressed();
        }
    };
}