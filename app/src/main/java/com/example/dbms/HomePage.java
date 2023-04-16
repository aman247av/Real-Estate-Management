package com.example.dbms;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbms.Database.RealEstateDatabaseHelper;
import com.example.dbms.Model.Property;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HomePage extends AppCompatActivity {

    RecyclerView recyclerView;

    RecyclerView.Adapter adapter;

    RealEstateDatabaseHelper db;

    TextView tvSearchBar,fabBtnSell;
    public static final String FILENAME = "com.example.dbms.LoginType";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        String agent=getIntent().getStringExtra("agent_id");

        getSupportActionBar().hide();

        tvSearchBar = findViewById(R.id.tvSearch);

        recyclerView = findViewById(R.id.rvProperty);
        fabBtnSell = findViewById(R.id.tvPreview);

        fabBtnSell.setOnClickListener(view -> {
            startActivity(new Intent(this,SellPropActivity.class));
        });

        SharedPreferences preferences=getSharedPreferences(FILENAME,MODE_PRIVATE);

        String agent_id=preferences.getString("login_id",agent);
        String loginType=preferences.getString("loginType",null);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        db = new RealEstateDatabaseHelper(this);

        if(!loginType.equals("customer")){
            fabBtnSell.setVisibility(View.INVISIBLE);
            tvSearchBar.setVisibility(View.INVISIBLE);
        }

        if(agent!=null || loginType.equals("agent") ){
            if(agent!=null){
                agent_id=agent;
            }
            List<Property> propertyList = db.getAgentsProp(db.getAgent(Integer.parseInt(agent_id)));
            adapter = new HomePageAdapter(this, propertyList);
            recyclerView.setAdapter(adapter);


        } else if (loginType.equals("customer")) {

            List<Property> propertyList = db.getData();
            List<Property> unsoldProperty = new ArrayList<>();

            for (int i = 0; i < propertyList.size(); i++){
                if(Integer.parseInt(propertyList.get(i).getStatus()) == 0){
                    unsoldProperty.add(propertyList.get(i));
                }
            }

            adapter = new HomePageAdapter(this, unsoldProperty);
            recyclerView.setAdapter(adapter);
            tvSearchBar.setOnClickListener(v -> {
                Intent intent = new Intent(HomePage.this, Filters.class);
                startActivityForResult(intent, 0);
            });
        } else if (loginType.equals("admin")) {

            List<Property> propertyList = db.getData();
            adapter = new HomePageAdapter(this, propertyList);
            recyclerView.setAdapter(adapter);

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == 0){
                if(data != null){
                    Bundle map = data.getBundleExtra("filterDetails");

                    List<Property> filteredData = db.getFilteredData(map.getString("city"), map.getString("Purpose"), Integer.parseInt(map.getString("Bedrooms")), map.getString("Type"), Integer.parseInt(map.getString("minBudget")), Integer.parseInt(map.getString("maxBudget")));

                    System.out.println(filteredData);

                    adapter = new HomePageAdapter(this, filteredData);

                    recyclerView.setAdapter(adapter);
                }
            }
        }
    }
}