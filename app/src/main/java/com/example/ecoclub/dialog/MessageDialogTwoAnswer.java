package com.example.ecoclub.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ecoclub.R;

public class MessageDialogTwoAnswer extends DialogFragment {

    private static final String ARG_PARAM1 = "pregunta";
    private static final String ARG_PARAM2 = "nameBtnSi";
    private static final String ARG_PARAM3 = "nameBtnNo";

    private String pregunta;
    private String nameBtnSi;
    private String nameBtnNo;

    private TextView textoPregunta;
    private Button btnSi;
    private Button btnNo;

    public MessageDialogTwoAnswer(){
    }

    static public MessageDialogMemberComunity newInstance(
            String pregunta, String nameBtnSi, String nameBtnNo) {
        //se referencia asi mismo
        MessageDialogMemberComunity f = new MessageDialogMemberComunity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, pregunta);
        args.putString(ARG_PARAM2, nameBtnSi);
        args.putString(ARG_PARAM3, nameBtnNo);
        f.setArguments(args);
        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.pregunta = getArguments().getString(ARG_PARAM1);
            this.nameBtnSi = getArguments().getString(ARG_PARAM2);
            this.nameBtnNo = getArguments().getString(ARG_PARAM3);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        //enlazando con el layaout que va en el dialogo
        View view = inflater.inflate(R.layout.dialog_fragment_two_answer, null);

        this.textoPregunta = view.findViewById(R.id.textAnswer);
        //botones si y no
        this.btnSi = view.findViewById(R.id.btnSi);
        this.btnNo = view.findViewById(R.id.btnNo);

        //aqui se pone los nombres recibidos por parametros===
        nombresComponentes();
        //====================================================

        //eventos
        //SI=================================================
        btnSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cerramos la aplicacion
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        //NO================================================
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        builder.setView(view);
        return builder.create();
    }

    //para tener una plantilla
    private void nombresComponentes() {
        this.textoPregunta.setText(this.pregunta);
        this.btnSi.setText(this.nameBtnSi);
        this.btnNo.setText(this.nameBtnNo);
    }

}
