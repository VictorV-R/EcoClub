package com.example.ecoclub.comunity;

import android.content.Intent;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoclub.R;
import com.example.ecoclub.fragments.ComunityDescriptionFragment;

import java.util.ArrayList;

public class AdapterComunity extends RecyclerView.Adapter<AdapterComunity.ViewHolderData> {

    private ArrayList<ComunityContent> listComunity;
    //broadcastReceiver
    protected IntentFilter intentFilter = new IntentFilter("SOME_ACTION_DESCRIPTION_COMUNITY");

    public AdapterComunity(ArrayList<ComunityContent> listComunity) {
        this.listComunity = listComunity;
        //enlazando con el fragmento al que pertenece
    }

    //Enlaza esta clase adaptador con el item(Comunity)
    @Override
    public ViewHolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflamos el view y lo retornamos
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comunity, null, false);
        return new ViewHolderData(view);
    }

    //establece la comunicacion entre el adaptador y la clase ViewHolderData
    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        holder.cargarDatos(this.listComunity.get(position));
    }

    //retorna el tamanio de la lista del item
    @Override
    public int getItemCount() {
        return this.listComunity.size();
    }

    //CLASE ViewHolderData
    public class ViewHolderData extends RecyclerView.ViewHolder {
        //Aqui referenciamos los items del recycler view
        //se juntarana varios item item_comunity

        int id;
        TextView data;
        ImageButton btnAddComunity;
        ImageButton btnComunity;

        //broadcastReceiver
        protected IntentFilter intentFilter = new IntentFilter("SOME_ACTION_DESCRIPTION_COMUNITY");

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            //buscando el item
            data = (TextView) itemView.findViewById(R.id.idDataComunity);
            btnAddComunity = (ImageButton) itemView.findViewById(R.id.imgBtnAddComunity);
            btnComunity = (ImageButton) itemView.findViewById(R.id.imgBtnComunityDescription);
        }

        public void cargarDatos(ComunityContent comunityContent) {
            //editamos los datos de ViewHolder
            id = comunityContent.getId();
            //de la lista(listComunity) solo tomamos el nombre
            data.setText(comunityContent.getName());

            //empezamos asignando los eventos
            btnComunity.setOnClickListener(btnEventComunity);
            btnAddComunity.setOnClickListener(btnEventAddComunity);
        }

        //EVENTOS ITEM==============================================================
        //button Comunity- 8 person
        View.OnClickListener btnEventComunity = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Ver Comunidad", Toast.LENGTH_LONG).show();

                //BroadcastReceiver
                Intent intent = new Intent("SOME_ACTION_DESCRIPTION_COMUNITY");
                intent.putExtra(ComunityDescriptionFragment.DESTINY, String.valueOf(id));
                view.getContext().sendBroadcast(intent);

            }
        };

        //button Add Comunity
        View.OnClickListener btnEventAddComunity = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Unirse a Comunidad", Toast.LENGTH_LONG).show();
                //Todavia no se envio el mensaje al fragment comunity para enviarselo al main
                //si se quiere cambiar de fragment
            }
        };
        //EVENTOS ITEM==============================================================

    }
}
