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

    //TABLE_NAME2 FOR CHARITY CLASS
    public static final String TABLE_NAME2 = "CHARITY";
    public static final String COLUMN_ID2 = "ID";
    public static final String CHARITY_NAME = "CHARITY_NAME";
    public static final String CHARITY_DESC = "CHARITY_DESC";

    //TABLE_NAME3 FOR USER_DONATION CLASS
    public static final String TABLE_NAME3 = "USER_DONATION";
    public static final String COLUMN_ID3 = "ID";
    //Email address here in this order
    public static final String CHARITY_NAME3 = "CHARITY_NAME";
    public static final String ACCOUNT_NUMBER = "ACCOUNT_NUMBER";
    public static final String ACCOUNT_PASSWORD = "ACCOUNT_PASSWORD";
    public static final String DONATION_AMOUNT = "DONATION_AMOUNT";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " Integer Primary Key AUTOINCREMENT, " + EMAIL_ID + " text, " + FULL_NAME + " text, " + PASSWORD + " text, " + USER_TYPE + " BOOL)";
        String createTableStatement2 = "CREATE TABLE " + TABLE_NAME2 + " (" + COLUMN_ID2 + " Integer Primary Key AUTOINCREMENT, " + CHARITY_NAME + " text, " + CHARITY_DESC + " text)";
        String createTableStatement3 = "CREATE TABLE " + TABLE_NAME3 + " (" + COLUMN_ID3 + " Integer Primary Key AUTOINCREMENT, " + EMAIL_ID + " text, " + CHARITY_NAME3 + " text, " + ACCOUNT_NUMBER + " INTEGER, " + ACCOUNT_PASSWORD + " text, " + DONATION_AMOUNT + " INTEGER)";

        sqLiteDatabase.execSQL(createTableStatement);
        sqLiteDatabase.execSQL(createTableStatement2);
        sqLiteDatabase.execSQL(createTableStatement3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        String dropTableQuery2 = "DROP TABLE IF EXISTS " + TABLE_NAME2;
        sqLiteDatabase.execSQL(dropTableQuery);
        sqLiteDatabase.execSQL(dropTableQuery2);
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

    public Boolean doesUserExist(String emailId, String password) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL_ID + " = ?" + " AND " + PASSWORD + " = ?";
        String[] whereArgs = {emailId, password};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);

        int count = cursor.getCount();

        cursor.close();

        return count >= 1;
    }

    public Boolean doesUserExistSignUp(String emailId) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL_ID + " = ?";
        String[] whereArgs = {emailId};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);

        int count = cursor.getCount();

        cursor.close();

        return count >= 1;
    }

    public Boolean addCharity(Charity charity) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CHARITY_NAME, charity.getCharityName());
        cv.put(CHARITY_DESC, charity.getCharityDescription());

        long row;
        row = database.insert(TABLE_NAME2, null, cv);
        database.close();
        return row != -1;
    }

    public ArrayList<Charity> getCharities() {
        ArrayList<Charity> charities = new ArrayList<>();
        String queryString = "Select * FROM " + TABLE_NAME2;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                String cName = cursor.getString(1);
                String cDesc = cursor.getString(2);
                charities.add(new Charity(cName, cDesc));
            } while (cursor.moveToNext());
        }

        database.close();
        return charities;
    }

    public Boolean addDonation(UserDonation userDonation) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(EMAIL_ID, userDonation.getUEmailAddress());
        cv.put(CHARITY_NAME3, userDonation.getCharityName());
        cv.put(ACCOUNT_NUMBER, userDonation.getAccountNumber());
        cv.put(ACCOUNT_PASSWORD, userDonation.getAccountPassword());
        cv.put(DONATION_AMOUNT, userDonation.getDonationAmount());

        long row;
        row = database.insert(TABLE_NAME3, null, cv);
        database.close();
        return row != -1;
    }

    public ArrayList<UserDonation> getDonations(String userEmail) {
        ArrayList<UserDonation> donations = new ArrayList<>();
        String queryString = "Select * FROM " + TABLE_NAME3 + "where " + EMAIL_ID + " = "+ userEmail;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                /*
                String userEmail = cursor.getString(1);
                String userFullName = cursor.getString(2);
                String userPassword = cursor.getString(3);
                Boolean isAdmin = cursor.getInt(4) == 1;
                User user = new User(userEmail, userFullName, userPassword, isAdmin);
                users.add(user);

                private String uEmailAddress;
                private String charityName;
                private Integer accountNumber;
                private String accountPassword;
                private Integer donationAmount;
                */

                String uEmailAddress = cursor.getString(1);
                String charityName = cursor.getString(2);
                Integer accountNumber = cursor.getInt(3);
                String accountPassword = cursor.getString(4);
                Integer donationAmount = cursor.getInt(5);
                donations.add(new UserDonation(uEmailAddress,charityName,accountNumber,accountPassword,donationAmount));

            } while (cursor.moveToNext());
        }

        database.close();
        return donations;
    }

    public int deleteDatabase() {
        SQLiteDatabase database = getWritableDatabase();
        int rowsDeleted = database.delete(TABLE_NAME, "1", null);
        database.close();
        return rowsDeleted;
    }
}
