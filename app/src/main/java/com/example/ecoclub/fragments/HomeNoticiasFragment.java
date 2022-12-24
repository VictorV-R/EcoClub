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
    private RecyclerView recyclerViewofHome;
    private String tema="reciclaje";

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
        ApiUtilities.getApiInterface().getNoticiasPollution(tema,100,api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if(response.isSuccessful())
                {
                    modelClassArrayList.addAll(response.body().getArticles());
                    removeNoticias();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });

    }
    //Noticias q no tienen que ver con reciclaje
    public void removeNoticias(){
        modelClassArrayList.remove(1);
        modelClassArrayList.remove(3);
        modelClassArrayList.remove(3);
        modelClassArrayList.remove(3);
        modelClassArrayList.remove(3);
    }
}