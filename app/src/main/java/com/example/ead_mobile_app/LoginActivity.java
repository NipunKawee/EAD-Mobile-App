package com.example.ead_mobile_app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
                    // Authentication successful, call onLoginSuccess and pass userNic
                    onLoginSuccess("your_userNic_here"); // Replace "your_userNic_here" with the actual userNic
                } else {
                    // Authentication failed, show an error message
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Launch TrainBookingActivity after login
//                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private boolean authenticate(String username, String password) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Perform a database query to check if the provided username and password exist
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USERS,
                new String[]{DatabaseHelper.COLUMN_USERNAME, DatabaseHelper.COLUMN_PASSWORD},
                DatabaseHelper.COLUMN_USERNAME + " = ? AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?",
                new String[]{username, password},
                null,
                null,
                null
        );

        boolean isAuthenticated = cursor.getCount() > 0;

        // Close the cursor and database
        cursor.close();
        db.close();

        return isAuthenticated;
    }

    public void onLoginSuccess(String userNic) {
        Intent intent = new Intent(this, TrainBookingActivity.class);
        intent.putExtra("userNic", userNic); // Pass userNic to TrainBookingActivity
        startActivity(intent);
        finish(); // Finish LoginActivity
    }
}

