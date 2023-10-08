package com.example.ead_mobile_app;

import android.annotation.SuppressLint;
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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered username and password
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Perform authentication (replace with your authentication logic)
                if (authenticate(username, password)) {
                    // Get the userNic from the authentication system (e.g., session or database)
                    String userNic = getUserNicByUsername(username);

                    if (userNic != null) {
                        // Check if the user is active or deactivated
                        if (UserManager.isUserActive(userNic)) {
                            // Authentication successful and user is active, navigate to the TrainBookingActivity
                            Intent intent = new Intent(LoginActivity.this, TrainBookingActivity.class);
                            intent.putExtra("userNic", userNic);
                            startActivity(intent);
                            finish(); // Finish the LoginActivity
                        } else {
                            // User is deactivated, show an error message
                            Toast.makeText(LoginActivity.this, "Your account is deactivated", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Handle the case where userNic retrieval failed
                        Toast.makeText(LoginActivity.this, "Failed to retrieve user information", Toast.LENGTH_SHORT).show();
                    }
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

//    public void onLoginSuccess(String userNic) {
//        Intent intent = new Intent(this, TrainBookingActivity.class);
//        intent.putExtra("userNic", userNic); // Pass userNic to TrainBookingActivity
//        startActivity(intent);
//        finish(); // Finish LoginActivity
//    }

    @SuppressLint("Range")
    private String getUserNicByUsername(String username) {
        String userNic = null;
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            // Assuming you have a DatabaseHelper class for database operations
            DatabaseHelper dbHelper = new DatabaseHelper(this); // Adjust 'this' to your context

            // Get a readable database
            db = dbHelper.getReadableDatabase();

            // Define the columns you want to retrieve (in this case, user NIC)
            String[] columns = {DatabaseHelper.COLUMN_NIC};

            // Define the selection criteria (where clause)
            String selection = DatabaseHelper.COLUMN_USERNAME + " = ?";

            // Define the selection arguments (the username provided as an argument)
            String[] selectionArgs = {username};

            // Query the database to fetch the user NIC based on the username
            cursor = db.query(
                    DatabaseHelper.TABLE_USERS,
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            // Check if a result is found
            if (cursor.moveToFirst()) {
                userNic = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NIC));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the cursor and database
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return userNic;
    }

}

