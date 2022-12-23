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

import com.example.ecoclub.Entities.Comunidad;
import com.example.ecoclub.R;
import com.example.ecoclub.View.ViewTransparente;
import com.example.ecoclub.fragments.ComunityDescriptionFragment;

import java.util.ArrayList;

public class AdapterComunity extends RecyclerView.Adapter<AdapterComunity.ViewHolderData> {

    private FragmentActivity main; //para cambiar de fragments
    private ComunityDescriptionFragment comuDescFrag;
    private ArrayList<Comunidad> listComunity;

    public AdapterComunity(ArrayList<Comunidad> listComunity, FragmentActivity activity) {
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

        private Comunidad itemComunity;

        private TextView data;
        private ImageButton btnAddComunity;
        private ViewTransparente btnComunity;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            //buscando el item
            data = (TextView) itemView.findViewById(R.id.idDataComunity);
            btnAddComunity = (ImageButton) itemView.findViewById(R.id.imgBtnAddComunity);
            btnComunity = itemView.findViewById(R.id.imgComunity);
        }

        public void cargarDatos(Comunidad comunityContent) {
            //editamos los datos de ViewHolder
            this.itemComunity = comunityContent;
            //de la lista(listComunity) solo tomamos el nombre
            data.setText(comunityContent.getNombre());

            //empezamos asignando los eventos
            btnComunity.setOnClickListener(btnEventComunity);
            btnAddComunity.setOnClickListener(btnEventAddComunity);
        }

        //EVENTOS ITEM==============================================================
        //button Comunity- 8 person
        View.OnClickListener btnEventComunity = new View.OnClickListener() {

            ComunityDescriptionFragment comunityDescriptionFragment;

            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Ver Comunidad", Toast.LENGTH_LONG).show();

                //tenemos que llamar newInstacne siempre para la descripcion de un fragment
                comunityDescriptionFragment = ComunityDescriptionFragment.newInstance(
                        String.valueOf(itemComunity.getId()), //id
                        itemComunity.getNombre(),                //name
                        itemComunity.getInformacion()
                );

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

                //Todo: unir a usuario en la comunidad en la base de datos====
                //Todo: AQUI LE DAMOS SOLO EL RANGO DE MIEMBRO para que habra
                //Todo: la interface de miembro en la descripcion de comunidad
                //Todo:========================================================
                comuDescFrag = ComunityDescriptionFragment.newInstance(
                        String.valueOf(itemComunity.getId()),
                        itemComunity.getNombre(),
                        itemComunity.getInformacion()
                );
                //cambiando de fragment
                main.getSupportFragmentManager().beginTransaction().replace(
                        R.id.container, comuDescFrag).addToBackStack(null).commit();


            }
        };
        //EVENTOS ITEM==============================================================

    }
}
