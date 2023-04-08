package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BillingActivity extends AppCompatActivity {
    FloatingActionButton fabCLose;
    TextView tvTransactionId,tvAgent,tvDate,tvAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        fabCLose=findViewById(R.id.fabClose);
        tvTransactionId=findViewById(R.id.tvTransactionId);
        tvAgent=findViewById(R.id.tvAgentId);
        tvDate=findViewById(R.id.tvDate);
        tvAmount=findViewById(R.id.tvAmount);

        fabCLose.setOnClickListener(view -> {

        });

        tvTransactionId.setText("14145");
        tvAgent.setText("Aman Verma");
        tvDate.setText("24-09-2002");
        tvAmount.setText("$30000");







    }
}