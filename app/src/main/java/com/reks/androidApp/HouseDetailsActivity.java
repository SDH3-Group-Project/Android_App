package com.reks.androidApp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class HouseDetailsActivity extends AppCompatActivity {

//    Spinner dependentsSpinner;
//    ArrayAdapter<CharSequence> dependentsAdapter;
//
//    Spinner propertySpinner;
//    ArrayAdapter<CharSequence> propertyAdapter;
    TextView longitude;
    TextView latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle("House Details");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        longLat = findViewById(R.id.longLatButton);
//        longLat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent openMap = new Intent(getApplicationContext(), MapsActivity.class);
//                startActivity(openMap);
//            }
//        });

        FloatingActionButton fab_back = (FloatingActionButton) findViewById(R.id.fab_back);
        fab_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goback = new Intent(getApplicationContext(), homepage.class);
                startActivity(goback);
            }
        });

        longitude = findViewById(R.id.textViewLong);
        latitude = findViewById(R.id.textViewLat);

    }

    public void getLongLat(View view) {
        Intent openMap = new Intent(getApplicationContext(), MapsActivity.class);
        startActivityForResult(openMap, 100);

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){

            if(requestCode==100){
                longitude.setText(MapsActivity.getLat() + "");
                latitude.setText(MapsActivity.getLong() + "");
            }
        }

    }
}