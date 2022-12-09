package com.example.ecoclub.comunity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoclub.R;
import com.example.ecoclub.View.Rectangulo;

import java.util.ArrayList;

public class AdapterComunityDescription extends
        RecyclerView.Adapter<AdapterComunityDescription.ViewHolderData> {

    //lista de miembros
    private ArrayList<Member> listMembersComunity;

    public AdapterComunityDescription(ArrayList<Member> listMembersComunity) {
        this.listMembersComunity = listMembersComunity;
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
        holder.cargarDatos(listMembersComunity.get(position));
    }

    @Override
    public int getItemCount() {
        return listMembersComunity.size();
    }

    //clase ViewHolder
    public class ViewHolderData extends RecyclerView.ViewHolder {
        private TextView textNameComunityDescription;
        private int id;
        private Rectangulo btnMember;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            this.textNameComunityDescription = itemView.findViewById(
                    R.id.textNameMemberComunity);
            this.btnMember = itemView.findViewById(
                    R.id.onClickComunityMember);
        }

        public void cargarDatos(Member member) {
            this.id = member.getId();
            this.textNameComunityDescription.setText(member.getNameMember());
            //evento-llamamos al View Rectangulo
            this.btnMember.setOnClickListener(eventMemberComunity);
        }

        //evento
        View.OnClickListener eventMemberComunity = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Ver miembro", Toast.LENGTH_LONG).show();
            }
        };
    }
}
