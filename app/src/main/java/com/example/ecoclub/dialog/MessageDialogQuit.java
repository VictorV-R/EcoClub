package com.example.ecoclub.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ecoclub.MainActivity;
import com.example.ecoclub.R;
import com.example.ecoclub.fragments.HomeFragment;

public class MessageDialogQuit extends DialogFragment {
    private Button btnSi;
    private Button btnNo;

    public MessageDialogQuit(){
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        //enlazando con el layaout que va en el dialogo
        View view = inflater.inflate(R.layout.dialog_fragment_quit, null);

        //botones si y no
        this.btnSi = view.findViewById(R.id.btnSi);
        this.btnNo = view.findViewById(R.id.btnNo);
        //eventos
        btnSi.setOnClickListener(eventSi);
        btnNo.setOnClickListener(eventNo);

        builder.setView(view);
        return builder.create();
    }

    private View.OnClickListener eventSi = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //para que no se muestre el dialog tampoco
            getDialog().dismiss();
            //Cerramos la aplicacion
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    };

    private View.OnClickListener eventNo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getDialog().dismiss();
        }
    };
}
