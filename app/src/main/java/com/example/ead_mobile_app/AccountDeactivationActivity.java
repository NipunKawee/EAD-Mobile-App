package com.example.ead_mobile_app;

import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AccountDeactivationActivity extends AppCompatActivity {

    private Button btnDeactivateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_deactivation);

        // Initialize the "Deactivate Account" button
        btnDeactivateAccount = findViewById(R.id.btnDeactivateAccount);

        // Set a click listener for the "Deactivate Account" button
        btnDeactivateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform account deactivation
                // You should add your logic here to deactivate the user's account

                // Display a success or error message to the user
                boolean accountDeactivationSuccessful = false;
                if (accountDeactivationSuccessful) {
                    Toast.makeText(AccountDeactivationActivity.this, "Account Deactivated Successfully", Toast.LENGTH_SHORT).show();
                    // Optionally, you can navigate to another activity here
                } else {
                    Toast.makeText(AccountDeactivationActivity.this, "Account Deactivation Failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

