package com.example.ead_mobile_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class UserManager {

    static DatabaseHelper dbHelper;

    public UserManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // User registration
    public boolean registerUser(String nic, String username, String password, int mobile) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NIC, nic);
        values.put(DatabaseHelper.COLUMN_MOBILE, mobile);
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);
        long newRowId = db.insert(DatabaseHelper.TABLE_USERS, null, values);
        return newRowId != -1;
    }

    // User modification (update username)
    public boolean updateUser(String nic, String newUsername) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, newUsername);
        int rowsAffected = db.update(DatabaseHelper.TABLE_USERS, values, DatabaseHelper.COLUMN_NIC + " = ?", new String[]{nic});
        return rowsAffected > 0;
    }

    // User deactivation
    public boolean deactivateUser(String nic) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ACTIVE, 0); // 0 means deactivated
        int rowsAffected = db.update(DatabaseHelper.TABLE_USERS, values, DatabaseHelper.COLUMN_NIC + " = ?", new String[]{nic});
        return rowsAffected > 0;
    }

//    // User reactivation (by a back-office officer)
//    public boolean reactivateUser(String nic) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(DatabaseHelper.COLUMN_ACTIVE, 1); // 1 means activated
//        int rowsAffected = db.update(DatabaseHelper.TABLE_USERS, values, DatabaseHelper.COLUMN_NIC + " = ?", new String[]{nic});
//        return rowsAffected > 0;
//    }
public static boolean isUserActive(String userNic) {
    SQLiteDatabase db = dbHelper.getReadableDatabase();
    String[] projection = {DatabaseHelper.COLUMN_ACTIVE};
    String selection = DatabaseHelper.COLUMN_NIC + " = ?";
    String[] selectionArgs = {userNic};

    Cursor cursor = db.query(
            DatabaseHelper.TABLE_USERS,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
    );

    if (cursor != null && cursor.moveToFirst()) {
        int activeStatus = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ACTIVE));
        cursor.close();
        return activeStatus == 1; // 1 means active, 0 means deactivated
    }

    return false; // User not found or an error occurred
}
}



