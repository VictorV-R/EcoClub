package com.example.ecoclub.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ecoclub.R;
import com.example.ecoclub.database.DbUsuariosComunidades;
import com.example.ecoclub.fragments.ComunityDescriptionFragment;

public class MessageDialogAddUserToComunity extends DialogFragment {

    private static final String ARG_PARAM1 = "idComunidad";
    private String idComunidad;

    private EditText editCorreoUsuario;
    private Button btnAgregarUsuario;
    private Button btnCancelar;

    private ComunityDescriptionFragment comunityDescriptionFragment =
            new ComunityDescriptionFragment();

    public MessageDialogAddUserToComunity(){
    }

    static public MessageDialogAddUserToComunity newInstance(
            String id_comunidad) {

        //se referencia asi mismo
        MessageDialogAddUserToComunity f = new MessageDialogAddUserToComunity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, id_comunidad);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.idComunidad = getArguments().getString(ARG_PARAM1);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        //enlazando con el layaout que va en el dialogo
        View view = inflater.inflate(
                R.layout.dialog_fragment_add_user_to_comunity, null);

        this.editCorreoUsuario = view.findViewById(R.id.editEmailUsuario);
        //botones si y no
        this.btnAgregarUsuario = view.findViewById(R.id.btnAgregarUsuario);
        this.btnCancelar = view.findViewById(R.id.btnCancelar);
        //eventos
        this.btnAgregarUsuario.setOnClickListener(eventAgregarUsuario);
        this.btnCancelar.setOnClickListener(eventCancelar);

        builder.setView(view);
        return builder.create();
    }

    private View.OnClickListener eventAgregarUsuario = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //Todo: agregar usuario a base de datos
            //todo: ya vez si puedes crear una notificacion tambien
            DbUsuariosComunidades dbUsuariosComunidades = new DbUsuariosComunidades();
            dbUsuariosComunidades.insertarUsuarioComunidadConEmail(
                    editCorreoUsuario.getText().toString(),
                    Integer.parseInt(idComunidad),
                    "Activo",
                    3
            );

            getDialog().dismiss();

        }
    };
    private View.OnClickListener eventCancelar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getDialog().dismiss();
        }
    };
}
