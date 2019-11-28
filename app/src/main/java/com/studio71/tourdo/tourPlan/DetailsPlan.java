package com.studio71.tourdo.tourPlan;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studio71.tourdo.R;
import com.studio71.tourdo.divisions.Details;

public class DetailsPlan extends AppCompatActivity {

    CoordinatorLayout detailsPlan;
    Snackbar snackbar;

    TextView nameTour,spotTour,tourExpense,tourTransport,one,two,three,four,five,six,seven;
    ImageView picTour;

    //networkcheck
    private DetailsPlan.NetworkChangeReceiver receiver;
    private boolean isConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_plan);

        detailsPlan = findViewById(R.id.detailsPlan);

        nameTour = findViewById(R.id.nameTour);
        spotTour = findViewById(R.id.spotTour);
        tourExpense = findViewById(R.id.tourExpense);
        tourTransport = findViewById(R.id.tourTransport);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        picTour = findViewById(R.id.picTour);

        String tourPic = getIntent().getStringExtra("tourPic");
        String tourName = getIntent().getStringExtra("tourName");
        String spots= getIntent().getStringExtra("spots");
        String expense = getIntent().getStringExtra("expense");
        String transport = getIntent().getStringExtra("transport");
        String firstDay = getIntent().getStringExtra("firstDay");
        String secondDay = getIntent().getStringExtra("secondDay");
        String thirdDay = getIntent().getStringExtra("thirdDay");
        String fourthDay = getIntent().getStringExtra("fourthDay");
        String fifthDay = getIntent().getStringExtra("fifthDay");
        String sixthDay = getIntent().getStringExtra("sixthDay");
        String seventhDay = getIntent().getStringExtra("seventhDay");

        Picasso.get().load(tourPic).into(picTour);

        nameTour.setText(tourName);
        spotTour.setText(spots);
        tourExpense.setText(expense);
        tourTransport.setText(transport);
        one.setText(firstDay);
        two.setText(secondDay);
        three.setText(thirdDay);
        four.setText(fourthDay);
        five.setText(fifthDay);
        six.setText(sixthDay);
        seven.setText(seventhDay);

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
        snackbar.make(detailsPlan,"No Internet Connection",Snackbar.LENGTH_LONG).show();
        isConnected = false;
        return false;
    }

}
