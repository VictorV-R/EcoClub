package com.example.ecoclub.comunity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoclub.R;
import com.example.ecoclub.View.ViewTransparente;
import com.example.ecoclub.fragments.ComunityDescriptionFragment;

import java.util.ArrayList;

public class AdapterComunity extends RecyclerView.Adapter<AdapterComunity.ViewHolderData> {

    private FragmentActivity main; //para cambiar de fragments
    private ArrayList<ComunityContent> listComunity;

    public AdapterComunity(ArrayList<ComunityContent> listComunity, FragmentActivity activity) {
        this.listComunity = listComunity;
        this.main = activity;

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
        holder.cargarDatos(this.listComunity.get(position), this.main);
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

        private int id;
        private TextView data;
        private ImageButton btnAddComunity;
        private ViewTransparente btnComunity;
        private FragmentActivity main; //para cambiar de fragments

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            //buscando el item
            data = (TextView) itemView.findViewById(R.id.idDataComunity);
            btnAddComunity = (ImageButton) itemView.findViewById(R.id.imgBtnAddComunity);
            btnComunity = itemView.findViewById(R.id.imgComunity);
        }

        public void cargarDatos(ComunityContent comunityContent, FragmentActivity main) {
            //editamos los datos de ViewHolder
            id = comunityContent.getId();
            //de la lista(listComunity) solo tomamos el nombre
            data.setText(comunityContent.getName());

            //empezamos asignando los eventos
            btnComunity.setOnClickListener(btnEventComunity);
            btnAddComunity.setOnClickListener(btnEventAddComunity);

            //pasando referencia de activity
            this.main = main;
        }

        //EVENTOS ITEM==============================================================
        //button Comunity- 8 person
        View.OnClickListener btnEventComunity = new View.OnClickListener() {

            ComunityDescriptionFragment comunityDescriptionFragment;

            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Ver Comunidad", Toast.LENGTH_LONG).show();

                comunityDescriptionFragment = new ComunityDescriptionFragment();
                //enviamos id al fragment.............
                Bundle enviarMensaje = new Bundle();
                enviarMensaje.putString("id", String.valueOf(id));
                comunityDescriptionFragment.setArguments(enviarMensaje);
                //...................................

                //lo guardamos en el back stack
                main.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, comunityDescriptionFragment)
                        .addToBackStack(null).commit();
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
