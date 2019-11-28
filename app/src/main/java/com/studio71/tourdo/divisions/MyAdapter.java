package com.studio71.tourdo.divisions;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.studio71.tourdo.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Model> models;

    public MyAdapter(Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, final int position) {

        holder.name.setText(models.get(position).getName());
        holder.details.setText(models.get(position).getDetails());

        Picasso.get().load(models.get(position).getPic()).into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Details.class);
                intent.putExtra("name",models.get(position).getName());
                intent.putExtra("details",models.get(position).getDetails());
                intent.putExtra("path",models.get(position).getPath());
                intent.putExtra("pic",models.get(position).getPic());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,details,path;
        ImageView pic;

        public MyViewHolder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.name);
            details = itemView.findViewById(R.id.details);
            path = itemView.findViewById(R.id.path);

            pic = itemView.findViewById(R.id.pic);

        }

    }

}
