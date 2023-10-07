package com.example.ead_mobile_app;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etNic;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etMobile;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize UI elements
        etNic = findViewById(R.id.etNic);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etMobile = findViewById(R.id.etMobile);
        btnRegister = findViewById(R.id.btnRegister);

        // Set a click listener for the registration button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform user registration
                String nic = etNic.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();


                UserManager userManager = new UserManager(RegistrationActivity.this);

                // Insert user data into the database
                boolean registrationSuccessful = userManager.registerUser(nic, username, password, Integer.parseInt(mobile));

                if (registrationSuccessful) {
                    // Registration was successful
                    Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegistrationActivity.this, TrainBookingActivity.class);
                    startActivity(intent);

                    // Finish this activity so the user can't navigate back to it
                    finish();
                } else {
                    // Registration failed
                    Toast.makeText(RegistrationActivity.this, "Registration Failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

