package com.example.ead_mobile_app;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TrainBookingActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Spinner spinnerStartStation;
    private Spinner spinnerDestinationStation;
    private Button btnBookTrain;
    private ImageButton btnProfile;

    private String userNic; // Member variable to store userNic

    // Constructor that accepts userNic
    public TrainBookingActivity(String userNic) {
        this.userNic = userNic;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_booking);

        userNic = getIntent().getStringExtra("userNic");

        // Initialize UI elements
        datePicker = findViewById(R.id.datePicker);
        spinnerStartStation = findViewById(R.id.spinnerStartStation);
        spinnerDestinationStation = findViewById(R.id.spinnerDestinationStation);
        btnBookTrain = findViewById(R.id.btnBookTrain);
        btnProfile = findViewById(R.id.btnProfile);

        ArrayAdapter<CharSequence> stationAdapter = ArrayAdapter.createFromResource(
                this, R.array.station_array, android.R.layout.simple_spinner_item);
        stationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerStartStation.setAdapter(stationAdapter);
        spinnerDestinationStation.setAdapter(stationAdapter);

        btnBookTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get selected date, start station, and destination station
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1; // Month is 0-based
                int year = datePicker.getYear();

                String selectedDate = day + "/" + month + "/" + year;
                String startStation = spinnerStartStation.getSelectedItem().toString();
                String destinationStation = spinnerDestinationStation.getSelectedItem().toString();

                TrainManager trainManager = new TrainManager(TrainBookingActivity.this);

                // Insert train booking data into the database
                boolean bookingSuccessful = trainManager.bookTrain(userNic, selectedDate, startStation, destinationStation);

                if (bookingSuccessful) {
                    // Booking was successful
                    Toast.makeText(TrainBookingActivity.this, "Booking Successful", Toast.LENGTH_SHORT).show();

                } else {
                    // Booking failed
                    Toast.makeText(TrainBookingActivity.this, "Booking Failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

