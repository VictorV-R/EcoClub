package com.example.ecoclub.comunity;

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

import java.util.ArrayList;

public class AdapterMyComunity extends RecyclerView.Adapter<AdapterMyComunity.ViewHolderData> {

    private ArrayList<ComunityContent> listMyComunity;

    //broadcast
    private IntentFilter intentFilter = new IntentFilter(
            "SOME_ACTION_DESCRIPTION_MY_COMUNITY");

    public AdapterMyComunity(ArrayList<ComunityContent> listMyComunity) {
        this.listMyComunity = listMyComunity;
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
        holder.cargarDatosItemMyComunity(listMyComunity.get(position));
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

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            nameMyComunity = itemView.findViewById(R.id.idDataMyComunity);
            btnMyComunity = (ImageButton) itemView.findViewById(R.id.imgBtnMyComunityDescription);
        }

        public void cargarDatosItemMyComunity(ComunityContent myComunityContent) {
            id = myComunityContent.getId();
            nameMyComunity.setText(myComunityContent.getName());
            //evento
            btnMyComunity.setOnClickListener(eventMyComunityDescription);
        }

        View.OnClickListener eventMyComunityDescription = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Ver Comunidad", Toast.LENGTH_LONG).show();
            }
        };
    }
}
