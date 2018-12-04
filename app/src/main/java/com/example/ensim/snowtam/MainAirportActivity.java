package com.example.ensim.snowtam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import Models.AirportModels.Airport;
import Models.WeatherModels.Weather;
import Services.GestureSwipe.DetectSwipeGestureListener;
import Services.Tabs.SampleFragmentPagerAdapter;
import Models.SnowtamModels.SnowtamDecode;
import Models.SnowtamModels.SnowtamSingleton;
import Services.WeatherAPI.JSONWeatherParser;
import Services.WeatherAPI.WeatherHttpClient;

public class MainAirportActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private int index;
    private ArrayList<Airport> listAirport;
    private GestureDetectorCompat gestureDetectorCompat = null;
    public ImageView imgView;
    public ImageView left;
    public ImageView right;
    public TextView temp;
    public TextView condDescr;
    public TextView press;
    public TextView windSpeed;
    public TextView windDeg;

    public TextView humi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_airport);

        SnowtamSingleton recup = SnowtamSingleton.getInstance();



        /*GESTURE*/
        DetectSwipeGestureListener gestureListener = new DetectSwipeGestureListener();
        gestureListener.setActivity(this);
        gestureDetectorCompat = new GestureDetectorCompat(this, gestureListener);

        /*TextView*/
        TextView countryName = findViewById(R.id.countryName);
        TextView airportName = findViewById(R.id.mainAirportName);
        TextView longitude = findViewById(R.id.longitude);
        TextView latitude = findViewById(R.id.latitude);
        TextView cityName = findViewById(R.id.cityName);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        condDescr = findViewById(R.id.condDescr);
        temp =  findViewById(R.id.temp);
        humi = findViewById(R.id.hum);
        press = findViewById(R.id.press);
        windSpeed = findViewById(R.id.windSpeed);
        windDeg = findViewById(R.id.windDeg);
        temp = findViewById(R.id.temp);

        /*left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSwipeLeft();
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSwipeRight();
            }
        });*/



        /*Get Intent*/
        listAirport = getIntent().getParcelableArrayListExtra("listAirport");
        index = getIntent().getIntExtra("index",0);

        /*Set Snwotams Raw and Decode*/
        SnowtamDecode decodage = new SnowtamDecode();
        Log.d("GetSet MainAirport :", "Index : " + recup.getIndex() + " et listAirport" + recup.getListAirport());
        recup.setIndex(index);
        recup.setListAirport(listAirport);



        /*Set Text*/
        DecimalFormat df = new DecimalFormat("########.0000000");
        cityName.setText(listAirport.get(index).getCityName() + " - " + listAirport.get(index).getICAO_Code());
        countryName.setText("Country name : "+listAirport.get(index).getCountryName());
        airportName.setText(listAirport.get(index).getName() + " - " + listAirport.get(index).getICAO_Code());
        longitude.setText("long : " + (df.format(listAirport.get(index).getLongitude())));
        latitude.setText("lat : " + (df.format(listAirport.get(index).getLatitude())));

        /*Set onglet (je sais pas comment on dit en anglais)*/
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                MainAirportActivity.this));


        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{""});
        Weather weather = new Weather();




        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        /*Fragment Map*/
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapViewAirport);
        mapFragment.getMapAsync(this);
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }







    //Fonction pour savoir ce qu'on fait en cas de swipe gauche
    public void onSwipeLeft()
    {
        if(index < listAirport.size()-1)
        {
            index++;
            onClick(index, MainAirportActivity.class);
        }
    }

    //Fonction pour savoir ce qu'on fait en cas de swipe droit
    public void onSwipeRight()
    {
        if(index > 0)
        {
            index--;
            onClick(index, MainAirportActivity.class);
        }
    }


    //Fonction pour afficher la MAP
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        // Add a marker in Sydney and move the camera
        final LatLng airport = new LatLng(listAirport.get(index).getLatitude(), listAirport.get(index).getLongitude());
        mMap.addMarker(new MarkerOptions().position(airport).title(listAirport.get(index).getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(airport));
        mMap.setMinZoomPreference(15.0f);
        mMap.setMaxZoomPreference(16.0f);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                onClick(index, MapsActivity.class);
            }
        });

    }

    //Fonction pour le reperage du swipe
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Pass activity on touch event to the gesture detector.
        gestureDetectorCompat.onTouchEvent(event);
        // Return true to tell android OS that event has been consumed, do not pass it to other event listeners.
        return true;
    }

    public void onClick(int num, Class activity)
    {
        Intent i = new Intent(MainAirportActivity.this, activity);
        i.putExtra("index", num);
        i.putExtra("listAirport", listAirport);
        startActivity(i);
        if(activity != MapsActivity.class)
            finish();
    }


    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            SnowtamSingleton recup = SnowtamSingleton.getInstance();
            Weather weather = new Weather();
            String data = ( (new WeatherHttpClient()).getWeatherData(params[0]));

            try {
                weather = JSONWeatherParser.getWeather(data);

                // Let's retrieve the icon
                weather.iconData = ( (new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));
                Log.d("MAIN AIRPORT ICON CODE", recup.getIcon());
                String url = "http://openweathermap.org/img/w/" + recup.getIcon() + ".png";
                new DownloadImageTask((ImageView) findViewById(R.id.condIcon)).execute(url);
                Log.d("MAIN AIRPORT URL ICON", url);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;

        }




        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            if (weather.iconData != null && weather.iconData.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
                imgView.setImageBitmap(img);
            }

            Log.d("Temperature", Math.round((weather.temperature.getTemp() - 273.15)) + "°C");

            DecimalFormat df = new DecimalFormat("##.0");
            //citytext.setText(weather.location.getCity() + "," + weather.location.getCountry());
            condDescr.setText(weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescr() + ")");
            temp.setText("Temperature : " + df.format((weather.temperature.getTemp() - 273.15)) + "°C");
            humi.setText("Humidity : " + weather.currentCondition.getHumidity() + "%");
            press.setText("Pression : " + weather.currentCondition.getPressure() + " hPa");
            windSpeed.setText("Wind speed : " + weather.wind.getSpeed() + " mps");
            windDeg.setText("Wind deg : " + weather.wind.getDeg() + "°");

        }







    }
}