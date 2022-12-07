package com.example.ecoclub.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoclub.R;

import java.util.ArrayList;

public class AdapterComunity extends RecyclerView.Adapter<AdapterComunity.ViewHolderData> {

    private ArrayList<String> listNameComunity;

    public AdapterComunity(ArrayList<String> listNameComunity) {
        this.listNameComunity = listNameComunity;
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
        //creamos un metodo en la clase ViewHolderData
        holder.asignarDatos(this.listNameComunity.get(position));
    }

    //retorna el tamanio de la lista del item
    @Override
    public int getItemCount() {
        return this.listNameComunity.size();
    }

    //clase ViewHolderData
    public class ViewHolderData extends RecyclerView.ViewHolder {
        //Aqui referenciamos los items del recycler view
        //se juntarana varios item item_comunity

        TextView data;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            //buscando el item
            data = (TextView) itemView.findViewById(R.id.idDataComunity);
        }

        //asignamos el string que llega del adaptador
        public void asignarDatos(String s) {
            data.setText(s);
        }
    }
}
