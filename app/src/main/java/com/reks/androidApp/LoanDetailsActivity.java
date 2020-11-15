package com.reks.androidApp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoanDetailsActivity extends AppCompatActivity {

    Map<String, String> allRadio = new HashMap<>();

    Spinner dependentsSpinner;
    ArrayAdapter<CharSequence> dependentsAdapter;

    Spinner propertySpinner;
    ArrayAdapter<CharSequence> propertyAdapter;

    EditText applicantIncome, coApplicantIncome, loanAmount, loanAmountTerm;

    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle("Loan Details");

        applicantIncome = findViewById(R.id.edit_income);
        coApplicantIncome = findViewById(R.id.edit_co_income);
        loanAmount = findViewById(R.id.edit_loan_amount);
        loanAmountTerm = findViewById(R.id.edit_loan_term);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();

                myRef.child("Loan Details").child(user.getUid()).child("Gender").setValue(allRadio.get("Gender"));
                myRef.child("Loan Details").child(user.getUid()).child("Married").setValue(allRadio.get("Married"));
                myRef.child("Loan Details").child(user.getUid()).child("Dependents").setValue(dependentsSpinner.getSelectedItem().toString());
                myRef.child("Loan Details").child(user.getUid()).child("Graduate").setValue(allRadio.get("Graduate"));
                myRef.child("Loan Details").child(user.getUid()).child("Self Employed").setValue(allRadio.get("sEmployed"));
                myRef.child("Loan Details").child(user.getUid()).child("Applicant Income").setValue(applicantIncome.getText().toString());
                myRef.child("Loan Details").child(user.getUid()).child("Co-Applicant Income").setValue(coApplicantIncome.getText().toString());
                myRef.child("Loan Details").child(user.getUid()).child("Loan Amount").setValue(loanAmount.getText().toString());
                myRef.child("Loan Details").child(user.getUid()).child("Loan Amount Term").setValue(loanAmountTerm.getText().toString());
                myRef.child("Loan Details").child(user.getUid()).child("Credit History").setValue(allRadio.get("History"));
                myRef.child("Loan Details").child(user.getUid()).child("Property Area").setValue(propertySpinner.getSelectedItem().toString());
            }
        });

        FloatingActionButton fab_back = findViewById(R.id.fab_back);
        fab_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goback = new Intent(getApplicationContext(), homepage.class);
                startActivity(goback);
            }
        });


        dependentsSpinner = findViewById(R.id.dependents_spinner);
        dependentsAdapter = ArrayAdapter.createFromResource(this, R.array.dependents_options, android.R.layout.simple_spinner_item);
        dependentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dependentsSpinner.setAdapter(dependentsAdapter);
        dependentsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), adapterView.getItemAtPosition(i) + " is selected", Toast.LENGTH_SHORT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        propertySpinner = findViewById(R.id.property_spinner);
        propertyAdapter = ArrayAdapter.createFromResource(this, R.array.property_options, android.R.layout.simple_spinner_item);
        propertyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        propertySpinner.setAdapter(propertyAdapter);
        propertySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), adapterView.getItemAtPosition(i) + " is selected", Toast.LENGTH_SHORT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
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