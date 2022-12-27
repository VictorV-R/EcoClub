package com.example.ecoclub.Activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoclub.Entities.Actividad;
import com.example.ecoclub.R;

import java.util.ArrayList;

public class AdapterActivity extends RecyclerView.Adapter<AdapterActivity.ViewHolderData> {

    private FragmentActivity main; //para cambiar de fragments
    private ArrayList<Actividad> listMisActividades;
    private int id_usuario;

    public AdapterActivity(FragmentActivity main, ArrayList<Actividad> listMisActividades, int id_usuario) {
        this.main = main;
        this.listMisActividades = listMisActividades;
        this.id_usuario = id_usuario;
    }

    @NonNull
    @Override
    public AdapterActivity.ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflamos el view y lo retornamos
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_actividad, null, false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterActivity.ViewHolderData holder, int position) {
        holder.cargarDatos(listMisActividades.get(position));
    }

    @Override
    public int getItemCount() {
        return listMisActividades.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {

        private Actividad item_actividad;
        private TextView nombre;
        private TextView descripcion;
        private Button btnMeInteresaAcividad;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.textViewNombre);
            this.descripcion = itemView.findViewById(R.id.textViewDescripcion);

            this.btnMeInteresaAcividad = itemView.findViewById(R.id.btnMeInteresaActividad);
            this.btnMeInteresaAcividad.setOnClickListener(eventoMeInteresa);
        }

        public void cargarDatos(Actividad actividad) {
            item_actividad = actividad;

            nombre.setText(actividad.getNombre());
            descripcion.setText(actividad.getDescripcion());
        }

        public View.OnClickListener eventoMeInteresa = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btnMeInteresaAcividad.getText().equals("Me interesa")){
                    btnMeInteresaAcividad.setText("No me interesa");
                    Toast.makeText(main, "Te inscribiste a actividad", Toast.LENGTH_SHORT).show();
                }else{
                    btnMeInteresaAcividad.setText("Me interesa");
                    Toast.makeText(main, "Te desinscribiste a actividad", Toast.LENGTH_SHORT).show();
                }
            }

        };
    }
}
