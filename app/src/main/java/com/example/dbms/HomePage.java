package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class HomePage extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList propImgList = new ArrayList<>(Arrays.asList(R.drawable.flats_24));
    ArrayList propDetailsList = new ArrayList<>(Arrays.asList("Aprna Flats","Sema House","SaiRam Flats"));

    SearchView searchView;
    TextView tvSearchtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getSupportActionBar().hide();


        tvSearchtxt=findViewById(R.id.txSearch);
        tvSearchtxt.setText("HELLO");
        tvSearchtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomePage.this,Filters.class);
                startActivity(i);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rvProperty);

        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        HomePageAdapter adapter = new HomePageAdapter(HomePage.this, propImgList, propDetailsList);
        recyclerView.setAdapter(adapter);


    }
}