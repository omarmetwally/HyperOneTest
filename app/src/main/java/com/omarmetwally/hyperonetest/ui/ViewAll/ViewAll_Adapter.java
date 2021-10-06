package com.omarmetwally.hyperonetest.ui.ViewAll;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.omarmetwally.hyperonetest.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


public class ViewAll_Adapter extends RecyclerView.Adapter<ViewAll_Adapter.ViewHolder> {

    DownloadManager.Request request;
    private ArrayList<ViewAllGetData_Model> content;
    private ViewAllGetData_Model contentToSharedPreference;
    private Context mContext;
    private String type, url, name;


    public ViewAll_Adapter(Context context, ArrayList<ViewAllGetData_Model> contact) {

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        ViewAllGetData_Model data = content.get(position);
        type = data.getType();
        name = data.getName();
        holder.Name.setText(data.getName() + "");
        Uri ui = Uri.parse(data.getUrl().trim());


        holder.downImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contentToSharedPreference = content.get(position);
                final Handler handler = new Handler();
                if (download(ui)) {
                    holder.downImage.setClickable(false);
                    saveData();
                    Runnable task = new Runnable() {
                        @Override
                        public void run() {
                            // Log.d("LOG", holder.pr.getProgress()+"");


                            if (holder.pr.getProgress() != 100) {
                                holder.pr.incrementProgressBy(data.getId());
                                holder.progBar.setText(holder.pr.getProgress() + "%");
                            } else {
                                holder.downImage.setImageResource(R.drawable.donedownload);
                                holder.progBar.setText(100 + "%");
                            }

                            handler.postDelayed(this, 1000L);
                        }
                    };
                    handler.post(task);

                }

            }

        });


        if (loadData(data.getId() + "")) {
            holder.downImage.setImageResource(R.drawable.donedownload);
            holder.downImage.setClickable(false);
            holder.pr.setProgress(100);
            holder.progBar.setText(100 + "%");

        }

        if (type.equals("PDF"))

            holder.image.setImageResource(R.drawable.pdf);
        else
            holder.image.setImageResource(R.drawable.video);

    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public boolean download(Uri ui) {

        try {
            request = new DownloadManager.Request(ui);
            request.setTitle("download");
            request.setDestinationInExternalFilesDir(mContext, Environment.DIRECTORY_DOWNLOADS, " ");
            DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
            downloadManager.enqueue(request);
            return true;
        } catch (Exception ex) {
            Toast.makeText(mContext, "Check the URL path", Toast.LENGTH_LONG).show();

            return false;
        }


    }

    private boolean loadData(String iD) {

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("shared preferences", mContext.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(iD, null);
        if (json != null)
            return true;

        Type type = new TypeToken<ViewAllGetData_Model>() {
        }.getType();//for casting json to my List Structure
        contentToSharedPreference = gson.fromJson(json, type);

        return false;
    }

    private void saveData() {

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("shared preferences", mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        String json = gson.toJson(contentToSharedPreference);
        editor.putString(contentToSharedPreference.getId() + "", json);
        editor.apply();

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