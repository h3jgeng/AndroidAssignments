package com.example.androidassignments;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "ListItemsActivity";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    int shortDuration = Toast.LENGTH_SHORT;
    int longDuration = Toast.LENGTH_LONG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        ImageButton imageBtn = findViewById(R.id.imageButton);
        imageBtn.setOnClickListener(v -> startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_IMAGE_CAPTURE));

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch mySwitch = (Switch) findViewById(R.id.onOffSwitch);
        mySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Toast myToast;
            if (isChecked) {
                myToast = Toast.makeText(ListItemsActivity.this, R.string.switch_on, shortDuration);
            }else{
                myToast = Toast.makeText(ListItemsActivity.this, R.string.switch_off, longDuration);
            }
            myToast.show();
        });

        CheckBox myCheckBox = (CheckBox) findViewById(R.id.logoutCheckBox);
        myCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
            builder.setMessage(R.string.dialog_message);
            builder.setTitle(R.string.dialog_title);
            builder.setPositiveButton(R.string.action_oK, (dialog, which) -> {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("Response", getResources().getString(R.string.infoToShare));
                setResult(Activity.RESULT_OK, resultIntent);
                ListItemsActivity.this.finish();
            });
            builder.setNegativeButton(R.string.action_cancel, (dialog, which) -> {

            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && responseCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageButton imageBtn = findViewById(R.id.imageButton);
            imageBtn.setImageBitmap(imageBitmap);
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
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

}