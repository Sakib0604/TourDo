package com.studio71.tourdo.firstPageIntent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.View;

import com.studio71.tourdo.R;
import com.studio71.tourdo.divisions.BarisalDivision;
import com.studio71.tourdo.divisions.CtgDivision;
import com.studio71.tourdo.divisions.DhakaDivision;
import com.studio71.tourdo.divisions.KhuDivision;
import com.studio71.tourdo.divisions.MymDivision;
import com.studio71.tourdo.divisions.RajDivision;
import com.studio71.tourdo.divisions.RangDivision;
import com.studio71.tourdo.divisions.SyhDivision;
import com.studio71.tourdo.navigation.base;

public class bangladeshtour extends base {

    CoordinatorLayout coordinator_layout1;
    Snackbar snackbar;

    //networkcheck
    private bangladeshtour.NetworkChangeReceiver receiver;
    private boolean isConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangladeshtour);

        coordinator_layout1 = findViewById(R.id.coordinator_layout1);

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
        snackbar.make(coordinator_layout1,"No Internet Connection",Snackbar.LENGTH_LONG).show();
        isConnected = false;
        return false;
    }

    //onclickCardview
    public void dhakaDivision(View view) {
        Intent i = new Intent(this,DhakaDivision.class);
        startActivity(i);
    }

    public void ctgDivision(View view) {
        Intent i = new Intent(this, CtgDivision.class);
        startActivity(i);
    }

    public void rajDivision(View view) {
        Intent i = new Intent(this, RajDivision.class);
        startActivity(i);
    }

    public void khuDivision(View view) {
        Intent i = new Intent(this, KhuDivision.class);
        startActivity(i);
    }

    public void braDivision(View view) {
        Intent i = new Intent(this, BarisalDivision.class);
        startActivity(i);
    }

    public void syhDivision(View view) {
        Intent i = new Intent(this, SyhDivision.class);
        startActivity(i);
    }

    public void rangDivision(View view) {
        Intent i = new Intent(this, RangDivision.class);
        startActivity(i);
    }

    public void mymDivision(View view) {
        Intent i = new Intent(this, MymDivision.class);
        startActivity(i);
    }

}
