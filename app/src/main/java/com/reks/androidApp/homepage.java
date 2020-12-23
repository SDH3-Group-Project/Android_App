package com.reks.androidApp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class homepage extends AppCompatActivity {

    TextView userName, userMail;
    Button logout;
    ImageButton loan, house, chatbot, support, feedback, aboutus, result, loanOfficer;
    ImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        logout = findViewById(R.id.logout);
        userName = findViewById(R.id.userName);
        userMail = findViewById(R.id.userMail);
        userImage = findViewById(R.id.userImage);
        loan = findViewById(R.id.LoanButton);
        house = findViewById(R.id.houseButton);
        chatbot = findViewById(R.id.chatbot);
        support = findViewById(R.id.SupportButton);
        feedback = findViewById(R.id.Review);
        aboutus = findViewById(R.id.aboutUsButton);
        result = findViewById(R.id.resultsButton);
        loanOfficer = findViewById(R.id.loanOfficerButton);


        loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goLoan = new Intent(getApplicationContext(), LoanDetailsActivity.class);
                startActivity(goLoan);
            }
        });

        house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHouse= new Intent(getApplicationContext(), HouseDetailsActivity.class);
                startActivity(goHouse);
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goReview= new Intent(getApplicationContext(), feedback.class);
                startActivity(goReview);
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goAbout= new Intent(getApplicationContext(), aboutus.class);
                startActivity(goAbout);
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goResult= new Intent(getApplicationContext(), loanResult.class);
                startActivity(goResult);
            }
        });

        loanOfficer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goOfficer= new Intent(getApplicationContext(), PaypalTest.class);
                startActivity(goOfficer);
            }
        });

        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSup= new Intent(getApplicationContext(), Support.class);
                startActivity(goSup);
            }
        });

        chatbot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goChat= new Intent(getApplicationContext(), chatbot.class);
                startActivity(goChat);
            }
        });


        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            userName.setText(signInAccount.getDisplayName());
            userMail.setText(signInAccount.getEmail());
            //userImage.setImageURI(signInAccount.getPhotoUrl());
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent signOut = new Intent(getApplicationContext(), Splashscreen.class);
                startActivity(signOut);
            }
        });

    }

}