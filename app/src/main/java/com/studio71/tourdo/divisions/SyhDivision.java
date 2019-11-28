package com.studio71.tourdo.divisions;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.studio71.tourdo.R;
import com.studio71.tourdo.navigation.base;

import java.util.ArrayList;

public class SyhDivision extends base {

    CoordinatorLayout syhDivision;
    Snackbar snackbar;

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Model> list;
    MyAdapter adapter;

    //networkcheck
    private SyhDivision.NetworkChangeReceiver receiver;
    private boolean isConnected = false;

    //dialog
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syh_division);

        syhDivision = findViewById(R.id.syhDivision);

        recyclerView = findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Model>();

        reference = FirebaseDatabase.getInstance().getReference().child("Sylhet");

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
        snackbar.make(syhDivision,"No Internet Connection",Snackbar.LENGTH_LONG).show();
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

                    Model model = dataSnapshot1.getValue(Model.class);
                    list.add(model);
                    progressDialog.dismiss();

                }

                adapter = new MyAdapter(SyhDivision.this,list);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(SyhDivision.this,"Wrong...",Toast.LENGTH_LONG).show();

            }
        });
    }

}
