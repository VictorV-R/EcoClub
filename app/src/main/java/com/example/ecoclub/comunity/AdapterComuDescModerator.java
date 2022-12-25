package com.example.ecoclub.comunity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoclub.Entities.Usuario;
import com.example.ecoclub.Entities.Usuario_Comunidad;
import com.example.ecoclub.R;
import com.example.ecoclub.database.DbUsuarios;
import com.example.ecoclub.dialog.DialogOptionsModerator;
import com.example.ecoclub.dialog.DialogOptionsModeratorToMember;
import com.example.ecoclub.dialog.DialogOptionsModeratorToModerator;
import com.example.ecoclub.dialog.MessageDialogMemberComunity;

import java.util.ArrayList;

public class AdapterComuDescModerator extends RecyclerView.Adapter<AdapterComuDescModerator.ViewHolderData> {

    //lista de miembros
    private ArrayList<Usuario_Comunidad> listMembersComunity;
    private FragmentActivity main;
    private int you_id;

    public AdapterComuDescModerator(ArrayList<Usuario_Comunidad> listMembersComunity,
                                    FragmentActivity activity, int id_usuario){
        this.listMembersComunity = listMembersComunity;
        this.main = activity;
        this.you_id = id_usuario;
    }

    @NonNull
    @Override
    public AdapterComuDescModerator.ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflamos el view y lo retornamos
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comunity_description_moderator
                        , null, false);
        return new AdapterComuDescModerator.ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterComuDescModerator.ViewHolderData holder, int position) {
        //le enviamos el main al ViewHolder
        holder.cargarDatos(listMembersComunity.get(position));
    }

    @Override
    public int getItemCount() {
        return listMembersComunity.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {

        private TextView textNameComunityDescription;

        private CardView btnMember;
        private ImageButton imgBtnConfiMember;
        private Usuario_Comunidad member;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            this.textNameComunityDescription = itemView.findViewById(
                    R.id.textNameMemberComunity);
            this.btnMember = itemView.findViewById(
                    R.id.onClickComunityMember);  //CARDVIEW
            this.imgBtnConfiMember = itemView.findViewById(R.id.imgBtnConfigMember);
        }

        public void cargarDatos(Usuario_Comunidad member) {
            this.member = member;

            DbUsuarios dbUsuarios=new DbUsuarios();
            Usuario usuarioInfo=dbUsuarios.obtenerUsuario(member.getId_usuario());

            this.textNameComunityDescription.setText(usuarioInfo.getName());
            //evento-llamamos
            this.btnMember.setOnClickListener(eventMemberComunity);
            this.imgBtnConfiMember.setOnClickListener(eventMemberConfi);
        }

        //evento descripcion de miembro
        private View.OnClickListener eventMemberComunity = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Ver miembro", Toast.LENGTH_LONG).show();
                //como se tiene el id se puede hacer consultas
                new Thread(new Runnable() {
                    public void run() {
                        //Aquí ejecutamos nuestras tareas costosas
                        MessageDialogMemberComunity.newInstance(
                                        member.getId_usuario(),
                                        member.getId_comunidad())
                                .show(main.getSupportFragmentManager(), null);
                    }
                }).start();
            }
        };

        private View.OnClickListener eventMemberConfi = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                new Thread(new Runnable() {
                    public void run() {

                        //Todo: opciones de configuraciones segun el rango=============
                        if(member.getId_usuario() == you_id){
                            //Todo: este usuario es el que inicio sesion
                            DialogOptionsModerator.newInstance(
                                    member.getId_usuario(),
                                    member.getId_comunidad()
                            ).show(main.getSupportFragmentManager(), null);
                        }else{
                            if (member.getId_rango() == 3){
                                //Todo: miembro
                                DialogOptionsModeratorToMember.newInstance(
                                        member.getId_usuario(),
                                        member.getId_comunidad()
                                ).show(main.getSupportFragmentManager(), null);
                            }else{
                                if (member.getId_rango() == 2) {
                                    //Todo: moderador
                                    DialogOptionsModeratorToModerator.newInstance(
                                        member.getId_usuario(),
                                        member.getId_comunidad()
                                    ).show(main.getSupportFragmentManager(), null);
                                }
                            }
                        }
                        //===============================================
                    }
                }).start();

            }
        };
    }
}
