package com.example.ecoclub.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionInflater;

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
import com.example.ecoclub.comunity.AdapterOthersComunity;
import com.example.ecoclub.database.DbViewsHelpers;
import com.example.ecoclub.dialog.MessageDialogComunityNotExist;
import com.example.ecoclub.interfaces.MainActivityCallbacks;

import java.util.ArrayList;

public class ComunityOthersFragment extends Fragment {

    public static final String DESTINY = "Comunity_Fragment";

    //Recycler View
    private ArrayList<Comunidad> listComunity;
    private RecyclerView recycler;
    private MainActivityCallbacks mainActivity;
    private ImageButton btnSearch;

    private EditText textComunitySearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fragment saliente
        //TransitionInflater inflater = TransitionInflater.from(requireContext());
        //setExitTransition(inflater.inflateTransition(R.transition.fade_description));

        //fragment entrante
        //setEnterTransition(inflater.inflateTransition(R.transition.slide_rigth_description));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_others_comunity, container, false);

        referenciarAdaptador(view);

        //texto a buscar
        textComunitySearch = view.findViewById(R.id.editTextComunitySearch);

        btnSearch = (ImageButton) view.findViewById(R.id.imgBtnSearch);
        btnSearch.setOnClickListener(btnEventSearch);

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
        llenarDatosComunidades();

        //enviamos los datos al adaptador de Comunidad
        //le damos el getActivity para que se pueda cambiar de fragment en el adapter
        AdapterOthersComunity adapter = new AdapterOthersComunity(listComunity, getActivity(),mainActivity.sendCurrentUserDataFragment().getId());
        //por ultimo al recycler le enviamos el adaptador de la Comunidad
        recycler.setAdapter(adapter);
        ///===================================
    }

    //Evento cuando se ejecuta el boton de buscar comunidad
    //TODO: buscando comunidades
    View.OnClickListener btnEventSearch = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            ArrayList<Comunidad> listaComBuscados = new ArrayList<>();
            String nombreBuscado = textComunitySearch.getText().toString();

            //TODO: buscando comunidades
            //en caso el texto este vacio
            if(nombreBuscado.equalsIgnoreCase("")){
                //enviamos los datos al adaptador de Comunidades
                AdapterOthersComunity adapter = new AdapterOthersComunity(
                        listComunity, getActivity());
                //por ultimo al recycler le enviamos el adaptador de comunidades
                recycler.setAdapter(adapter);
            }else{

                //buscando de forma lineal==============================================
                for (int i = 0; i < listComunity.size(); i++){
                    if(nombreBuscado.equalsIgnoreCase(listComunity.get(i).getNombre()
                            .substring(0,nombreBuscado.length()))){
                        listaComBuscados.add(listComunity.get(i)); //agregandolo a nueva lista
                    }
                }
                //======================================================================

                //en caso de que se encontro
                if (listaComBuscados.size() > 0){
                    //enviamos los datos al adaptador de Comunidades
                    AdapterOthersComunity adapterbuscados = new AdapterOthersComunity(
                            listaComBuscados, getActivity());
                    //por ultimo al recycler le enviamos el adaptador de Comunidades
                    recycler.setAdapter(adapterbuscados);

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

    private void llenarDatosComunidades(){
        /*listComunity = new ArrayList<>();

        ArrayList<Comunidad> nombresComunidades = new ArrayList<>();
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
        }*/

        //DbComunidades dbComunidades=new DbComunidades();
        //listComunity = new ArrayList<>();
        //listComunity=dbComunidades.obtenerComunidades();

        DbViewsHelpers dbViewsHelpers=new DbViewsHelpers();
        listComunity = new ArrayList<>();
        listComunity=dbViewsHelpers.obtenerComunidadesNoAsociadasPorUsuarioID(
                mainActivity.sendCurrentUserDataFragment().getId());
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivityCallbacks){
            mainActivity = (MainActivityCallbacks) context;
        }
    }
}