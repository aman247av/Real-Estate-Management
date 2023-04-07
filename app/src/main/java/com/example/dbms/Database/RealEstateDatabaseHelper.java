package com.example.dbms.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.dbms.HomePage;
import com.example.dbms.Model.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

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
        String CREATE_TABLE_CUSTOMER = "CREATE TABLE Customer (\n" +
                "  customer_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  email VARCHAR(255) NOT NULL,\n" +
                "  name VARCHAR(255) NOT NULL,\n" +
                "  password VARCHAR(255) NOT NULL,\n" +
                "  contact VARCHAR(15) NOT NULL,\n" +
                "  DOB DATE NOT NULL\n" +
                ");";

        String CREATE_TABLE_PROPERTY = "CREATE TABLE Property (property_id int PRIMARY KEY, " +
                " type varchar(20)," +
                " area_size int," +
                " bedroom_count int," +
                " image mediumblob," +
                " category varchar(50)," +
                " construction_year int," +
                " rent int," +
                " selling_price int," +
                " status varchar(15)," +
                " house_no varchar(10)," +
                " street varchar(50)," +
                " district varchar(50)," +
                " city varchar(25)," +
                " state varchar(20)," +
                " pincode int(10));";

        db.execSQL(CREATE_TABLE_CUSTOMER);
        db.execSQL(CREATE_TABLE_PROPERTY);
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

    public boolean authenticateUser(Context context, String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format(Locale.UK, "SELECT username,password FROM Customer WHERE username=\"%s\";", username);

        Cursor c = db.rawQuery(query, null);

        int checkUser = c.getCount();

        c.moveToFirst();

        if(checkUser > 0){
            String realPassword = c.getString(1);

            if(password.equals(realPassword)){
                Toast.makeText(context, "Sign in successful!", Toast.LENGTH_SHORT).show();

                return true;
            }
            else{
                Toast.makeText(context, "Wrong password!", Toast.LENGTH_SHORT).show();

                return false;
            }
        }
        else{
            Toast.makeText(context, "This username does not exist", Toast.LENGTH_SHORT).show();

            return false;
        }
    }

    @SuppressLint("Range")
    public List<Property> getData(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format(Locale.UK, "SELECT * FROM Property");

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        List<Property> propertyList = new ArrayList<>();

        while (c.moveToNext()){
            Property property;
            if(c.getString(c.getColumnIndex("type")).equals("rent")) {
                property = new Property(c.getInt(0), c.getString(c.getColumnIndex("p_name")), c.getString(c.getColumnIndex("type")), c.getInt(c.getColumnIndex("area_size")), c.getInt(c.getColumnIndex("no_of_bedrooms")), c.getString(c.getColumnIndex("category")), c.getInt(c.getColumnIndex("year_of_const")), c.getInt(c.getColumnIndex("rent")), c.getInt(c.getColumnIndex("rent")), c.getString(c.getColumnIndex("status")), c.getString(c.getColumnIndex("house_no")), c.getString(c.getColumnIndex("street")), c.getString(c.getColumnIndex("district")), c.getString(c.getColumnIndex("city")), c.getString(c.getColumnIndex("state")), c.getInt(c.getColumnIndex("pincode")));
            }
            else{
                property = new Property(c.getInt(0), c.getString(c.getColumnIndex("p_name")), c.getString(c.getColumnIndex("type")), c.getInt(c.getColumnIndex("area_size")), c.getInt(c.getColumnIndex("no_of_bedrooms")), c.getString(c.getColumnIndex("category")), c.getInt(c.getColumnIndex("year_of_const")), c.getInt(c.getColumnIndex("selling_price")), c.getInt(c.getColumnIndex("rent")), c.getString(c.getColumnIndex("status")), c.getString(c.getColumnIndex("house_no")), c.getString(c.getColumnIndex("street")), c.getString(c.getColumnIndex("district")), c.getString(c.getColumnIndex("city")), c.getString(c.getColumnIndex("state")), c.getInt(c.getColumnIndex("pincode")));
            }
            propertyList.add(property);
        }

        return propertyList;
    }
}
