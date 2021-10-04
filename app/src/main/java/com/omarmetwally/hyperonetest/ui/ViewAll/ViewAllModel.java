package com.omarmetwally.hyperonetest.ui.ViewAll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.omarmetwally.hyperonetest.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

public class ViewAllModel extends RecyclerView.Adapter<ViewAllModel.ViewHolder> {

    private MutableLiveData<String> mText;
    private ArrayList<ViewAllGetData> content;
    private Context mContext;
    private int id;
    private String type, url, name;


    public ViewAllModel(Context context, ArrayList<ViewAllGetData> contact) {

        content = contact;
        mContext = context;
    }

    /*public LiveData<String> getText() {
        return mText;
    }*/


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.liststyle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        ViewAllGetData data = content.get(position);
        type = data.getType();
        name = data.getName();

        holder.Name.setText(name);
        holder.Type.setText(type);
        holder.Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Doneee : "+position,Toast.LENGTH_SHORT).show();
            }
        });

        if (type.equals("PDF"))
            holder.image.setImageResource(R.drawable.pdf);
        else
            holder.image.setImageResource(R.drawable.video);

    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Name, Type;
        ImageView image;


        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            Name = itemView.findViewById(R.id.name);
            Type = itemView.findViewById(R.id.type);


        }
    }
}