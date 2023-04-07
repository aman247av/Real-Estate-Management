package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dbms.Database.RealEstateDatabaseHelper;
import com.example.dbms.Model.Property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePage extends AppCompatActivity {

    RecyclerView recyclerView;

    RecyclerView.Adapter adapter;

    RealEstateDatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.rvProperty);

        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        db = new RealEstateDatabaseHelper(this);

        List<Property> propertyList = db.getData();

        adapter = new HomePageAdapter(this, propertyList);

        recyclerView.setAdapter(adapter);

    }
}