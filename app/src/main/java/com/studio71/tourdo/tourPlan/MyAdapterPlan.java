package com.studio71.tourdo.tourPlan;

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

public class MyAdapterPlan extends RecyclerView.Adapter<MyAdapterPlan.MyViewHolder> {

    Context context;
    ArrayList<ModelPlan> models;

    public MyAdapterPlan(Context context, ArrayList<ModelPlan> models) {
        this.context = context;
        this.models = models;
    }


    @NonNull
    @Override
    public MyAdapterPlan.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.tourplancard, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterPlan.MyViewHolder holder, final int position) {

        holder.tourName.setText(models.get(position).getName());
        holder.spots.setText(models.get(position).getSpot());
        holder.expenses.setText(models.get(position).getExpense());

        Picasso.get().load(models.get(position).getPic()).into(holder.tourPic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailsPlan.class);
                intent.putExtra("tourPic",models.get(position).getPic());
                intent.putExtra("tourName",models.get(position).getName());
                intent.putExtra("spots",models.get(position).getSpot());
                intent.putExtra("expense",models.get(position).getExpense());
                intent.putExtra("transport",models.get(position).getTransport());
                intent.putExtra("firstDay",models.get(position).getFirstDay());
                intent.putExtra("secondDay",models.get(position).getSecondDay());
                intent.putExtra("thirdDay",models.get(position).getThirdDay());
                intent.putExtra("fourthDay",models.get(position).getFourthDay());
                intent.putExtra("fifthDay",models.get(position).getFifthDay());
                intent.putExtra("sixthDay",models.get(position).getSixthDay());
                intent.putExtra("seventhDay",models.get(position).getSeventhDay());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tourName,spots,expenses,transports,first,second,third,fourth,fifth,sixth,seventh;
        ImageView tourPic;

        public MyViewHolder(View itemview){
            super(itemview);

            tourPic = itemview.findViewById(R.id.tourPic);
            tourName = itemview.findViewById(R.id.tourName);
            spots = itemview.findViewById(R.id.spots);
            expenses = itemview.findViewById(R.id.expenses);
            transports = itemview.findViewById(R.id.transports);
            first = itemview.findViewById(R.id.first);
            second = itemview.findViewById(R.id.second);
            third = itemview.findViewById(R.id.third);
            fourth = itemview.findViewById(R.id.fourth);
            fifth = itemview.findViewById(R.id.fifth);
            sixth = itemview.findViewById(R.id.sixth);
            seventh = itemview.findViewById(R.id.seventh);

        }
    }
}
