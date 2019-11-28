package com.studio71.tourdo.travelBlog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.studio71.tourdo.R;

import java.util.ArrayList;

public class MyAdapterBlog extends RecyclerView.Adapter<MyAdapterBlog.MyViewHolder> {

    Context context;
    ArrayList<ModelBlog> models;

    public MyAdapterBlog(Context context, ArrayList<ModelBlog> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.blogcard,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.name.setText(models.get(position).getName());
        holder.details.setText(models.get(position).getDetails());
        Picasso.get().load(models.get(position).getPic()).into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailsBlog.class);

                intent.putExtra("blogName",models.get(position).getName());
                intent.putExtra("blogDetails",models.get(position).getDetails());
                intent.putExtra("blogVideo",models.get(position).getVideo());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView pic;
        VideoView video;
        TextView name,details;

        public MyViewHolder(View itemView){

            super(itemView);

            pic = itemView.findViewById(R.id.pic);
            name = itemView.findViewById(R.id.name);
            details = itemView.findViewById(R.id.details);
            video = itemView.findViewById(R.id.video);

        }

    }
}
