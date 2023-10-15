package com.example.ead_mobile_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookingHistoryActivity extends Activity {
    private ListView listViewBookingHistory;
    private String userNic;
    //private Button deleteButton;
    private BookingHistoryAdapter adapter;
    private ArrayList<BookingHistoryItem> bookingHistoryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);


        //deleteButton = findViewById(R.id.deleteButton);

        // Get the userNic passed from TrainBookingActivity
        userNic = getIntent().getStringExtra("userNic");

        // Initialize the ListView
        listViewBookingHistory = findViewById(R.id.listViewBookingHistory);

        // Retrieve booking history data for the logged-in user
        ArrayList<String> bookingHistoryData = retrieveBookingHistoryData(userNic);

        // Create an adapter to populate the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookingHistoryData);

        // Set the adapter to the ListView
        listViewBookingHistory.setAdapter(adapter);


    }

    // Method to retrieve booking history data for the logged-in user from SQLite database
    private ArrayList<String> retrieveBookingHistoryData(String userNic) {
        ArrayList<String> bookingHistoryData = new ArrayList<>();

        // Get a readable database instance
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                "booking_id",
                "booking_date",
                "start_station",
                "destination_station"
        };

        // Define the selection (WHERE) clause
        String selection = "user_nic = ?";
        String[] selectionArgs = {userNic};

        // Query the database to fetch booking history for the user
        Cursor cursor = db.query("bookings", projection, selection, selectionArgs, null, null, null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") int bookingId = cursor.getInt(cursor.getColumnIndex("booking_id"));
            @SuppressLint("Range") String bookingDate = cursor.getString(cursor.getColumnIndex("booking_date"));
            @SuppressLint("Range") String startStation = cursor.getString(cursor.getColumnIndex("start_station"));
            @SuppressLint("Range") String destinationStation = cursor.getString(cursor.getColumnIndex("destination_station"));

            // Format the booking data as a string and add it to the list
            String bookingInfo = "Booking " + bookingId + ": Date - " + bookingDate +
                    ", From - " + startStation + ", To - " + destinationStation;
            bookingHistoryData.add(bookingInfo);
        }

        // Close the cursor and database connection
        cursor.close();
        db.close();

        return bookingHistoryData;
    }

    private class BookingHistoryAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return bookingHistoryData.size();
        }

        @Override
        public Object getItem(int position) {
            return bookingHistoryData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.booking_list_item, parent, false);
            }

            TextView bookingInfoTextView = convertView.findViewById(R.id.bookingInfoTextView);
            Button deleteButton = convertView.findViewById(R.id.deleteButton);

            final BookingHistoryItem item = bookingHistoryData.get(position);

            // Display booking information
            bookingInfoTextView.setText("Booking " + item.getBookingId() + ": Date - " + item.getBookingDate()
                    + ", From - " + item.getStartStation() + ", To - " + item.getDestinationStation());

            // Handle delete button click
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle the delete action here
                    bookingHistoryData.remove(position);
                    notifyDataSetChanged(); // Refresh the ListView

                    deleteBooking(item.getBookingId());
                }
            });

            return convertView;
        }
    }
    private void deleteBooking(int bookingId) {
        // Get a writable database instance
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = "booking_id = ?";
        String[] selectionArgs = {String.valueOf(bookingId)};

        // Delete the booking record
        int deletedRows = db.delete("bookings", selection, selectionArgs);

        if (deletedRows > 0) {
            Toast.makeText(BookingHistoryActivity.this, "Booking deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(BookingHistoryActivity.this, "Failed to delete booking", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }


}
