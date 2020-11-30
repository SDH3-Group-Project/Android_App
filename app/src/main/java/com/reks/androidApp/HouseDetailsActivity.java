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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class HouseDetailsActivity extends AppCompatActivity {

    TextView longitude;
    TextView latitude;

    Map<String, String> allRadio = new HashMap<>();

    EditText date, nbOfBedrooms, nbOfBathrooms, interiorSqFt, totalSqFt, numberOfFloors,
            gradeOfHouse, sqFtAboveGroundFloor, sqFtInBasement, yrOfConstruction, lastRenovation, addressZipCode;

    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle("House Details");

        date = findViewById(R.id.edit_date);
        nbOfBedrooms = findViewById(R.id.edit_bedrooms);
        nbOfBathrooms = findViewById(R.id.edit_bathrooms);
        interiorSqFt = findViewById(R.id.edit_sqft_interior);
        totalSqFt = findViewById(R.id.edit_sqft_land);
        numberOfFloors = findViewById(R.id.edit_floors);
        gradeOfHouse = findViewById(R.id.edit_Grade);
        sqFtAboveGroundFloor = findViewById(R.id.edit_sqft_above_floor);
        sqFtInBasement = findViewById(R.id.edit_sqft_basement);
        yrOfConstruction = findViewById(R.id.edit_yr_built);
        lastRenovation = findViewById(R.id.edit_yr_renovated);
        addressZipCode = findViewById(R.id.edit_ZipCode);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Data Sent to Database", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();

                myRef.child("House Details").child(user.getUid()).child("Date").setValue(date.getText().toString());
                myRef.child("House Details").child(user.getUid()).child("Number Of Bedrooms").setValue(nbOfBedrooms.getText().toString());
                myRef.child("House Details").child(user.getUid()).child("Number Of Bathrooms").setValue(nbOfBathrooms.getText().toString());
                myRef.child("House Details").child(user.getUid()).child("Interior Square Feet").setValue(interiorSqFt.getText().toString());
                myRef.child("House Details").child(user.getUid()).child("Total Land Square Feet").setValue(totalSqFt.getText().toString());
                myRef.child("House Details").child(user.getUid()).child("Number Of Floors").setValue(numberOfFloors.getText().toString());
                myRef.child("House Details").child(user.getUid()).child("Waterfront View").setValue(allRadio.get("Water"));
                myRef.child("House Details").child(user.getUid()).child("Quality Of View").setValue(allRadio.get("viewQuality"));
                myRef.child("House Details").child(user.getUid()).child("Condition Of The Property").setValue(allRadio.get("condition"));
                myRef.child("House Details").child(user.getUid()).child("Grade Of House").setValue(gradeOfHouse.getText().toString());
                myRef.child("House Details").child(user.getUid()).child("Square Feet Above Ground Floors").setValue(sqFtAboveGroundFloor.getText().toString());
                myRef.child("House Details").child(user.getUid()).child("Square Feet In Basement").setValue(sqFtInBasement.getText().toString());
                myRef.child("House Details").child(user.getUid()).child("Year Of Construction").setValue(yrOfConstruction.getText().toString());
                myRef.child("House Details").child(user.getUid()).child("Last Renovated").setValue(lastRenovation.getText().toString());
                myRef.child("House Details").child(user.getUid()).child("Address ZipCode").setValue(addressZipCode.getText().toString());
                myRef.child("House Details").child(user.getUid()).child("Longitude").setValue(longitude.getText().toString());
                myRef.child("House Details").child(user.getUid()).child("Latitude").setValue(latitude.getText().toString());

            }
        });

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

        if (resultCode == RESULT_OK) {

            if (requestCode == 100) {
                longitude.setText(MapsActivity.getLat() + "");
                latitude.setText(MapsActivity.getLong() + "");
            }
        }

    }

    public void onRadioClicked(View view) {
        // Check if button currently checked
        boolean checked = ((RadioButton) view).isChecked();

        // Tag naming format: group_pick. Ex: sex_female
        String[] tag = view.getTag().toString().split("_");
        String group = tag[0];
        String pick = tag[1];

        // Put all data
        if (checked) {
            allRadio.put(group, pick);
        }
    }
}