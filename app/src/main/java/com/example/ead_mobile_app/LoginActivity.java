package com.example.ead_mobile_app;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI elements
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Set a click listener for the login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered username and password
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (authenticate(username, password)) {
                    // Authentication successful, navigate to the TrainBookingActivity
                    Intent intent = new Intent(LoginActivity.this, TrainBookingActivity.class);
                    startActivity(intent);
                    finish(); // Finish the LoginActivity so that the user can't navigate back to it
                } else {
                    // Authentication failed, show an error message
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch TrainBookingActivity after login
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean authenticate(String username, String password) {
        // You can implement your own logic, such as checking credentials against a database
        // For this example, we'll use a simple check
        return username.equals("your_username") && password.equals("your_password");
    }
    public void onLoginSuccess(String userNic) {
        Intent intent = new Intent(this, TrainBookingActivity.class);
        intent.putExtra("userNic", userNic); // Pass userNic to TrainBookingActivity
        startActivity(intent);
        finish(); // Finish LoginActivity
    }
}

