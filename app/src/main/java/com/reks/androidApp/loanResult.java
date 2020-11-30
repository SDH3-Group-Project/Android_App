package com.reks.androidApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loanResult extends AppCompatActivity {

    FirebaseUser user;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    String prediction, prediction1;

    TextView loanPrediction, housePrediction;

    Double tempFormat, tempFormat1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_result);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        loanPrediction = findViewById(R.id.loanPrediction);
        housePrediction = findViewById(R.id.housePrediction);

        getPrediction();

    }

    private void getPrediction() {

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Loan Details").child(user.getUid()).child("Prediction").exists()) {
                    prediction = dataSnapshot.child("Loan Details").child(user.getUid()).child("Prediction").getValue().toString();
                    tempFormat = Double.parseDouble(prediction);
                    prediction = String.format("%.2f", tempFormat) + "%";
                } else {
                    prediction = "No prediction made";
                }
                loanPrediction.setText(prediction);

                if (dataSnapshot.child("House Details").child(user.getUid()).child("Prediction").exists()) {
                    prediction1 = dataSnapshot.child("House Details").child(user.getUid()).child("Prediction").getValue().toString();
                    tempFormat1 = Double.parseDouble(prediction1);
                    prediction1 = "$" + String.format("%.2f", tempFormat1);
                } else {
                    prediction1 = "No prediction made";
                }
                housePrediction.setText(prediction1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });

    }
}