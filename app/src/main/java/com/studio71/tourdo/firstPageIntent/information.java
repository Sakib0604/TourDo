package com.studio71.tourdo.firstPageIntent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.View;

import com.studio71.tourdo.MainActivity;
import com.studio71.tourdo.R;
import com.studio71.tourdo.navigation.base;

public class information extends base {

    CoordinatorLayout coordinator_layout2;
    Snackbar snackbar;

    //networkcheck
    private information.NetworkChangeReceiver receiver;
    private boolean isConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        coordinator_layout2 = findViewById(R.id.coordinator_layout2);

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
        snackbar.make(coordinator_layout2,"No Internet Connection",Snackbar.LENGTH_LONG).show();
        isConnected = false;
        return false;
    }

}
