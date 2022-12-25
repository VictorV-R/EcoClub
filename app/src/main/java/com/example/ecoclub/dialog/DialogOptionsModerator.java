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

public class DialogOptionsModerator extends DialogFragment {

    //Data
    private static final String ARG_PARAM1 = "id_usuario";
    private static final String ARG_PARAM2 = "id_comunidad";

    private String id_usuario;
    private String id_comunidad;

    Button btnInfo;

    static public DialogOptionsModerator newInstance(
            int id_usuario,int id_comunidad) {
        //se referencia asi mismo
        DialogOptionsModerator f = new DialogOptionsModerator();
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
        View view = inflater.inflate(R.layout.dialog_options_moderator, null);

        this.btnInfo = view.findViewById(R.id.btnOpcionInfo);

        //eventos
        this.btnInfo.setOnClickListener(eventoInfo);

        builder.setView(view);
        return builder.create();
    }

    //Eventos
    private View.OnClickListener eventoInfo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getContext(), "Tu perfil", Toast.LENGTH_LONG).show();

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
}
