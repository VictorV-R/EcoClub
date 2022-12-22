package com.example.ecoclub.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ecoclub.Entities.Comunidad;
import com.example.ecoclub.MainActivity;
import com.example.ecoclub.R;
import com.example.ecoclub.comunity.AdapterMyComunity;
import com.example.ecoclub.database.DbComunidades;
import com.example.ecoclub.dialog.MessageDialogComunityNotExist;
import com.example.ecoclub.dialog.MessageDialogQuit;

import java.util.ArrayList;

public class MyComunityFragment extends Fragment {

    public static final String DESTINY = "My Comunity";

    private ArrayList<Comunidad> listMyComunity;

    private RecyclerView recyclerMyComunity;
    private ImageButton btnSearchMyComunity;
    private Button btnAtras;

    EditText textMyComunitySearch;

    public MyComunityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_comunity, container, false);

        //texto a buscar
        textMyComunitySearch = view.findViewById(R.id.editTextMyComunity);

        btnSearchMyComunity = view.findViewById(R.id.imgBtnSearchMyComunity);
        btnSearchMyComunity.setOnClickListener(eventSearchMyComunity);

        btnAtras = view.findViewById(R.id.btnAtrasMyComunity);
        btnAtras.setOnClickListener(eventAtras);

        referenciarAdaptadorMyComunity(view);
        return view;
    }

    private void referenciarAdaptadorMyComunity(View view) {
        recyclerMyComunity = view.findViewById(R.id.idRecyclerMyComunity);
        //para cargar una lista vertical
        recyclerMyComunity.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false));

        //llenando datos de comunidad
        llenarDatosComunidades();

        //enviamos la data al adaptador
        //le damos el getActivity para que se pueda cambiar de fragment en el adapter
        AdapterMyComunity adapterMyComunity = new AdapterMyComunity(listMyComunity, getActivity());
        //por ultimo al recycler le enviamos el adaptador de mi Comunidad
        recyclerMyComunity.setAdapter(adapterMyComunity);
    }

    //eventos
    //TODO: buscando mis comunidades
    private View.OnClickListener eventSearchMyComunity = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            ArrayList<Comunidad> listaMisComBuscados = new ArrayList<>();
            String nombreBuscado = textMyComunitySearch.getText().toString();

            //en caso el texto este vacio
            if(nombreBuscado.equalsIgnoreCase("")){
                //enviamos los datos al adaptador de Mis Comunidades
                AdapterMyComunity adapter = new AdapterMyComunity(
                        listMyComunity, getActivity());
                //por ultimo al recycler le enviamos el adaptador de mis comunidades
                recyclerMyComunity.setAdapter(adapter);
            }else{

                //buscando de forma lineal==============================================
                for (int i = 0; i < listMyComunity.size(); i++){
                    if(nombreBuscado.equalsIgnoreCase(listMyComunity.get(i).getNombre()
                            .substring(0,nombreBuscado.length()))){
                        listaMisComBuscados.add(listMyComunity.get(i)); //agregandolo a nueva lista
                    }
                }
                //======================================================================

                //en caso de que se encontro
                if (listaMisComBuscados.size() > 0){
                    //enviamos los datos al adaptador de mis Comunidades
                    AdapterMyComunity adapterbuscados = new AdapterMyComunity(
                            listaMisComBuscados, getActivity());
                    //por ultimo al recycler le enviamos el adaptador de mis Comunidades
                    recyclerMyComunity.setAdapter(adapterbuscados);

                }else{
                    //Dialogo para confirmar que no se encontro comunidad======
                    MessageDialogComunityNotExist dialogComunityNotExist =
                            new MessageDialogComunityNotExist();
                    //mostramos mensaje emergente
                    dialogComunityNotExist.show(
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction(),null);
                    //=========================================================
                }

            }


        }
    };


    private View.OnClickListener eventAtras = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ((MainActivity) getActivity()).changeFragmentInMain(
                    ComunityFragment.DESTINY);
        }
    };

    private void llenarDatosComunidades(){
        /*listMyComunity = new ArrayList<>();

        ArrayList<String> nombresComunidades = new ArrayList<>();
        nombresComunidades.add("Reciclaje AQP");
        nombresComunidades.add("Reciclaje Perú");
        nombresComunidades.add("Reciclaje AQP");
        nombresComunidades.add("Recuperando el ambiente");
        nombresComunidades.add("Ecologistas en acción");
        nombresComunidades.add("Reclicladores");
        nombresComunidades.add("Independencia-Recicla");
        nombresComunidades.add("San Pedro-Reciclaje");
        nombresComunidades.add("Reciclaje Cerro Colorado");
        nombresComunidades.add("Reciclaje Socabaya");

        for (int i = 0; i < nombresComunidades.size(); i++){
            listMyComunity.add(new ComunityContent((i+1),
                    nombresComunidades.get(i),
                    "Descripción de comunidad "+ (i+1)));
        }*/
        DbComunidades dbComunidades=new DbComunidades();
        listMyComunity = new ArrayList<>();
        //Falta 1 metodo aldair
        listMyComunity=dbComunidades.obtenerComunidades();
    }
}