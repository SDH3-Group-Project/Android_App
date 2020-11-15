package com.reks.androidApp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class homepage extends AppCompatActivity {

    TextView userName, userMail;
    Button logout, loan, house;
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