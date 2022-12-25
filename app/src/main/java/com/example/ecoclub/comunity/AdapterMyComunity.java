package com.example.ecoclub.comunity;

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

public class AdapterMyComunity extends RecyclerView.Adapter<AdapterMyComunity.ViewHolderData> {

    private FragmentActivity main; //para cambiar de fragments
    private ArrayList<Comunidad> listMyComunity;

    public AdapterMyComunity(ArrayList<Comunidad> listMyComunity, FragmentActivity activity) {
        this.listMyComunity = listMyComunity;
        this.main = activity;
    }

    @NonNull
    @Override
    public AdapterMyComunity.ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //referenciamos el item my comunity
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_my_comunity, null, true);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMyComunity.ViewHolderData holder, int position) {
        holder.cargarDatosItemMyComunity(listMyComunity.get(position), main);
    }

    @Override
    public int getItemCount() {
        return listMyComunity.size();
    }

    //clase View Holder
    public class ViewHolderData extends RecyclerView.ViewHolder {

        private Comunidad itemComunity;

        private TextView nameMyComunity;
        private ViewTransparente btnMyComunity;
        private FragmentActivity main;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            this.nameMyComunity = itemView.findViewById(R.id.idDataMyComunity);
            this.btnMyComunity = itemView.findViewById(R.id.imgMyComunity); //img
        }

        public void cargarDatosItemMyComunity(Comunidad myComunityContent, FragmentActivity main) {
            this.itemComunity = myComunityContent;
            nameMyComunity.setText(myComunityContent.getNombre());
            //evento
            btnMyComunity.setOnClickListener(eventMyComunityDescription);
            this.main = main;
        }

        View.OnClickListener eventMyComunityDescription = new View.OnClickListener() {

            ComunityDescriptionFragment comunityDescriptionFragment;
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Ver Comunidad", Toast.LENGTH_LONG).show();

                comunityDescriptionFragment = ComunityDescriptionFragment.newInstance(
                        String.valueOf(itemComunity.getId()),
                        itemComunity.getNombre(),
                        itemComunity.getInformacion()
                );

                //no lo guardamos en el back stack
                main.getSupportFragmentManager().beginTransaction().setCustomAnimations(
                        R.anim.fade_in,  // enter
                        R.anim.fade_out   // exit
                ).replace(R.id.container, comunityDescriptionFragment)
                        .addToBackStack(null).commit();
            }
        };
    }
}
