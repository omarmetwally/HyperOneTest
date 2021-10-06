package com.omarmetwally.hyperonetest.ui.Downloaded;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.omarmetwally.hyperonetest.R;
import com.omarmetwally.hyperonetest.ui.ViewAll.ViewAllGetData_Model;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class DownloadedFragment extends Fragment {

    SharedPreferences sharedPreferences;
    RecyclerView mreRecyclerView;
    Downloaded_Adapter adapter;
    boolean flag = false;
    private ViewAllGetData_Model contentToSharedPreference;
    private ArrayList<ViewAllGetData_Model> typedata = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_downloaded, container, false);
        sharedPreferences = getContext().getSharedPreferences("shared preferences", MODE_PRIVATE);
        mreRecyclerView = root.findViewById(R.id.downloadedRecyclerView);
        mreRecyclerView.setHasFixedSize(true);
        mreRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        for (int i = 1; i <= 12; i++)//for testing
        {
            loadData(i + "");
        }
        adapter = new Downloaded_Adapter(getContext(), typedata);
        mreRecyclerView.setAdapter(adapter);


        return root;
    }

    private void loadData(String iD) {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(iD, null);
        Type type = new TypeToken<ViewAllGetData_Model>() {
        }.getType();


        contentToSharedPreference = gson.fromJson(json, type);

        if (contentToSharedPreference != null)  //if List is empty
            typedata.add(contentToSharedPreference);


    }

}