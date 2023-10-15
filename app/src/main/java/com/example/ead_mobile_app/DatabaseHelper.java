package com.example.ead_mobile_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //public static final String COLUMN_NIC = "nic";
    private static final String DATABASE_NAME = "UserManager.db";
    private static final int DATABASE_VERSION = 1;

    // Define the User table schema
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_NIC = "nic";
    public static final String COLUMN_MOBILE = "mobile";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ACTIVE = "active";

    public static final String TABLE_BOOKINGS = "bookings";
    public static final String COLUMN_BOOKING_ID = "booking_id";
    public static final String COLUMN_USER_NIC = "user_nic";
    public static final String COLUMN_BOOKING_DATE = "booking_date";
    public static final String COLUMN_START_STATION = "start_station";
    public static final String COLUMN_DESTINATION_STATION = "destination_station";


    // Create the Users table
    private static final String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " (" +
            COLUMN_NIC + " TEXT PRIMARY KEY, " +
            COLUMN_MOBILE + " INTEGER, " +
            COLUMN_USERNAME + " TEXT, " +
            COLUMN_PASSWORD + " TEXT, " +
            COLUMN_ACTIVE + " INTEGER DEFAULT 1);";

    private static final String CREATE_BOOKINGS_TABLE = "CREATE TABLE " + TABLE_BOOKINGS + " (" +
            COLUMN_BOOKING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USER_NIC + " TEXT, " +
            COLUMN_BOOKING_DATE + " TEXT, " +
            COLUMN_START_STATION + " TEXT, " +
            COLUMN_DESTINATION_STATION + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_BOOKINGS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKINGS);
        onCreate(db);
    }
    public boolean deleteBooking(int bookingId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete("bookings", "booking_id=?", new String[]{String.valueOf(bookingId)});
        db.close();
        return rowsDeleted > 0;
    }






}

