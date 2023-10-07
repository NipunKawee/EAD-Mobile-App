package com.example.ead_mobile_app;

import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileModificationActivity extends AppCompatActivity {

    private EditText etNewUsername;
    private Button btnUpdateProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_modification);

        // Initialize UI elements
        etNewUsername = findViewById(R.id.etNewUsername);
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile);

        // Set a click listener for the update profile button
        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform profile modification
                String newUsername = etNewUsername.getText().toString().trim();

                // Validate and update the user's profile (you can add your logic here)

                // Display a success or error message to the user
                boolean profileUpdateSuccessful = false;
                if (profileUpdateSuccessful) {
                    Toast.makeText(ProfileModificationActivity.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                    // Optionally, you can navigate to another activity here
                } else {
                    Toast.makeText(ProfileModificationActivity.this, "Profile Update Failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

