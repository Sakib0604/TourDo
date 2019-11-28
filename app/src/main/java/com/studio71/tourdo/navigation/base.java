package com.studio71.tourdo.navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.studio71.tourdo.R;
import com.studio71.tourdo.firstPageIntent.bangladeshtour;
import com.studio71.tourdo.firstPageIntent.information;
import com.studio71.tourdo.firstPageIntent.tourPlan;
import com.studio71.tourdo.firstPageIntent.touristPlace;
import com.studio71.tourdo.firstPageIntent.travelBlog;
import com.studio71.tourdo.transport.Bus;
import com.studio71.tourdo.transport.Plane;
import com.studio71.tourdo.transport.Train;
import com.studio71.tourdo.transport.launch;
import com.studio71.tourdo.weather.Weather;

public class base extends AppCompatActivity {

    NavigationView navigation_drawer;
    DrawerLayout drawer_layout;
    ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void setContentView(int layoutResID)
    {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);

        drawer_layout=findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawer_layout,R.string.Open,R.string.Close);
        drawer_layout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        navigation_drawer = findViewById(R.id.navigation_drawer);

        navigation_drawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                switch (id){
                    case R.id.bangladeshtour:
                        Intent intent = new Intent(base.this,bangladeshtour.class);
                        startActivity(intent);
                        drawer_layout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.touristplace:
                        Intent intent1 = new Intent(base.this,touristPlace.class);
                        startActivity(intent1);
                        drawer_layout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.tourplan:
                        Intent intent2 = new Intent(base.this,tourPlan.class);
                        startActivity(intent2);
                        drawer_layout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.travelblog:
                        Intent intent3 = new Intent(base.this,travelBlog.class);
                        startActivity(intent3);
                        drawer_layout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.weather:
                        Intent intent4 = new Intent(base.this, Weather.class);
                        startActivity(intent4);
                        drawer_layout.closeDrawer(GravityCompat.START);
                        return true;
                    /*case R.id.bus:
                        Intent intent4 = new Intent(base.this, Bus.class);
                        startActivity(intent4);
                        drawer_layout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.launch:
                        Intent intent5 = new Intent(base.this, launch.class);
                        startActivity(intent5);
                        drawer_layout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.train:
                        Intent intent6 = new Intent(base.this, Train.class);
                        startActivity(intent6);
                        drawer_layout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.plane:
                        Intent intent7 = new Intent(base.this, Plane.class);
                        startActivity(intent7);
                        drawer_layout.closeDrawer(GravityCompat.START);
                        return true;*/
                    case R.id.information:
                        Intent intent8 = new Intent(base.this,information.class);
                        startActivity(intent8);
                        drawer_layout.closeDrawer(GravityCompat.START);
                        return true;
                        default:
                            return false;

                }
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
