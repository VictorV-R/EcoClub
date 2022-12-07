package com.example.ecoclub.fragments;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import com.example.ecoclub.R;


public class ProfileFragment extends Fragment {

    private View view;
    private RadioGroup radioGroupButton;
    private AppCompatRadioButton rbLeft, rbRight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        rbLeft = view.findViewById(R.id.radioButtonLeft);
        rbRight = view.findViewById(R.id.radioButtonRight);

        rbLeft.setOnCheckedChangeListener(new Radio_check());
        rbRight.setOnCheckedChangeListener(new Radio_check());

        return view;
    }

    class Radio_check implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(rbLeft.isChecked()) {
                rbLeft.setTextColor(Color.BLACK);
                rbRight.setTextColor(Color.GREEN);
            }
            else if(rbRight.isChecked()) {
                rbRight.setTextColor(Color.BLACK);
                rbLeft.setTextColor(Color.GREEN);
            }
        }
    }
}
