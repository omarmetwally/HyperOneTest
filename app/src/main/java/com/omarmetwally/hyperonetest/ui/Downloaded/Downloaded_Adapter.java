package com.omarmetwally.hyperonetest.ui.Downloaded;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.omarmetwally.hyperonetest.R;
import com.omarmetwally.hyperonetest.ui.ViewAll.ViewAllGetData_Model;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Downloaded_Adapter extends RecyclerView.Adapter<Downloaded_Adapter.ViewHolder> {

    private ArrayList<ViewAllGetData_Model> content;

    private Context mContext;


    public Downloaded_Adapter(Context context, ArrayList<ViewAllGetData_Model> contact) {
        content = contact;
        mContext = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.liststyle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewAllGetData_Model data = content.get(position);


        holder.Name.setText(data.getName());
        holder.downImage.setVisibility(View.INVISIBLE);

        if (data.getType().equals("PDF"))

            holder.image.setImageResource(R.drawable.pdf);
        else
            holder.image.setImageResource(R.drawable.video);

    }

    @Override
    public int getItemCount() {
        return content.size(); //number for testing
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Name, progBar;
        ImageView image, downImage;
        ProgressBar pr;


        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            Name = itemView.findViewById(R.id.name);

            pr = itemView.findViewById(R.id.progress_bar);
            progBar = itemView.findViewById(R.id.text_view_progress);
            downImage = itemView.findViewById(R.id.down_imageView2);


        }
    }
}