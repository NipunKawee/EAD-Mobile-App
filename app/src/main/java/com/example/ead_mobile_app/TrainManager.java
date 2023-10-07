package com.example.ead_mobile_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class TrainManager {

    private Context context;
    private DatabaseHelper dbHelper;

    public TrainManager(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    // Insert a train booking into the database
    public boolean bookTrain(String userNic, String bookingDate, String startStation, String destinationStation) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USER_NIC, userNic);
        values.put(DatabaseHelper.COLUMN_BOOKING_DATE, bookingDate);
        values.put(DatabaseHelper.COLUMN_START_STATION, startStation);
        values.put(DatabaseHelper.COLUMN_DESTINATION_STATION, destinationStation);

        // Insert the booking and return the ID of the newly inserted row
        long newRowId = db.insert(DatabaseHelper.TABLE_BOOKINGS, null, values);
        db.close();
        return newRowId != -1;
    }

}
