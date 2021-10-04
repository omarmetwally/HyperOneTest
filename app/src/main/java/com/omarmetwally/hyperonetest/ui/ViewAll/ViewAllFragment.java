package com.omarmetwally.hyperonetest.ui.ViewAll;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import com.omarmetwally.hyperonetest.Api;
import com.omarmetwally.hyperonetest.R;
import com.omarmetwally.hyperonetest.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

public class ViewAllFragment extends Fragment {


    private Api api;
    private ArrayList<ViewAllGetData> typedata = new ArrayList<>();
    private int id;
    private String type, url, name;
    RecyclerView mreRecyclerView;
    ViewAllModel adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        mreRecyclerView=root.findViewById(R.id.MycoursesrecyclerView);
        mreRecyclerView.setHasFixedSize(true);
        mreRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        RetrofitClient reto = new RetrofitClient();

       /* Retrofit reto = new Retrofit.Builder()
                .baseUrl("https://elsayedmustafa.github.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
*/


        api = reto.getApi();
        final Call<List<ViewAllGetData>> call = api.getData();

        call.enqueue(new Callback<List<ViewAllGetData>>() {
            @Override
            public void onResponse(Call<List<ViewAllGetData>> call, Response<List<ViewAllGetData>> response) {

                List<ViewAllGetData> getter = response.body();


                for (ViewAllGetData posts : getter) {
                    id = posts.getId();
                    posts.setId(id);

                    type = posts.getType();
                    posts.setType(type);

                    url = posts.getUrl();
                    posts.setUrl(url);

                    name = posts.getName();
                    posts.setName(name);

                    typedata.add(posts);
                }

                adapter=new ViewAllModel(getContext(),typedata);
                mreRecyclerView.setAdapter(adapter);
                // Toast.makeText(getContext(),id,Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<List<ViewAllGetData>> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();

            }
        });


        return root;
    }
}