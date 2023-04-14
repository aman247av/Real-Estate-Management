package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dbms.Database.RealEstateDatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BillingActivity extends AppCompatActivity {
    FloatingActionButton fabCLose;
    TextView tvTransactionId,tvAgent,tvDate,tvAmount;
    private TextView tvOwner;

    RealEstateDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        fabCLose=findViewById(R.id.fabClose);
        tvOwner=findViewById(R.id.tvOwner);
        tvTransactionId=findViewById(R.id.tvTransactionId);
        tvAgent=findViewById(R.id.tvAgentId);
        tvDate=findViewById(R.id.tvDate);
        tvAmount=findViewById(R.id.tvAmount);
        db = new RealEstateDatabaseHelper(this);

        fabCLose.setOnClickListener(view -> {
            finish();
        });

        String transaction_id = getIntent().getStringExtra("transaction_id");
        String agent = getIntent().getStringExtra("agent_name");
        String date = getIntent().getStringExtra("purchase_date");
        String amount = getIntent().getStringExtra("amount");
        String customer_id = getIntent().getStringExtra("customer_id");

        String buyerName = db.getCustomer(Integer.parseInt(customer_id));

        tvOwner.setText(buyerName.trim());
        tvTransactionId.setText(transaction_id.trim());
        tvAgent.setText(agent.trim());
        tvDate.setText(date.trim());
        tvAmount.setText(amount+"");
    }
}