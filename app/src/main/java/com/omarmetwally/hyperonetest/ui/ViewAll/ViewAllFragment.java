package com.omarmetwally.hyperonetest.ui.ViewAll;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import com.omarmetwally.hyperonetest.Api;
import com.omarmetwally.hyperonetest.R;
import com.omarmetwally.hyperonetest.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

public class ViewAllFragment extends Fragment {


    RecyclerView mreRecyclerView;
    ViewAll_Adapter adapter;
    private Api api;
    private ArrayList<ViewAllGetData_Model> typedata = new ArrayList<>();
    private int id;
    private String type, url, name;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_view_all, container, false);
        mreRecyclerView = root.findViewById(R.id.MycoursesrecyclerView);
        mreRecyclerView.setHasFixedSize(true);
        mreRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mreRecyclerView.setItemViewCacheSize(1000000000);

        RetrofitClient reto = new RetrofitClient();
        api = reto.getApi();
        final Call<List<ViewAllGetData_Model>> call = api.getData();

        call.enqueue(new Callback<List<ViewAllGetData_Model>>() {
            @Override
            public void onResponse(Call<List<ViewAllGetData_Model>> call, Response<List<ViewAllGetData_Model>> response) {

                List<ViewAllGetData_Model> getter = response.body();
                for (ViewAllGetData_Model posts : getter) {
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

                adapter = new ViewAll_Adapter(getContext(), typedata);
                mreRecyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<ViewAllGetData_Model>> call, Throwable t) {
                Toast.makeText(getContext(), "Check your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }
}