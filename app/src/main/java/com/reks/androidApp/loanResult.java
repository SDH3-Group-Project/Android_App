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

    String prediction;

    TextView loanPrediction;

    Double tempFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_result);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        loanPrediction = findViewById(R.id.loanPrediction);

        getPrediction();

    }

    private void getPrediction(){

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Loan Details").child(user.getUid()).child("Prediction").exists()){
                    prediction = dataSnapshot.child("Loan Details").child(user.getUid()).child("Prediction").getValue().toString();
                    tempFormat = Double.parseDouble(prediction);
                    if (tempFormat < 52){
                        prediction = "Rejected from loan";
                    }
                    else {
                        prediction = "%" + String.format("%.2f", tempFormat) + " Chance to get a loan";
                    }
                }
                else{
                    prediction = "No prediction made";
                }
                loanPrediction.setText(prediction);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });

    }
}