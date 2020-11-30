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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager;
import com.google.firebase.ml.custom.FirebaseCustomRemoteModel;

import org.tensorflow.lite.Interpreter;

import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class HouseDetailsActivity extends AppCompatActivity {

    TextView longitude;
    TextView latitude;

    Map<String, String> allRadio = new HashMap<>();

    EditText date, nbOfBedrooms, nbOfBathrooms, interiorSqFt, totalSqFt, numberOfFloors,
            gradeOfHouse, sqFtAboveGroundFloor, sqFtInBasement, yrOfConstruction, lastRenovation, addressZipCode, sqftLiving15, sqftLot15, dateText;

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
        sqftLiving15 = findViewById(R.id.edit_sqft_living15);
        sqftLot15 = findViewById(R.id.edit_sqft_lot15);
        dateText = findViewById(R.id.edit_date);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                mAuth = FirebaseAuth.getInstance();
                final FirebaseUser user = mAuth.getCurrentUser();

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

                ArrayList<Float> inputs = new ArrayList<Float>();
                inputs.add(Float.valueOf(nbOfBedrooms.getText().toString()));
                inputs.add(Float.valueOf(nbOfBathrooms.getText().toString()));
                inputs.add(Float.valueOf(interiorSqFt.getText().toString()));
                inputs.add(Float.valueOf(totalSqFt.getText().toString()));
                inputs.add(Float.valueOf(numberOfFloors.getText().toString()));
                inputs.add((float) encodeData(new String[]{"No", "Yes"}, allRadio.get("Water")));
                inputs.add((float) encodeData(new String[]{"Awful", "Bad", "Fine", "Good", "Great"}, allRadio.get("viewQuality")));
                inputs.add((float) encodeData(new String[]{"Awful", "Bad", "Fine", "Good", "Great"}, allRadio.get("condition")));
                inputs.add(Float.valueOf(gradeOfHouse.getText().toString()));
                inputs.add(Float.valueOf(sqFtAboveGroundFloor.getText().toString()));
                inputs.add(Float.valueOf(sqFtInBasement.getText().toString()));
                inputs.add(Float.valueOf(yrOfConstruction.getText().toString()));
                inputs.add(Float.valueOf(lastRenovation.getText().toString()));
                inputs.add(Float.valueOf(latitude.getText().toString()));
                inputs.add(Float.valueOf((longitude.getText().toString())));
                inputs.add(Float.valueOf(sqftLiving15.getText().toString()));
                inputs.add(Float.valueOf(sqftLot15.getText().toString()));
                String date = dateText.getText().toString();
                inputs.add(Float.valueOf(date.split("-")[1]));
                inputs.add(Float.valueOf(date.split("-")[0]));

                final float[] input_tensors = new float[19];
                for(int i = 0; i < inputs.size(); i++)
                {
                    input_tensors[i] = inputs.get(i);
                }
                min_max_scale(input_tensors);

                FirebaseCustomRemoteModel remoteModel =
                        new FirebaseCustomRemoteModel.Builder("home_prediction_model").build();
                FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder()
                        .requireWifi()
                        .build();
                FirebaseModelManager.getInstance().download(remoteModel, conditions)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void v) {
                                // Download complete. Depending on your app, you could enable
                                // the ML feature, or switch from the local model to the remote
                                // model, etc.
                                FirebaseCustomRemoteModel remoteModel = new FirebaseCustomRemoteModel.Builder("home_prediction_model").build();
                                FirebaseModelManager.getInstance().getLatestModelFile(remoteModel)
                                        .addOnCompleteListener(new OnCompleteListener<File>() {
                                            @Override
                                            public void onComplete(@NonNull Task<File> task)
                                            {
                                                File modelFile = task.getResult();
                                                if (modelFile != null)
                                                {
//                                                    float[] input_tensors = {3,	1,	1180,	5650,	1,	0,	0,	3,	7,	1180,	0,	1955,	0,	47.5112f,	-122.257f,	1340,	5650,	10,	2014};
                                                    min_max_scale(input_tensors);
                                                    System.out.println(Arrays.toString(input_tensors));
                                                    Interpreter interpreter = new Interpreter(modelFile);
                                                    float[][] outputValue = new float[1][1];
                                                    interpreter.run(input_tensors, outputValue);

                                                    System.out.println("Prediction is: " + outputValue[0][0]);
                                                    myRef.child("House Details").child(user.getUid()).child("Prediction").setValue((outputValue[0][0]));
                                                }
                                            }

                                        });
                            }
                        });

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

    public void min_max_scale(float[] input_tensors)
    {
        Arrays.sort(input_tensors);
        float min = input_tensors[0];
        float max = input_tensors[input_tensors.length - 1];

        for(int i  = 0; i < input_tensors.length; i++)
        {
            input_tensors[i] = (input_tensors[i] - min)/(max - min);
        }
    }

    public int encodeData(String[] options, String option)
    {
        for(int i = 0; i < options.length; i++)
        {
            if(option.equals(options[i]))
            {
                return i;
            }
        }
        return -1;
    }
}
