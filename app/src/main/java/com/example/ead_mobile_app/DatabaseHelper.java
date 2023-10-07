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
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ACTIVE = "active";

    // Create the Users table
    private static final String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " (" +
            COLUMN_NIC + " TEXT PRIMARY KEY, " +
            COLUMN_USERNAME + " TEXT, " +
            COLUMN_PASSWORD + " TEXT, " +
            COLUMN_ACTIVE + " INTEGER DEFAULT 1);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades, if needed
    }
}

