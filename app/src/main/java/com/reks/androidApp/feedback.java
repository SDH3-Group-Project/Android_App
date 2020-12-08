package com.reks.androidApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class feedback extends AppCompatActivity {

    RatingBar ratingSelector;

    EditText review;

    Button submit;

    private FirebaseAuth mAuth;
    FirebaseUser user;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ratingSelector = findViewById(R.id.ratingBar2);
        review = findViewById(R.id.feedback);
        submit = findViewById(R.id.feedbackSubmit);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Sending Review";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                myRef.child("Reviews").child(user.getUid()).child("Rating").setValue(ratingSelector.getRating());
                myRef.child("Reviews").child(user.getUid()).child("Review").setValue(review.getText().toString().trim());

                review.setText("");
            }
        });
    }
}