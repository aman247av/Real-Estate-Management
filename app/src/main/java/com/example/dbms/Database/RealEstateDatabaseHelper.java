package com.example.dbms.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Locale;

public class RealEstateDatabaseHelper extends SQLiteOpenHelper {

    //Database Info
    private static final String DATABASE_NAME = "RealEstateDatabase";
    private static final int DATABASE_VERSION = 1;

    //Tables in database
    private static final String TABLE_CUSTOMER = "Customer";
    private static final String TABLE_AGENT = "Agent";
    private static final String TABLE_PROPERTY = "Property";
    private static final String TABLE_OWNER = "Owner";

    public RealEstateDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_CUSTOMER = "CREATE TABLE Customer(" +
                "customer_id int PRIMARY KEY," +
                "name varchar(255), " +
                "contact int, " +
                "e_mail varchar(255), " +
                "dob varchar(11), " +
                "username varchar(50), " +
                "password varchar(50)" +
                ");";

        db.execSQL(CREATE_TABLE_CUSTOMER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENT);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_OWNER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROPERTY);
            onCreate(db);
        }
    }

    public void authenticateUser(Context context, String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format(Locale.UK, "SELECT username,password FROM Customer WHERE username=\"%s\";", username);

        Cursor c = db.rawQuery(query, null);

        int checkUser = c.getCount();

        c.moveToFirst();

        if(checkUser > 0){
            String realPassword = c.getString(1);

            if(password.equals(realPassword)){
                //Code for processes after sign in
                Toast.makeText(context, "User signed in successfully", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Wrong password!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(context, "This username does not exist", Toast.LENGTH_SHORT).show();
        }
    }
}
