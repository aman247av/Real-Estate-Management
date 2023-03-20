package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class HomePage extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList propImgList = new ArrayList<>(Arrays.asList(R.drawable.flats_24));
    ArrayList propDetailsList = new ArrayList<>(Arrays.asList("Aprna Flats","Sema House","SaiRam Flats"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getSupportActionBar().hide();

        recyclerView = (RecyclerView) findViewById(R.id.rvProperty);

        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        HomePageAdapter adapter = new HomePageAdapter(HomePage.this, propImgList, propDetailsList);
        recyclerView.setAdapter(adapter);


    }
}