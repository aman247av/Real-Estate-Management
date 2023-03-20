package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    TextView tvUsername, tvPassword;

    Button btSignIn;

    RealEstateDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvUsername = findViewById(R.id.tvUsername);
        tvPassword = findViewById(R.id.tvPassword);
        btSignIn = findViewById(R.id.btSignIn);

        String loginType = getIntent().getStringExtra("loginType");

        db = new RealEstateDatabaseHelper(this);

        String username = tvUsername.getText().toString();
        String password = tvPassword.getText().toString();

        btSignIn.setOnClickListener(v -> {
            if(loginType.equals("customer")){
                loginCustomer(username, password);
            } else if (loginType.equals("agent")) {
                loginAgent(username, password);
            }
        });
    }

    public void loginCustomer(String username, String password){
        db.authenticateUser(this, username, password);
    }

    public void loginAgent(String username, String password){
        db.authenticateUser(this, username, password);
    }
}