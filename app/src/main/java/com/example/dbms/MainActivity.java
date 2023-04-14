package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    CardView customerCardView;

    CardView agentCardView;
    private Button btnAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customerCardView = findViewById(R.id.customerCardView);
        agentCardView = findViewById(R.id.agentCardView);
        btnAdmin = findViewById(R.id.btnAdm);

        btnAdmin.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            i.putExtra("loginType", "admin");
            startActivity(i);
        });


        customerCardView.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            i.putExtra("loginType", "customer");
            startActivity(i);
        });

        agentCardView.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            i.putExtra("loginType", "agent");
            startActivity(i);
        });
    }

}