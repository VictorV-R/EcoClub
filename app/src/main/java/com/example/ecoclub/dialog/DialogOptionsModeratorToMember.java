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
import com.example.ecoclub.database.DbUsuariosComunidades;

public class DialogOptionsModeratorToMember extends DialogFragment {
    //Data
    private static final String ARG_PARAM1 = "id_usuario";
    private static final String ARG_PARAM2 = "id_comunidad";

    private String id_usuario;
    private String id_comunidad;

    Button btnInfoMiembro;
    Button btnDeasignarMiembro;
    Button btnExpulsarMiembro;

    static public DialogOptionsModeratorToMember newInstance(
            int id_usuario,int id_comunidad) {
        //se referencia asi mismo
        DialogOptionsModeratorToMember f = new DialogOptionsModeratorToMember();
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
        View view = inflater.inflate(R.layout.dialog_options_moderator_to_member, null);

        this.btnInfoMiembro = view.findViewById(R.id.btnOpcionInfoMember);
        this.btnDeasignarMiembro = view.findViewById(R.id.btnOpcionAsignarRangoMember);
        this.btnExpulsarMiembro = view.findViewById(R.id.btnOpcionExpulsarMember);

        //eventos
        this.btnInfoMiembro.setOnClickListener(eventoInfoMiembro);
        this.btnDeasignarMiembro.setOnClickListener(eventoAsignarMiembro);
        this.btnExpulsarMiembro.setOnClickListener(eventoExpulsarMiembro);

        builder.setView(view);
        return builder.create();
    }

    //Eventos
    private View.OnClickListener eventoInfoMiembro = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getContext(), "Info miembro", Toast.LENGTH_LONG).show();

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
    private View.OnClickListener eventoAsignarMiembro = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            new Thread(new Runnable() {
                public void run() {
                    DbUsuariosComunidades dbUsuariosComunidades = new DbUsuariosComunidades();
                    dbUsuariosComunidades.modificarRangoUsuarioComunidad(
                            Integer.parseInt(id_usuario),
                            Integer.parseInt(id_comunidad),
                            "Moderador"
                    );
                }
            }).start();

            Toast.makeText(getContext(), "Se asigno moderador", Toast.LENGTH_LONG).show();

            //cerrar ventana
            getDialog().dismiss();

        }
    };
    private View.OnClickListener eventoExpulsarMiembro = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            new Thread(new Runnable() {
                public void run() {
                    DbUsuariosComunidades dbUsuariosComunidades = new DbUsuariosComunidades();
                    dbUsuariosComunidades.eliminarUsuarioComunidad(
                            Integer.parseInt(id_usuario),
                            Integer.parseInt(id_comunidad));
                }
            }).start();


            Toast.makeText(getContext(), "Se expulso miembro", Toast.LENGTH_LONG).show();

            //cerrar ventana
            getDialog().dismiss();

        }
    };
}
