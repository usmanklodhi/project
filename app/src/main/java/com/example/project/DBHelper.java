package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "myDb.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "USER_LIST";
    public static final String COLUMN_ID = "ID";
    public static final String EMAIL_ID = "EMAIL_ID";
    public static final String FULL_NAME = "FULL_NAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String USER_TYPE = "USER_TYPE";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " Integer Primary Key AUTOINCREMENT, " + EMAIL_ID + " text, " + FULL_NAME + " text, " + PASSWORD + " text, " + USER_TYPE + " BOOL)";
        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(dropTableQuery);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //super.onDowngrade(db, oldVersion, newVersion);
        onUpgrade(db, oldVersion, newVersion);
    }

    public Boolean addUser(User user) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(EMAIL_ID, user.getEmailID());
        cv.put(FULL_NAME, user.getFullName());
        cv.put(PASSWORD, user.getPassword());
        cv.put(USER_TYPE, user.getAdmin());

        long row;
        row = database.insert(TABLE_NAME, null, cv);
        database.close();
        return row != -1;
    }

    public Boolean deleteUser(User user) {
        SQLiteDatabase database = getWritableDatabase();
        int deleteCount = database.delete(TABLE_NAME, EMAIL_ID + " =? ", new String[]{user.getEmailID()});
        database.close();
        return deleteCount > 0;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        String queryString = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                String userEmail = cursor.getString(1);
                String userFullName = cursor.getString(2);
                String userPassword = cursor.getString(3);
                Boolean isAdmin = cursor.getInt(4) == 1;
                User user = new User(userEmail, userFullName, userPassword, isAdmin);
                users.add(user);
            } while (cursor.moveToNext());
        }

        database.close();
        return users;
    }

    public Boolean doesUserExist(String emailId, String password){
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL_ID + " = ?" + " AND " + PASSWORD + " = ?";
        String[] whereArgs = {emailId, password};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);

        int count = cursor.getCount();

        cursor.close();

        return count >= 1;
    }

    public Boolean doesUserExistSignUp(String emailId){
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL_ID + " = ?";
        String[] whereArgs = {emailId};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);

        int count = cursor.getCount();

        cursor.close();

        return count >= 1;
    }

    public int deleteDatabase() {
        SQLiteDatabase database = getWritableDatabase();
        int rowsDeleted = database.delete(TABLE_NAME, "1", null);
        database.close();
        return rowsDeleted;
    }
}
