package com.example.ecoclub.comunity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoclub.R;
import com.example.ecoclub.View.Rectangulo;
import com.example.ecoclub.dialog.MessageDialogMemberComunity;

import java.util.ArrayList;

public class AdapterComunityDescription extends
        RecyclerView.Adapter<AdapterComunityDescription.ViewHolderData> {

    //lista de miembros
    private ArrayList<Member> listMembersComunity;
    private FragmentActivity main;

    public AdapterComunityDescription(ArrayList<Member> listMembersComunity,
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
        holder.cargarDatos(listMembersComunity.get(position), main);
    }

    @Override
    public int getItemCount() {
        return listMembersComunity.size();
    }

    //clase ViewHolder
    public class ViewHolderData extends RecyclerView.ViewHolder {
        private TextView textNameComunityDescription;
        private Member member;
        private Rectangulo btnMember;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            this.textNameComunityDescription = itemView.findViewById(
                    R.id.textNameMemberComunity);
            this.btnMember = itemView.findViewById(
                    R.id.onClickComunityMember);
        }

        public void cargarDatos(Member member, FragmentActivity main) {
            this.member = member;
            this.textNameComunityDescription.setText(member.getNameMember());
            //evento-llamamos al View Rectangulo
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
                Toast.makeText(view.getContext(), "Ver miembro", Toast.LENGTH_LONG).show();
                //como se tiene el id se puede hacer consultas
                //por ahora mandamos datos por defecto
                MessageDialogMemberComunity.newInstance(
                                "Miembro de la Comunidad",
                                member.getNameMember(),
                                member.getRango(),
                                member.getLogros()+"")
                        .show(main.getSupportFragmentManager(), null);
            }
        };
        //=======================================================
    }
}
