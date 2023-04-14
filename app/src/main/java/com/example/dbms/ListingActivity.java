package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListingActivity extends AppCompatActivity {

    private TextView tvAddress,tvAgentName,tvPropertyId,tvAgentId,tvDate,tvAmount;
    FloatingActionButton fabCLose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);


        tvPropertyId=findViewById(R.id.tvTransactionId);
        tvAgentId=findViewById(R.id.tvAgentId);
        tvAgentName=findViewById(R.id.tvOwner);
        tvAddress=findViewById(R.id.tvAddress);
        tvDate=findViewById(R.id.tvDate);
        tvAmount=findViewById(R.id.tvAmount);
        fabCLose=findViewById(R.id.fabClose);


        fabCLose.setOnClickListener(view -> finish());
        tvAddress.setText("Agent's Address:\n"+"3123 Park Avenue, Guwahati");
    }
}