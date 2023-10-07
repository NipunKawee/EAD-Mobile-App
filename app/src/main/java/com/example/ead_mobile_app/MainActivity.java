package com.example.ead_mobile_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnRegistration;
    private Button btnProfileModification;
    private Button btnAccountDeactivation;
    private Button btnLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        btnRegistration = findViewById(R.id.btnRegistration);
        btnProfileModification = findViewById(R.id.btnProfileModification);
        btnAccountDeactivation = findViewById(R.id.btnAccountDeactivation);
        btnLogin = findViewById(R.id.btnLogin);

        // Set click listeners for buttons
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch RegistrationActivity
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch RegistrationActivity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnProfileModification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch ProfileModificationActivity
                Intent intent = new Intent(MainActivity.this, ProfileModificationActivity.class);
                startActivity(intent);
            }
        });

        btnAccountDeactivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch AccountDeactivationActivity
                Intent intent = new Intent(MainActivity.this, AccountDeactivationActivity.class);
                startActivity(intent);
            }
        });
    }
}

