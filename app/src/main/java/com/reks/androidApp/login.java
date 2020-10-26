package com.reks.androidApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class login extends AppCompatActivity {
    private EditText lPasswordInput;
    private EditText lEmailInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lEmailInput = findViewById(R.id.userEmailInput);
        lPasswordInput = findViewById(R.id.userPasswordInput);
    }

}