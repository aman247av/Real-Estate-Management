package com.example.dbms;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Filters extends AppCompatActivity {
    private final static int MY_REQUEST_CODE = 1;
    TextView tvSearchLoc;
    TextView tvRent, tvBuy;
    Button btnFilter;
    Spinner spinner_max_budget, spinner_min_budget;
    Bundle bundle = new Bundle();

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

        btnFilter = findViewById(R.id.btn_filter);

        tvSearchLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Filters.this,FilterLocation.class);
                startActivityForResult(i, MY_REQUEST_CODE);
            }
        });
        tvBuy.setBackgroundResource(R.color.btnColor);
        bundle.putString("Purpose","buy");
        tvHouse.setBackgroundResource(R.color.btnColor);
        bundle.putString("Type","house");
        tv_1BHK.setBackgroundResource(R.color.btnColor);
        bundle.putString("Bedrooms","1");


        tvBuy.setOnClickListener(view -> {
            bundle.putString("Purpose","buy");
            tvBuy.setBackgroundResource(R.color.btnColor);
            tvRent.setBackgroundResource(R.color.white);
        });

        tvRent.setOnClickListener(view -> {
            bundle.putString("Purpose","Rent");
            tvRent.setBackgroundResource(R.color.btnColor);
            tvBuy.setBackgroundResource(R.color.white);
        });

        tvHouse.setOnClickListener(view -> {
            bundle.putString("Type","house");
            tvHouse.setBackgroundResource(R.color.btnColor);
            tvFlat.setBackgroundResource(R.color.white);
        });

        tvFlat.setOnClickListener(view -> {
            bundle.putString("Type","flat");
            tvFlat.setBackgroundResource(R.color.btnColor);
            tvHouse.setBackgroundResource(R.color.white);
        });

        tv_1BHK.setOnClickListener(view -> {
            bundle.putString("Bedrooms","1");
            tv_1BHK.setBackgroundResource(R.color.btnColor);
            tv_2BHK.setBackgroundResource(R.color.white);
            tv_3BHK.setBackgroundResource(R.color.white);
        });

        tv_2BHK.setOnClickListener(view -> {
            bundle.putString("Bedrooms","2");
            tv_2BHK.setBackgroundResource(R.color.btnColor);
            tv_1BHK.setBackgroundResource(R.color.white);
            tv_3BHK.setBackgroundResource(R.color.white);

        });

        tv_3BHK.setOnClickListener(view -> {
            bundle.putString("Bedrooms","3");

            tv_1BHK.setBackgroundResource(R.color.white);
            tv_2BHK.setBackgroundResource(R.color.white);
            tv_3BHK.setBackgroundResource(R.color.btnColor);
        });



        String[] minBudgets = {"₹ 10000", "₹ 30000", "₹ 50000", "₹ 1000000", "₹ 2000000", "More than ₹ 5000000"};
        String[] maxBudgets = {"₹ 20000", "₹ 40000", "₹ 60000", "₹ 1500000", "₹ 4000000", "More than ₹ 8000000"};


        ArrayAdapter<String> minAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, minBudgets);
        minAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_min_budget.setAdapter(minAdapter);


        ArrayAdapter<String> maxAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, maxBudgets);
        maxAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_max_budget.setAdapter(maxAdapter);






        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] minBudget = spinner_min_budget.getSelectedItem().toString().split(" ");
                String[] maxBudget = spinner_max_budget.getSelectedItem().toString().split(" ");

                if(!tvSearchLoc.getText().toString().isEmpty()) {
                    if (Integer.parseInt(minBudget[minBudget.length - 1]) < Integer.parseInt(maxBudget[maxBudget.length - 1])) {
                        bundle.putString("minBudget", minBudget[minBudget.length - 1]);
                        bundle.putString("maxBudget", maxBudget[maxBudget.length - 1]);
                        bundle.putString("city", tvSearchLoc.getText().toString().trim());

                        System.out.println(bundle);

                        Intent intent = new Intent();
                        intent.putExtra("filterDetails", bundle);
                        setResult(RESULT_OK, intent);
                        finish();

                    } else {
                        Toast.makeText(Filters.this, "Invalid Budget Range!!!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Filters.this, "Invalid Location", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == MY_REQUEST_CODE) {
                if (data != null) {
                    tvSearchLoc.setText(data.getStringExtra("value"));
                }
            }
        }

    }

}