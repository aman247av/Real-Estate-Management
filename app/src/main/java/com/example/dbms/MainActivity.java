package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tvName, tvAge;
    Button btSave, btCreateTable;

    SQLiteDatabase db;

    CardView customerCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customerCardView=findViewById(R.id.customerCardView);

        customerCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,HomePage.class);
                startActivity(i);
            }
        });


//
//        db = openOrCreateDatabase("Demo", MODE_PRIVATE, null);
//
//        btSave.setOnClickListener(v -> {
//            saveData(tvName.getText().toString(), Integer.parseInt(tvAge.getText().toString()));
//        });
//
//        btCreateTable.setOnClickListener(v -> {
//            createTable("Table2");
//        });
    }

    public void saveData(String name, int age){

        String query = String.format(Locale.UK,"INSERT INTO MyTable VALUES (\"%s\", %d);", name, age);

        db.execSQL(query);
    }

    public void createTable(String name){
        String query = String.format(Locale.UK, "CREATE TABLE %s(mobile int(22), gender varchar);", name);

        db.execSQL(query);
    }
}