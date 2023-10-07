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
    private Button btnDeactivateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_modification);

        // Initialize UI elements
        etNewUsername = findViewById(R.id.etNewUsername);
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile);
        btnDeactivateAccount = findViewById(R.id.btnDeactivateAccount);

        // Set a click listener for the update profile button
        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform profile modification
                String newUsername = etNewUsername.getText().toString().trim();


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
        btnDeactivateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Display a success or error message to the user
                boolean accountDeactivationSuccessful = false;
                if (accountDeactivationSuccessful) {
                    Toast.makeText(ProfileModificationActivity.this, "Account Deactivated Successfully", Toast.LENGTH_SHORT).show();
                    // Optionally, you can navigate to another activity here
                } else {
                    Toast.makeText(ProfileModificationActivity.this, "Account Deactivation Failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

