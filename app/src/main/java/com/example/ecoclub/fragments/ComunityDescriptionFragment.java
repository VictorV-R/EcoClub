package com.example.ecoclub.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecoclub.R;


public class ComunityDescriptionFragment extends Fragment {
    public static final String DESTINY = "Comunity_Description";
    private TextView msgDesFrag;

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
        this.msgDesFrag = view.findViewById(R.id.msgComunityDescription);
        this.msgDesFrag.setText("Descripción de comunidad "+ id);
        return view;
    }
}