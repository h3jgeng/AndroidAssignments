package com.example.androidassignments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        Button showListItemsBtn = findViewById(R.id.button);
        showListItemsBtn.setOnClickListener(view -> startActivityForResult(new Intent(MainActivity.this, ListItemsActivity.class), 10));

        Button startChartBtn = findViewById(R.id.button2);
        startChartBtn.setOnClickListener(v -> {
            Log.i(ACTIVITY_NAME, "User clicked Start Chat");
            Intent myIntent = new Intent(MainActivity.this, ChatWindow.class);
            startActivity(myIntent);
        });

        Button starToolBarBtn = findViewById(R.id.button3);
        starToolBarBtn.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, TestToolbar.class);
            startActivity(myIntent);
        });

        Button starWeatherBtn = findViewById(R.id.button4);
        starWeatherBtn.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, WeatherForecast.class);
            startActivity(myIntent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        if (requestCode == 10 && responseCode == RESULT_OK) {
            Log.i(ACTIVITY_NAME, "Returned to MainActivity.onActivityResult");
            String messagePassed = data.getStringExtra("Response");
            Toast myToast = Toast.makeText(MainActivity.this, messagePassed, Toast.LENGTH_LONG);
            myToast.show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "onDestroy()");
    }

}