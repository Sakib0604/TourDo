package com.studio71.tourdo.travelBlog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.studio71.tourdo.R;
import com.studio71.tourdo.divisions.Details;

public class DetailsBlog extends AppCompatActivity {

    CoordinatorLayout blogLayout;
    Snackbar snackbar;

    //networkcheck
    private DetailsBlog.NetworkChangeReceiver receiver;
    private boolean isConnected = false;

    TextView details,name;
    VideoView videoView;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_blog);

        blogLayout = findViewById(R.id.blogLayout);

        details = findViewById(R.id.details);
        name = findViewById(R.id.name);
        videoView = findViewById(R.id.video);
        mediaController = new MediaController(this);

        String blogName = getIntent().getStringExtra("blogName");
        String blogDetails = getIntent().getStringExtra("blogDetails");
        String blogVideo = getIntent().getStringExtra("blogVideo");

        name.setText(blogName);
        details.setText(blogDetails);

        Uri uri = Uri.parse(blogVideo);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setMediaController(mediaController);


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
        snackbar.make(blogLayout,"No Internet Connection",Snackbar.LENGTH_LONG).show();
        isConnected = false;
        return false;
    }

}
