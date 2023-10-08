package com.example.ead_mobile_app;

import android.content.Intent;
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

        String userNic = getIntent().getStringExtra("userNic");

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

                UserManager userManager = new UserManager(ProfileModificationActivity.this);
                boolean profileUpdateSuccessful = userManager.updateUser(userNic, newUsername);

                // Display a success or error message to the user
                if (profileUpdateSuccessful) {
                    Toast.makeText(ProfileModificationActivity.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();

                    // Logout the user and navigate back to the LoginActivity
                    logoutAndNavigateToLogin();
                } else {
                    Toast.makeText(ProfileModificationActivity.this, "Profile Update Failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDeactivateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the deactivate user function from the UserManagement class
                UserManager userManager = new UserManager(ProfileModificationActivity.this);
                boolean accountDeactivationSuccessful = userManager.deactivateUser(userNic);

                // Display a success or error message to the user
                if (accountDeactivationSuccessful) {
                    Toast.makeText(ProfileModificationActivity.this, "Account Deactivated Successfully", Toast.LENGTH_SHORT).show();
                    // Logout the user and navigate back to the LoginActivity
                    logoutAndNavigateToLogin();
                } else {
                    Toast.makeText(ProfileModificationActivity.this, "Account Deactivation Failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void logoutAndNavigateToLogin() {
        // Perform the logout action here (clear user session, etc.)

        // Navigate to the LoginActivity
        Intent intent = new Intent(ProfileModificationActivity.this, LoginActivity.class);

        // Clear the back stack so the user cannot navigate back to the ProfileModificationActivity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
        finish(); // Finish the ProfileModificationActivity
    }
}

