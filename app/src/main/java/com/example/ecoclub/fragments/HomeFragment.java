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

public class HomeFragment extends Fragment {
    private View view;
    private Fragment homeNoticiasFragment;
    private Fragment homeActividadesFragment;
    FragmentTransaction transaction;
    private AppCompatRadioButton rbLeft, rbRight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        rbLeft = view.findViewById(R.id.radioButtonLeft);
        rbRight = view.findViewById(R.id.radioButtonRight);
        rbLeft.setOnCheckedChangeListener(new Radio_check());
        rbRight.setOnCheckedChangeListener(new Radio_check());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeNoticiasFragment = new HomeNoticiasFragment();
        transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.child_fragment_container, homeNoticiasFragment).addToBackStack(null).commit();
    }

    class Radio_check implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //limpiando back stack
            clearBackStack();
            if(rbLeft.isChecked()) {
                rbLeft.setTextColor(Color.BLACK);
                rbRight.setTextColor(Color.GRAY);
                homeNoticiasFragment = new HomeNoticiasFragment();
                transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.child_fragment_container, homeNoticiasFragment).addToBackStack(null).commit();
            }
            else if(rbRight.isChecked()) {
                rbRight.setTextColor(Color.BLACK);
                rbLeft.setTextColor(Color.GRAY);
                homeActividadesFragment = new HomeActividadesFragment();
                transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.child_fragment_container, homeActividadesFragment).addToBackStack(null).commit();
            }
        }
    }
    //para limpiar el back stack**
    private void clearBackStack() {
        FragmentManager manager = getChildFragmentManager();
        //solo dejamos el Home Fragment(index 1)
        if (manager.getBackStackEntryCount() > 1) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(1);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}