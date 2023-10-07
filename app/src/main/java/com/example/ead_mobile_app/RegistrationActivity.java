package com.example.ead_mobile_app;

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
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize UI elements
        etNic = findViewById(R.id.etNic);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        // Set a click listener for the registration button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform user registration
                String nic = etNic.getText().toString().trim();
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Validate and register the user (you can add your logic here)

                // Display a success or error message to the user
                boolean registrationSuccessful = false;
                if (registrationSuccessful) {
                    Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    // Optionally, you can navigate to another activity here
                } else {
                    Toast.makeText(RegistrationActivity.this, "Registration Failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

