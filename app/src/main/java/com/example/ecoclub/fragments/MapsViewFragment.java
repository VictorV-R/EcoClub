package com.example.ecoclub.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.ecoclub.R;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MapsViewFragment extends Fragment {

    private ArrayList<LatLng> ubicaciones;
    private View view;
    private Fragment mapsFragment;
    FragmentTransaction transaction;
    private AppCompatRadioButton rbLeft, rbRight;

    public MapsViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapsFragment = new MapsFragment();
        transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.maps_fragment_container, mapsFragment).addToBackStack(null).commit();
    }

    public static MapsViewFragment newInstance(String param1, String param2) {
        MapsViewFragment fragment = new MapsViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_maps_view, container, false);
        rbLeft = view.findViewById(R.id.radioButtonActividades);
        rbRight = view.findViewById(R.id.radioButtonComunidades);
        rbLeft.setOnCheckedChangeListener(new Radio_check());
        rbRight.setOnCheckedChangeListener(new Radio_check());
        // Inflate the layout for this fragment
        return view;
    }

    class Radio_check implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //limpiando back stack
            clearBackStack();
            if(rbLeft.isChecked()) {
                rbLeft.setTextColor(Color.BLACK);
                rbRight.setTextColor(Color.GRAY);
                mapsFragment = new MapsFragment();
                transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.maps_fragment_container, mapsFragment).addToBackStack(null).commit();
            }
            else if(rbRight.isChecked()) {
                rbRight.setTextColor(Color.BLACK);
                rbLeft.setTextColor(Color.GRAY);
                mapsFragment = new MapsFragment();
                transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.maps_fragment_container, mapsFragment).addToBackStack(null).commit();
            }
        }
    }
    //para limpiar el back stack*************************************************
    private void clearBackStack() {
        FragmentManager manager = getChildFragmentManager();
        //solo dejamos el Home Fragment(index 1)
        if (manager.getBackStackEntryCount() > 1) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(1);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

    }
}