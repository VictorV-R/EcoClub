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

import com.example.ecoclub.MainActivity;
import com.example.ecoclub.R;
import com.example.ecoclub.comunity.AdapterComunity;
import com.example.ecoclub.comunity.AdapterMyComunity;
import com.example.ecoclub.comunity.ComunityContent;

import java.util.ArrayList;

public class ComunityFragment extends Fragment {

    public static final String DESTINY = "Comunity_Fragment";

    //Recycler View
    private ArrayList<ComunityContent> listComunity;
    private RecyclerView recycler;

    private ImageButton btnSearch;
    private Button btnMyComunity;

    private EditText textComunitySearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comunity, container, false);

        referenciarAdaptador(view);

        //texto a buscar
        textComunitySearch = view.findViewById(R.id.editTextComunitySearch);

        btnSearch = (ImageButton) view.findViewById(R.id.imgBtnSearch);
        btnSearch.setOnClickListener(btnEventSearch);

        btnMyComunity = (Button) view.findViewById(R.id.btnMyComunity);
        btnMyComunity.setOnClickListener(btnEventMyComunity);
        return view;
    }

    //para referenciar el adaptador y relacionarlo con el RecyclerView de Comunidades
    //también se genera la informacion
    private void referenciarAdaptador(View view) {
        //Recycler View=======================
        //Referenciamos el RecyclerView del layout
        recycler = view.findViewById(R.id.idRecyclerComunity);
        //para cargar una lista vertical
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false));

        //llenando datos de comunidad
        llenarDatosComunidades(); //debe reemplazarlo la bd

        //enviamos los datos al adaptador de Comunidad
        //le damos el getActivity para que se pueda cambiar de fragment en el adapter
        AdapterComunity adapter = new AdapterComunity(listComunity, getActivity());
        //por ultimo al recycler le enviamos el adaptador de la Comunidad
        recycler.setAdapter(adapter);
        ///===================================
    }

    //Evento cuando se ejecuta el boton de buscar comunidad
    View.OnClickListener btnEventSearch = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            ArrayList<ComunityContent> listaComBuscados = new ArrayList<>();
            String nombreBuscado = textComunitySearch.getText().toString();

            Toast.makeText(getActivity(), "Buscando Comunidad",
                    Toast.LENGTH_LONG).show();

            //en caso el texto este vacio
            if(nombreBuscado.equalsIgnoreCase("")){
                //enviamos los datos al adaptador de Mis Comunidades
                AdapterComunity adapter = new AdapterComunity(
                        listComunity, getActivity());
                //por ultimo al recycler le enviamos el adaptador de mis comunidades
                recycler.setAdapter(adapter);
            }else{

                //buscando de forma lineal==============================================
                for (int i = 0; i < listComunity.size(); i++){
                    if(nombreBuscado.equalsIgnoreCase(listComunity.get(i).getName()
                            .substring(0,nombreBuscado.length()))){
                        listaComBuscados.add(listComunity.get(i)); //agregandolo a nueva lista
                    }
                }
                //======================================================================

                //en caso de que se encontro
                if (listaComBuscados.size() > 0){
                    //enviamos los datos al adaptador de Clientes
                    AdapterComunity adapterbuscados = new AdapterComunity(
                            listaComBuscados, getActivity());
                    //por ultimo al recycler le enviamos el adaptador de la Clientes
                    recycler.setAdapter(adapterbuscados);

                }else{
                    //Dialogo para confirmar la eliminacion
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("No se encontro la comunidad buscada. ")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss(); //la ventana se cierra
                                }
                            });
                    builder.show();
                }

            }


        }
    };

    //Evento cuando se ejecuta el boton de mis comunidad
    View.OnClickListener btnEventMyComunity = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "Mis Comunidades", Toast.LENGTH_LONG).show();

            //mensaje al main para cambiar al fragment MyComunity
            ((MainActivity) getActivity()).changeFragmentInMain(
                    MyComunityFragment.DESTINY);

        }
    };


    private void llenarDatosComunidades(){
        listComunity = new ArrayList<>();

        ArrayList<String> nombresComunidades = new ArrayList<>();
        nombresComunidades.add("Reciclaje Piura");
        nombresComunidades.add("Ecologia Cayma");
        nombresComunidades.add("Eco-Miraflores");
        nombresComunidades.add("Reciclaje Moquegua");
        nombresComunidades.add("Reciclaje Tumbes");
        nombresComunidades.add("Por un mejor futuro");
        nombresComunidades.add("Ecologistas AQP");
        nombresComunidades.add("Reclicladores");
        nombresComunidades.add("San Pablo-Recicla");
        nombresComunidades.add("Grupo de Reciclaje");
        nombresComunidades.add("Reciclaje Cusco");
        nombresComunidades.add("Reciclaje Tumbes");

        for (int i = 0; i < nombresComunidades.size(); i++){
            listComunity.add(new ComunityContent((i+1),
                    nombresComunidades.get(i),
                    "Descripción de comunidad "+ (i+1)));
        }
    }
}