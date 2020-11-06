package com.reks.androidApp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Splashscreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

//      If the user is already logged in when you open the app they will be brought directly to the main menu, otherwise they will have to login
        handler.postDelayed(new Runnable() {
            public void run() {
                if(user != null){
                    Intent homepageIntent = new Intent(getApplicationContext(), homepage.class);
                    startActivity(homepageIntent);
                }
                else{
                    Intent loginIntent = new Intent(getApplicationContext(), login.class);
                    startActivity(loginIntent);
                }
            }
        }, 1000);
    }

}