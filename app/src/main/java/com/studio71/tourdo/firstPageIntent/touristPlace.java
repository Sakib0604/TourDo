package com.studio71.tourdo.firstPageIntent;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.studio71.tourdo.MainActivity;
import com.studio71.tourdo.R;
import com.studio71.tourdo.divisions.BarisalDivision;
import com.studio71.tourdo.divisions.Model;
import com.studio71.tourdo.divisions.MyAdapter;
import com.studio71.tourdo.navigation.base;
import com.studio71.tourdo.touristPlace.ModelPlace;
import com.studio71.tourdo.touristPlace.MyAdapterPlace;

import java.util.ArrayList;

public class touristPlace extends base {

    CoordinatorLayout coordinator_layout3;
    Snackbar snackbar;

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<ModelPlace> list;
    MyAdapterPlace adapter;

    //networkcheck
    private touristPlace.NetworkChangeReceiver receiver;
    private boolean isConnected = false;

    //dialog
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_place);

        coordinator_layout3 = findViewById(R.id.coordinator_layout3);

        recyclerView = findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<ModelPlace>();

        reference = FirebaseDatabase.getInstance().getReference().child("Barisal");

        //networkcheck
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkChangeReceiver();
        registerReceiver(receiver, filter);

        //dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

    }
    //networkCheck
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, final Intent intent) {
            isNetworkAvailable(context);
        }
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        if(!isConnected){
                            /*Toast.makeText(this,"Now you are connected to Internet!",Toast.LENGTH_LONG).show();*/
                            /*snackbar.make(coordinator_layout,"Now you are connected to Internet!",Snackbar.LENGTH_LONG).show();*/
                            isConnected = true;
                        }
                        return true;
                    }
                }
            }
        }
        /*Toast.makeText(this,"You are not connected to Internet!",Toast.LENGTH_LONG).show();*/
        snackbar.make(coordinator_layout3,"No Internet Connection",Snackbar.LENGTH_LONG).show();
        isConnected = false;
        return false;
    }

    //database
    @Override
    protected void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();


                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    ModelPlace modelPlace = dataSnapshot1.getValue(ModelPlace.class);
                    list.add(modelPlace);
                    progressDialog.dismiss();

                }

                adapter = new MyAdapterPlace(touristPlace.this,list);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(touristPlace.this,"Wrong...",Toast.LENGTH_LONG).show();

            }
        });
    }


}
