package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class Filters extends AppCompatActivity {

    TextView tvSearchLoc;
    TextView tvRent, tvBuy;
    Spinner spinner_max_budget, spinner_min_budget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters2);

        tvSearchLoc = findViewById(R.id.tvSearchLoc);

        tvRent = findViewById(R.id.tvRent);
        tvBuy = findViewById(R.id.tvBuy);

        TextView tvHouse = findViewById(R.id.tvHouse);
        TextView tvFlat = findViewById(R.id.tvFlat);

        spinner_min_budget = findViewById(R.id.spinner_min_budget);
        spinner_max_budget = findViewById(R.id.spinner_max_budget);

        TextView tv_1BHK = findViewById(R.id.tv_1BHK);
        TextView tv_2BHK = findViewById(R.id.tv2BHK);
        TextView tv_3BHK = findViewById(R.id.tv_3BHK);


        // Create an array of minimum budget values
        String[] minBudgets = {"$100", "$200", "$300", "$400", "$500", "More than $500"};

// Create an array of maximum budget values
        String[] maxBudgets = {"$500", "$1000", "$2000", "$3000", "$4000", "More than $4000"};

// Create an adapter for the spinners
        ArrayAdapter<String> minAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, minBudgets);
        minAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_min_budget.setAdapter(minAdapter);

// Create an adapter for the maximum budget spinner
        ArrayAdapter<String> maxAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, maxBudgets);
        maxAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_max_budget.setAdapter(maxAdapter);


        String minBudget = spinner_min_budget.getSelectedItem().toString();
        String maxBudget = spinner_max_budget.getSelectedItem().toString();

    }

}