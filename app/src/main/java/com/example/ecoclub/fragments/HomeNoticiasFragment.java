package com.example.ecoclub.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ecoclub.R;
import com.example.ecoclub.news.Adapter;
import com.example.ecoclub.news.ApiUtilities;
import com.example.ecoclub.news.NewsShowClass;
import com.example.ecoclub.news.mainNews;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeNoticiasFragment extends Fragment {
    String api="470ef48c77514e4b82c3ffbda421c1ef";
    ArrayList<NewsShowClass> modelClassArrayList;
    Adapter adapter;
    String country="in";
    private RecyclerView recyclerViewofHome;
    private String category="science";
    String filtros[]={"plastic","environment","nature","pollution","contamination","ecology"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_home_noticias, container, false);

        recyclerViewofHome=v.findViewById(R.id.recycleviewofHome);
        modelClassArrayList=new ArrayList<>();
        recyclerViewofHome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter= new Adapter(getContext(), modelClassArrayList);
        recyclerViewofHome.setAdapter(adapter);
        findNews();

        return v;
    }

    private void findNews() {
        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if(response.isSuccessful())
                {
                    ArrayList<NewsShowClass> listaNoticias=response.body().getArticles();
                    Log.d("INFO",response.body().getTotalResults());
                    for (NewsShowClass aux:listaNoticias) {
                        Log.d("INFO","-");
                        if(contienePalabrasAmbiente(aux.getTitle()) || contienePalabrasAmbiente(aux.getDescription())){
                            modelClassArrayList.add(aux);
                        }
                    }
                    //modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });
        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if(response.isSuccessful())
                {
                    ArrayList<NewsShowClass> listaNoticias=response.body().getArticles();
                    Log.d("INFO",response.body().getTotalResults());
                    for (NewsShowClass aux:listaNoticias) {
                        Log.d("INFO","-");
                        if(contienePalabrasAmbiente(aux.getTitle()) || contienePalabrasAmbiente(aux.getDescription())){
                            modelClassArrayList.add(aux);
                        }
                    }
                    //modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });

    }
    private boolean contienePalabrasAmbiente(String texto) {
        if (texto == null ) {
            return false;
        } else {
            texto = texto.toLowerCase();
            Log.d("INFO",texto);
            for (int i = 0; i < filtros.length; i++) {
                if (texto.contains(filtros[i])) {
                    return true;
                }
            }
            return false;
        }
    }
}