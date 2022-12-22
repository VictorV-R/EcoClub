package com.example.ecoclub.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ecoclub.Entities.Usuario;
import com.example.ecoclub.Entities.Usuario_Comunidad;
import com.example.ecoclub.R;
import com.example.ecoclub.database.DbUsuarios;
import com.example.ecoclub.database.DbUsuariosComunidades;

public class MessageDialogMemberComunity extends DialogFragment {
    public static String TAG = "MessageDialog";

    //Data
    private static final String ARG_PARAM1 = "cargo";
    private static final String ARG_PARAM2 = "nombre";
    private static final String ARG_PARAM3 = "rango";
    private static final String ARG_PARAM4 = "logros";

    static public MessageDialogMemberComunity newInstance(
            int id_usuario,int id_comunidad) {

        DbUsuariosComunidades dbUsuariosComunidades=new DbUsuariosComunidades();
        Usuario_Comunidad aux=dbUsuariosComunidades.obtenerUsuarioComunidad(id_comunidad,id_usuario);


        //se referencia asi mismo
        MessageDialogMemberComunity f = new MessageDialogMemberComunity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, aux.getTipo_usuario());
        args.putString(ARG_PARAM2, aux.getId_usuario()+"");
        args.putString(ARG_PARAM3, aux.getId_rango()+"");
        args.putString(ARG_PARAM4, aux.getTipo_usuario());
        f.setArguments(args);
        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //recibiendo por el flujo de datos
        final String cargoMiembro = getArguments().getString(ARG_PARAM1);
        final String nombreMiembro = getArguments().getString(ARG_PARAM2);
        final String rangoMiembro = getArguments().getString(ARG_PARAM3);
        final String logrosMiembro = getArguments().getString(ARG_PARAM4);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        //enlazando con el layaout que va en el dialogo
        View view = inflater.inflate(R.layout.dialog_fragment_message, null);

        //Cargo
        TextView txtMessagePositionComunity = view.findViewById(R.id.cargoMiembroComunidad);
        txtMessagePositionComunity.setText(cargoMiembro);
        //nombre
        TextView txtMessageNameComunity = view.findViewById(R.id.nombreMiembroComunidad);
        txtMessageNameComunity.setText(nombreMiembro);
        //rango
        TextView txtMessageRangeComunity = view.findViewById(R.id.rangoMiembroComunidad);
        txtMessageRangeComunity.setText(rangoMiembro);
        //logros
        TextView txtMessagePointsComunity = view.findViewById(R.id.logrosMiembroComunidad);
        txtMessagePointsComunity.setText(logrosMiembro);

        builder.setView(view);
        return builder.create();
    }
}
