package com.example.ecoclub.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ecoclub.R;

public class DialogOptionsModeratorToModerator extends DialogFragment {

    //Data
    private static final String ARG_PARAM1 = "id_usuario";
    private static final String ARG_PARAM2 = "id_comunidad";

    private String id_usuario;
    private String id_comunidad;

    Button btnInfoModerador;
    Button btnDeasignarModerador;
    Button btnExpulsarModerador;

    static public DialogOptionsModeratorToModerator newInstance(
            int id_usuario,int id_comunidad) {
        //se referencia asi mismo
        DialogOptionsModeratorToModerator f = new DialogOptionsModeratorToModerator();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, String.valueOf(id_usuario));
        args.putString(ARG_PARAM2, String.valueOf(id_comunidad));
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.id_usuario = getArguments().getString(ARG_PARAM1);
            this.id_comunidad = getArguments().getString(ARG_PARAM2);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        //enlazando con el layaout que va en el dialogo
        View view = inflater.inflate(R.layout.dialog_options_moderator_to_moderator, null);

        this.btnInfoModerador = view.findViewById(R.id.btnOpcionInfoModerador);
        this.btnDeasignarModerador = view.findViewById(R.id.btnOpcionAsignarRangoModerador);
        this.btnExpulsarModerador = view.findViewById(R.id.btnOpcionExpulsarModerador);

        //eventos
        this.btnInfoModerador.setOnClickListener(eventoInfoModerador);
        this.btnDeasignarModerador.setOnClickListener(eventoDeasignarModerador);
        this.btnExpulsarModerador.setOnClickListener(eventoExpulsarModerador);

        builder.setView(view);
        return builder.create();
    }

    //Eventos
    private View.OnClickListener eventoInfoModerador = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getContext(), "Info moderador", Toast.LENGTH_LONG).show();

            new Thread(new Runnable() {
                public void run() {
                    //info usuario
                    MessageDialogMemberComunity.newInstance(
                            Integer.parseInt(id_usuario),
                            Integer.parseInt(id_comunidad)
                    ).show(getActivity().getSupportFragmentManager(), null);
                }
            }).start();

        }
    };
    private View.OnClickListener eventoDeasignarModerador = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getContext(), "Deasignar moderador", Toast.LENGTH_LONG).show();
        }
    };
    private View.OnClickListener eventoExpulsarModerador = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getContext(), "Expulsar moderador", Toast.LENGTH_LONG).show();
        }
    };
}
