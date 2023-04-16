package com.example.dbms.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.dbms.Model.Agent;
import com.example.dbms.Model.Property;
import com.example.dbms.Model.Transactions;

import java.util.ArrayList;
import java.util.List;
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
                " lease int," +
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

    public int authenticateUser(Context context, String table, String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format(Locale.UK, "SELECT * FROM \"%s\" WHERE email=\"%s\";", table,username);

        Cursor c = db.rawQuery(query, null);

        int checkUser = c.getCount();

        c.moveToFirst();

        if(checkUser > 0){
            String realPassword = c.getString(c.getColumnIndexOrThrow("password"));

            if(password.equals(realPassword)){
                Toast.makeText(context, "Sign in successful!", Toast.LENGTH_SHORT).show();
                db.close();
                return c.getInt(0);
            }
            else{
                Toast.makeText(context, "Wrong password!", Toast.LENGTH_SHORT).show();
                db.close();
                return -1;
            }
        }
        else{
            Toast.makeText(context, "This username does not exist", Toast.LENGTH_SHORT).show();
            db.close();
            return -1;
        }
    }

    public String getCustomer(int customer_id){
        SQLiteDatabase db = getReadableDatabase();

        String query = String.format(Locale.UK, "SELECT * FROM Customer WHERE customer_id=%d", customer_id);

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        return c.getString(2);
    }

    public void sellProperty(Property property){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = String.format(Locale.UK, "INSERT INTO Property VALUES(%d, %d, \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", %d, %d, %d, \"%s\", %d, \"%s\", \"%s\", %d, \"%s\", \"%s\")", property.getProperty_id(), property.getBedroom_count(), property.getHouse_no(), property.getStreet(), property.getDistrict(), property.getCity(), property.getState(), property.getPincode(), property.getRent(), property.getSelling_price(), property.getStatus(), property.getConstruction_year(), property.getCategory(), property.getType(), property.getArea_size(), property.getDateListed(), property.getPropertyName());

        String query1 = String.format(Locale.UK, "INSERT INTO Assign VALUES(%d, %d)", property.getProperty_id(), (int) ((Math.random() * (18 - 1)) + 1));

        db.execSQL(query);
        db.execSQL(query1);
    }

    public void buyProperty(Transactions transactions, int property_id, int status){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = String.format(Locale.UK, "INSERT INTO Transactions VALUES(%d, %d, %d, \"%s\", \"%s\", %d)", transactions.getTransaction_id(), transactions.getAgent_id(),transactions.getCustomer_id(),transactions.getDateFrom(),transactions.getDateTo(),transactions.getAmount());

        String query1 = String.format(Locale.UK, "UPDATE Property SET status=%d WHERE property_id=%d", status,property_id);

        db.execSQL(query);
        db.execSQL(query1);
    }

    @SuppressLint("Range")
    public List<Property> getData(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM Property";

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        List<Property> propertyList = new ArrayList<>();

        while (c.moveToNext()){
            Property property;
            if(c.getString(c.getColumnIndexOrThrow("type")).equals("lease")) {
                property = new Property(c.getInt(0), c.getString(c.getColumnIndexOrThrow("p_name")), c.getString(c.getColumnIndexOrThrow("type")), c.getInt(c.getColumnIndexOrThrow("area_size")), c.getInt(c.getColumnIndexOrThrow("no_of_bedrooms")), c.getString(c.getColumnIndexOrThrow("category")), c.getInt(c.getColumnIndexOrThrow("year_of_const")), c.getInt(c.getColumnIndexOrThrow("rent")), c.getInt(c.getColumnIndexOrThrow("rent")), c.getString(c.getColumnIndexOrThrow("status")), c.getString(c.getColumnIndexOrThrow("house_no")), c.getString(c.getColumnIndexOrThrow("street")), c.getString(c.getColumnIndexOrThrow("district")), c.getString(c.getColumnIndexOrThrow("city")), c.getString(c.getColumnIndexOrThrow("state")), c.getInt(c.getColumnIndexOrThrow("pincode")), c.getString(c.getColumnIndexOrThrow("dateListed")));
            }
            else{
                property = new Property(c.getInt(0), c.getString(c.getColumnIndexOrThrow("p_name")), c.getString(c.getColumnIndexOrThrow("type")), c.getInt(c.getColumnIndexOrThrow("area_size")), c.getInt(c.getColumnIndexOrThrow("no_of_bedrooms")), c.getString(c.getColumnIndexOrThrow("category")), c.getInt(c.getColumnIndexOrThrow("year_of_const")), c.getInt(c.getColumnIndexOrThrow("selling_price")), c.getInt(c.getColumnIndexOrThrow("selling_price")), c.getString(c.getColumnIndexOrThrow("status")), c.getString(c.getColumnIndexOrThrow("house_no")), c.getString(c.getColumnIndexOrThrow("street")), c.getString(c.getColumnIndexOrThrow("district")), c.getString(c.getColumnIndexOrThrow("city")), c.getString(c.getColumnIndexOrThrow("state")), c.getInt(c.getColumnIndexOrThrow("pincode")), c.getString(c.getColumnIndexOrThrow("dateListed")));
            }
            propertyList.add(property);
        }

        db.close();

        return propertyList;
    }

    public List<String> getLocations(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format(Locale.UK, "SELECT DISTINCT city FROM Property");

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        List<String> locations = new ArrayList<>();

        locations.add(c.getString(0));

        while (c.moveToNext()){
            locations.add(c.getString(0));
        }

        return locations;
    }

    @SuppressLint("Range")
    public List<Property> getFilteredData(String city, String type, int bedrooms, String category, int minPrice, int maxPrice){
        SQLiteDatabase db = this.getReadableDatabase();

        String query;
        if(type.equals("lease")) {
            query = String.format(Locale.UK, "SELECT * FROM Property WHERE city=\"%s\" AND type=\"%s\" AND no_of_bedrooms=%d AND category=\"%s\" AND rent >= %d AND rent <= %d", city, type, bedrooms, category, minPrice, maxPrice);
        }
        else {
            query = String.format(Locale.UK, "SELECT * FROM Property WHERE city=\"%s\" AND type=\"%s\" AND no_of_bedrooms=%d AND category=\"%s\" AND selling_price >= %d AND selling_price <= %d", city, type, bedrooms, category, minPrice, maxPrice);
        }

        Cursor c = db.rawQuery(query, null);

        System.out.println(c.getCount()+"");

        c.moveToFirst();

        List<Property> propertyList = new ArrayList<>();

        while (true){
            Property property;
            if(c.getCount() > 0) {
                if (c.getString(c.getColumnIndexOrThrow("type")).equals("lease")) {
                    property = new Property(c.getInt(0), c.getString(c.getColumnIndexOrThrow("p_name")), c.getString(c.getColumnIndexOrThrow("type")), c.getInt(c.getColumnIndexOrThrow("area_size")), c.getInt(c.getColumnIndexOrThrow("no_of_bedrooms")), c.getString(c.getColumnIndexOrThrow("category")), c.getInt(c.getColumnIndexOrThrow("year_of_const")), c.getInt(c.getColumnIndexOrThrow("rent")), c.getInt(c.getColumnIndexOrThrow("rent")), c.getString(c.getColumnIndexOrThrow("status")), c.getString(c.getColumnIndexOrThrow("house_no")), c.getString(c.getColumnIndexOrThrow("street")), c.getString(c.getColumnIndexOrThrow("district")), c.getString(c.getColumnIndexOrThrow("city")), c.getString(c.getColumnIndexOrThrow("state")), c.getInt(c.getColumnIndexOrThrow("pincode")), c.getString(c.getColumnIndexOrThrow("dateListed")));
                } else {
                    property = new Property(c.getInt(0), c.getString(c.getColumnIndexOrThrow("p_name")), c.getString(c.getColumnIndexOrThrow("type")), c.getInt(c.getColumnIndexOrThrow("area_size")), c.getInt(c.getColumnIndexOrThrow("no_of_bedrooms")), c.getString(c.getColumnIndexOrThrow("category")), c.getInt(c.getColumnIndexOrThrow("year_of_const")), c.getInt(c.getColumnIndexOrThrow("selling_price")), c.getInt(c.getColumnIndexOrThrow("selling_price")), c.getString(c.getColumnIndexOrThrow("status")), c.getString(c.getColumnIndexOrThrow("house_no")), c.getString(c.getColumnIndexOrThrow("street")), c.getString(c.getColumnIndexOrThrow("district")), c.getString(c.getColumnIndexOrThrow("city")), c.getString(c.getColumnIndexOrThrow("state")), c.getInt(c.getColumnIndexOrThrow("pincode")), c.getString(c.getColumnIndexOrThrow("dateListed")));
                }
                propertyList.add(property);
            }
            if(!c.moveToNext()){
                break;
            }
        }

        db.close();

        return propertyList;

    }

    public Agent getPropertyAgent(Property property){
        Agent agent;

        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format(Locale.UK, "SELECT agent_id FROM Assign WHERE property_id=%d", property.getProperty_id());

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        int agent_id = c.getInt(0);

        String query2 = String.format(Locale.UK, "SELECT * FROM Agent WHERE agent_id=%d", agent_id);

        Cursor c2 = db.rawQuery(query2, null);

        c2.moveToFirst();

        agent = new Agent(c2.getInt(0), c2.getString(1), c2.getString(2), c2.getString(3), c2.getString(4), c2.getString(5));

        return agent;
    }

    public Agent getAgent(int agent_id){
        Agent agent;

        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format(Locale.UK, "SELECT * FROM Agent WHERE agent_id=%d", agent_id);

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();


        agent = new Agent(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));

        return agent;
    }


    public List<Agent> getAgentData(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM Agent";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        List<Agent> agentList = new ArrayList<>();
        while (c.moveToNext()){
                Agent agent= new Agent(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));
            agentList.add(agent);
        }

        return agentList;
    }

    public List<Property> getAgentsProp(Agent agent){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = String.format(Locale.UK, "SELECT * FROM Agent NATURAL JOIN Assign NATURAL JOIN Property WHERE agent_id =%d", agent.getAgent_id());

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        List<Property> agentsPropList = new ArrayList<>();

        while (c.moveToNext()){
            if(agentsPropList.isEmpty()){
                c.moveToFirst();
            }
            Property agentsProp= new Property(c.getInt(c.getColumnIndexOrThrow("property_id")), c.getString(c.getColumnIndexOrThrow("p_name")), c.getString(c.getColumnIndexOrThrow("type")), c.getInt(c.getColumnIndexOrThrow("area_size")), c.getInt(c.getColumnIndexOrThrow("no_of_bedrooms")), c.getString(c.getColumnIndexOrThrow("category")), c.getInt(c.getColumnIndexOrThrow("year_of_const")), c.getInt(c.getColumnIndexOrThrow("selling_price")), c.getInt(c.getColumnIndexOrThrow("selling_price")), c.getString(c.getColumnIndexOrThrow("status")), c.getString(c.getColumnIndexOrThrow("house_no")), c.getString(c.getColumnIndexOrThrow("street")), c.getString(c.getColumnIndexOrThrow("district")), c.getString(c.getColumnIndexOrThrow("city")), c.getString(c.getColumnIndexOrThrow("state")), c.getInt(c.getColumnIndexOrThrow("pincode")), c.getString(c.getColumnIndexOrThrow("dateListed")));
            agentsPropList.add(agentsProp);
        }

        return agentsPropList;
    }

}
