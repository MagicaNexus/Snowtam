package com.example.ensim.snowtam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        final ArrayList<String> airports = getIntent().getStringArrayListExtra("airports");



        Log.d("AIRPORTSSSSSS", String.valueOf(airports));
    }
}
