package com.studio71.tourdo.weather;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.studio71.tourdo.R;
import com.studio71.tourdo.navigation.base;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Weather extends AppCompatActivity {

    AutoCompleteTextView autoComplete;
    RequestQueue queue;
    TextView cityName,weatherType,temp;
    ImageView weatherIcon;

    String cityNames;
    String API = "8118ed6ee68db2debfaaa5a44c832918";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        cityName = findViewById(R.id.city);
        weatherType = findViewById(R.id.weatherType);
        temp = findViewById(R.id.temp);
        weatherIcon = findViewById(R.id.weatherIcon);


        autoComplete = findViewById(R.id.autoComplete);
        String[] city = getResources().getStringArray(R.array.city);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,city);
        autoComplete.setAdapter(adapter);

        queue = Volley.newRequestQueue(this);



    }

    public void search(View view) {

        cityNames= autoComplete.getText().toString();

        autoComplete.setText("");

        final String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityNames + "&appid=" + API;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray weatherArray = response.getJSONArray("weather");
                        JSONObject weatherObject = weatherArray.getJSONObject(0);
                        String weatherMain = weatherObject.getString("main");
                        String icon = weatherObject.getString("icon");
                        String iconUrl = "http://openweathermap.org/img/w/" + icon + ".png";

                        weatherType.setText(weatherMain);
                        Picasso.get().load(iconUrl).into(weatherIcon);


                        JSONObject mainObject = response.getJSONObject("main");
                        Double mainTemp = mainObject.getDouble("temp");
                        String currentTempCel = new DecimalFormat("##.##").format((mainTemp-273.15))+"°C";
                        temp.setText(currentTempCel);

                        JSONObject sysObject = response.getJSONObject("sys");
                        String country = sysObject.getString("country");

                        String city = response.getString("name");

                        cityName.setText(city+", "+country);



                    } catch (JSONException e) {
                        Log.e("jsonException", e.toString());
                        Toast.makeText(Weather.this, "Data is not valid!", Toast.LENGTH_SHORT).show();
                    } }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VolleyError", error.toString());
                }
            });

            queue.add(jsonObjectRequest);

    }
}


