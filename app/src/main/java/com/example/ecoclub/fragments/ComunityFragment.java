package com.example.ecoclub.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.ecoclub.R;

public class ComunityFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //private RadioGroup radioGroupButton;
    private ComunityOthersFragment comunityOthersFragment;
    private MyComunityFragment myComunityFragment;
    private FragmentTransaction transaction;
    private AppCompatRadioButton rbLeft, rbRight;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ComunityFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ComunityFragment newInstance(String param1, String param2) {
        ComunityFragment fragment = new ComunityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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

        View view = inflater.inflate(R.layout.fragment_comunity, container, false);

        // Inflate the layout for this fragment
        this.rbLeft = view.findViewById(R.id.btnOtrasComunidades);
        this.rbRight = view.findViewById(R.id.btnMisComunidades);

        //eventos
        this.rbLeft.setOnCheckedChangeListener(eventoComunidades);
        this.rbRight.setOnCheckedChangeListener(eventoComunidades);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        comunityOthersFragment = new ComunityOthersFragment();
        transaction = getChildFragmentManager().beginTransaction();

        transaction.replace(R.id.frame_layout_comunity, comunityOthersFragment).commit();
    }

    private CompoundButton.OnCheckedChangeListener eventoComunidades = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            if(rbLeft.isChecked()) {
                rbLeft.setTextColor(Color.BLACK);
                rbRight.setTextColor(Color.GRAY);
                comunityOthersFragment = new ComunityOthersFragment();
                transaction = getChildFragmentManager().beginTransaction();
                transaction.setCustomAnimations(
                        R.anim.slide_in_rigth,  // enter
                        R.anim.slide_out  // exit
                ).replace(R.id.frame_layout_comunity, comunityOthersFragment).commit();
            }else
                if (rbRight.isChecked()){
                rbRight.setTextColor(Color.BLACK);
                rbLeft.setTextColor(Color.GRAY);
                myComunityFragment = new MyComunityFragment();
                transaction = getChildFragmentManager().beginTransaction();
                transaction.setCustomAnimations(
                        R.anim.slide_in_left,  // enter
                        R.anim.slide_out  // exit
                ).replace(R.id.frame_layout_comunity, myComunityFragment).commit();
            }
        }
    };
}