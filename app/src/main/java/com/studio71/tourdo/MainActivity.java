package com.studio71.tourdo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.studio71.tourdo.firstPageIntent.bangladeshtour;
import com.studio71.tourdo.firstPageIntent.information;
import com.studio71.tourdo.firstPageIntent.tourPlan;
import com.studio71.tourdo.firstPageIntent.touristPlace;
import com.studio71.tourdo.firstPageIntent.travelBlog;
import com.studio71.tourdo.map.MapsActivity;
import com.studio71.tourdo.navigation.base;
import com.studio71.tourdo.transport.Bus;
import com.studio71.tourdo.transport.Plane;
import com.studio71.tourdo.transport.Train;
import com.studio71.tourdo.transport.launch;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.View;
import android.widget.TextView;

public class MainActivity extends base {

    CoordinatorLayout coordinator_layout;
    Snackbar snackbar;

    TextView bangladeshTour,touristPlace,tourPlan,travelBlog,information,map;

    /*BoomMenuButton bmb ;
    Integer[] imageRes = {R.drawable.launch,R.drawable.bus,R.drawable.train,R.drawable.plane};
    String[] title = {"লঞ্ঘাট","বাস স্ট্যান্ড","রেল ষ্টেশন","বিমান বন্দর"};*/

    //networkcheck
    private NetworkChangeReceiver receiver;
    private boolean isConnected = false;

    //navigation
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //navigation
        drawer_layout=findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawer_layout,R.string.Open,R.string.Close);
        drawer_layout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        coordinator_layout = findViewById(R.id.coordinator_layout);

        //networkcheck
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkChangeReceiver();
        registerReceiver(receiver, filter);

        //banglaFont
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "font/rupali.ttf");

        bangladeshTour = findViewById(R.id.bangladeshTour);
        touristPlace = findViewById(R.id.touristPlace);
        tourPlan = findViewById(R.id.tourPlan);
        travelBlog = findViewById(R.id.travelBlog);
        information = findViewById(R.id.information);
        map = findViewById(R.id.map);

        bangladeshTour.setTypeface(tf);
        touristPlace.setTypeface(tf);
        touristPlace.setTypeface(tf);
        touristPlace.setTypeface(tf);
        touristPlace.setTypeface(tf);
        map.setTypeface(tf);

        //boomButton
        /*bmb = findViewById(R.id.bmb);

        bmb.setButtonEnum(ButtonEnum.TextOutsideCircle);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_4_1);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_4_2);

        for (int i =0 ; i<bmb.getPiecePlaceEnum().pieceNumber(); i++){
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(imageRes[i])
                    .normalText(title[i])
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            if (index == 0){
                                Intent i = new Intent(MainActivity.this, launch.class);
                                startActivity(i);
                            }else if(index == 1){
                                Intent i = new Intent(MainActivity.this, Bus.class);
                                startActivity(i);
                            }else if(index == 2){
                                Intent i = new Intent(MainActivity.this, Train.class);
                                startActivity(i);
                            }else if(index == 3){
                                Intent i = new Intent(MainActivity.this, Plane.class);
                                startActivity(i);
                            }
                        }
                    });
            bmb.addBuilder(builder);
        }*/

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
        snackbar.make(coordinator_layout,"No Internet Connection",Snackbar.LENGTH_LONG).show();
        isConnected = false;
        return false;
    }

    //cardView onClick

    public void bangladeshTour(View view) {
        Intent i = new Intent(this, bangladeshtour.class);
        startActivity(i);
    }

    public void touristPlace(View view) {
        Intent i = new Intent(this, touristPlace.class);
        startActivity(i);
    }

    public void tourPlan(View view) {
        Intent i = new Intent(this, tourPlan.class);
        startActivity(i);
    }

    public void travelBlog(View view) {
        Intent i = new Intent(this, travelBlog.class);
        startActivity(i);
    }

    public void information(View view){
        Intent i = new Intent(this, information.class);
        startActivity(i);
    }

    public void map(View view) {
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }

}
