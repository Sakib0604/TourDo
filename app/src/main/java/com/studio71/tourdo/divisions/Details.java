package com.studio71.tourdo.divisions;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import com.studio71.tourdo.R;
import com.studio71.tourdo.navigation.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class Details extends base {

    CoordinatorLayout detailsLayout;
    Snackbar snackbar;

    //networkcheck
    private Details.NetworkChangeReceiver receiver;
    private boolean isConnected = false;

    TextView placeName,placeDetails,placePath;
    ImageView placePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailsLayout = findViewById(R.id.detailsLayout);

        placeName = findViewById(R.id.placeName);
        placeDetails = findViewById(R.id.placeDetails);
        placePath = findViewById(R.id.placePath);
        placePic = findViewById(R.id.placePic);

        String name = getIntent().getStringExtra("name");
        String details = getIntent().getStringExtra("details");
        String path = getIntent().getStringExtra("path");
        String pic = getIntent().getStringExtra("pic");

        placeName.setText(name);
        placeDetails.setText(details);
        placePath.setText(path);
        Picasso.get().load(pic).into(placePic);

        //networkcheck
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkChangeReceiver();
        registerReceiver(receiver, filter);

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
        snackbar.make(detailsLayout,"No Internet Connection",Snackbar.LENGTH_LONG).show();
        isConnected = false;
        return false;
    }

}
