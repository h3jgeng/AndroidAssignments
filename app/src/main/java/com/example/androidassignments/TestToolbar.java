package com.example.androidassignments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.androidassignments.databinding.ActivityTestToolbarBinding;
import com.google.android.material.snackbar.Snackbar;

public class TestToolbar extends AppCompatActivity {

    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.androidassignments.databinding.ActivityTestToolbarBinding binding = ActivityTestToolbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        View parentLayout = findViewById(android.R.id.content);
        snackbar = Snackbar.make(parentLayout, getString(R.string.action_one_message), Snackbar.LENGTH_LONG);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_test_toolbar);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.action_one:
                snackbar.show();
                break;

            case R.id.action_two:
                actionTwoSelected();
                break;

            case R.id.action_three:
                actionThreeSelected();
                break;

            case R.id.action_about:
                Toast.makeText(getApplicationContext(), getString(R.string.about_message), Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    public void actionTwoSelected() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TestToolbar.this);
        builder.setTitle(R.string.dialog_message);
        builder.setPositiveButton(R.string.action_oK,
                (dialog, id) -> {
                    // User clicked OK button
                    TestToolbar.this.finish();
                });
        builder.setNegativeButton(R.string.action_cancel,
                (dialog, id) -> {
                    // User cancelled the dialog
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void actionThreeSelected() {
        AlertDialog.Builder customDialog = new AlertDialog.Builder(TestToolbar.this);
        LayoutInflater inflater = TestToolbar.this.getLayoutInflater();

        final View view = inflater.inflate(R.layout.custom_layout, null);
        customDialog.setView(view)
                .setPositiveButton(R.string.action_oK, (dialog, id) -> {
                    EditText edit = view.findViewById(R.id.dialog_message_box);
                    String message = edit.getText().toString();
                    snackbar.setText(message);
                })
                .setNegativeButton(R.string.action_cancel,
                        (dialog, id) -> {
                        });
        Dialog dialog = customDialog.create();
        dialog.show();

    }
}