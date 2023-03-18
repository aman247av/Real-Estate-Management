package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tvName, tvAge;
    Button btSave, btCreateTable;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btSave = findViewById(R.id.button);
        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        btCreateTable = findViewById(R.id.createTable);

        db = openOrCreateDatabase("Demo", MODE_PRIVATE, null);

        btSave.setOnClickListener(v -> {
            saveData(tvName.getText().toString(), Integer.parseInt(tvAge.getText().toString()));
        });

        btCreateTable.setOnClickListener(v -> {
            createTable("Table2");
        });
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