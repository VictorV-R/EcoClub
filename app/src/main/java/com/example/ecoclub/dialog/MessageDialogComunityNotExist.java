package com.example.ecoclub.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ecoclub.R;

public class MessageDialogComunityNotExist extends DialogFragment {

    private Button btnAceptar;

    public MessageDialogComunityNotExist(){
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        //enlazando con el layaout que va en el dialogo
        View view = inflater.inflate(
                R.layout.dialog_fragment_comunity_not_exist, null);

        //botones si y no
        this.btnAceptar = view.findViewById(R.id.btnAceptar);
        //eventos
        btnAceptar.setOnClickListener(eventAceptar);

        builder.setView(view);
        return builder.create();
    }

    private View.OnClickListener eventAceptar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getDialog().dismiss();
        }
    };
}
