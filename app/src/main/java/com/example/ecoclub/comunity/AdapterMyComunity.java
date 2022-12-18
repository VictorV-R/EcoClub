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

import com.example.ecoclub.R;
import com.example.ecoclub.fragments.ComunityDescriptionFragment;

import java.util.ArrayList;

public class AdapterMyComunity extends RecyclerView.Adapter<AdapterMyComunity.ViewHolderData> {

    private FragmentActivity main; //para cambiar de fragments
    private ArrayList<ComunityContent> listMyComunity;

    public AdapterMyComunity(ArrayList<ComunityContent> listMyComunity, FragmentActivity activity) {
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
        private int id;
        private TextView nameMyComunity;
        private ImageButton btnMyComunity;
        private FragmentActivity main;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            nameMyComunity = itemView.findViewById(R.id.idDataMyComunity);
            btnMyComunity = (ImageButton) itemView.findViewById(R.id.imgBtnMyComunityDescription);
        }

        public void cargarDatosItemMyComunity(ComunityContent myComunityContent, FragmentActivity main) {
            id = myComunityContent.getId();
            nameMyComunity.setText(myComunityContent.getName());
            //evento
            btnMyComunity.setOnClickListener(eventMyComunityDescription);
            this.main = main;
        }

        View.OnClickListener eventMyComunityDescription = new View.OnClickListener() {

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

                //no lo guardamos en el back stack
                main.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, comunityDescriptionFragment)
                        .addToBackStack(null).commit();
            }
        };
    }
}
