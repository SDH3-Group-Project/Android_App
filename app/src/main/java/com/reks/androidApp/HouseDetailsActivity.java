package com.reks.androidApp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

//    Button longLat;

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


//        dependentsSpinner = (Spinner) findViewById(R.id.dependents_spinner);
//        dependentsAdapter = ArrayAdapter.createFromResource(this, R.array.dependents_options, android.R.layout.simple_spinner_item);
//        dependentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        dependentsSpinner.setAdapter(dependentsAdapter);
//        dependentsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getBaseContext(), adapterView.getItemAtPosition(i) + " is selected",Toast.LENGTH_SHORT);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

//        propertySpinner = (Spinner) findViewById(R.id.property_spinner);
//        propertyAdapter = ArrayAdapter.createFromResource(this, R.array.property_options, android.R.layout.simple_spinner_item);
//        propertyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        propertySpinner.setAdapter(propertyAdapter);
//        propertySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getBaseContext(), adapterView.getItemAtPosition(i) + " is selected",Toast.LENGTH_SHORT);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
    }
}