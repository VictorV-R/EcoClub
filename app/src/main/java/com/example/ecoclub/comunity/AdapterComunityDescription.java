package com.example.ecoclub.comunity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.ecoclub.dialog.MessageDialogMemberComunity;

import java.util.ArrayList;

public class AdapterComunityDescription extends
        RecyclerView.Adapter<AdapterComunityDescription.ViewHolderData> {

    //lista de miembros
    private ArrayList<Usuario_Comunidad> listMembersComunity;
    private FragmentActivity main;

    public AdapterComunityDescription(ArrayList<Usuario_Comunidad> listMembersComunity,
                                      FragmentActivity activity) {
        this.listMembersComunity = listMembersComunity;
        this.main = activity;
    }

    @NonNull
    @Override
    public AdapterComunityDescription.ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflamos el view y lo retornamos
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comunity_description, null, false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterComunityDescription.ViewHolderData holder, int position) {
        //le enviamos el main al ViewHolder
        holder.cargarDatos(listMembersComunity.get(position));
    }

    @Override
    public int getItemCount() {
        return listMembersComunity.size();
    }

    //clase ViewHolder
    public class ViewHolderData extends RecyclerView.ViewHolder {
        private TextView textNameComunityDescription;

        private CardView btnMember;
        private Usuario_Comunidad member;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            this.textNameComunityDescription = itemView.findViewById(
                    R.id.textNameMemberComunity);
            this.btnMember = itemView.findViewById(
                    R.id.onClickComunityMember);  //CARDVIEW
        }

        public void cargarDatos(Usuario_Comunidad member) {
            this.member = member;

            DbUsuarios dbUsuarios=new DbUsuarios();
            Usuario usuarioInfo=dbUsuarios.obtenerUsuario(member.getId_usuario());

            this.textNameComunityDescription.setText(usuarioInfo.getName());
            //evento-llamamos
            this.btnMember.setOnClickListener(eventMemberComunity);
        }

        //evento=================================================
        //recibiendo el getActivity() desde el fragment
        //y pasandolo por el constructor del adapter
        //=======================================================
        //otra forma de llegar al MainActivity es a traves de un Broadcast
        //aunque se reiniciaria el activity
        View.OnClickListener eventMemberComunity = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //como se tiene el id se puede hacer consultas
                new Thread(new Runnable() {
                    public void run() {
                        MessageDialogMemberComunity.newInstance(
                                        member.getId_usuario(),
                                        member.getId_comunidad())
                                .show(main.getSupportFragmentManager(), null);
                    }
                }).start();
            }
        };
        //=======================================================
    }
}