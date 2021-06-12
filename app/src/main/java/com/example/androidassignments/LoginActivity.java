package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        loadUserData();
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


    private void loadUserData() {
        // gte pref instance
        String preferenceName = getString(R.string.preference_name);
        SharedPreferences myPref = getSharedPreferences(preferenceName, MODE_PRIVATE);

        // load user's email
        String emailKey = getString(R.string.email_key);
        String email_value = myPref.getString(emailKey, "");
        ((EditText) findViewById(R.id.emailEditText)).setText(email_value);

    }

    private void saveUserData(){
        // gte pref instance end editor
        String preferenceName = getString(R.string.preference_name);
        SharedPreferences myPref = getSharedPreferences(preferenceName, MODE_PRIVATE);

        SharedPreferences.Editor myPrefEditor = myPref.edit();
        myPrefEditor.clear();

        // email
        String emailKey = getString(R.string.email_key);
        String emailEntered = ((EditText) findViewById(R.id.emailEditText)).getText().toString();

        // save
        myPrefEditor.putString(emailKey, emailEntered);
        myPrefEditor.apply();

    }


    public void loginClicked(View view) {
        saveUserData();
        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(myIntent);
    }
}