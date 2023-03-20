package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbms.Database.RealEstateDatabaseHelper;

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

        btSignIn.setOnClickListener(v -> {
            if(loginType.equals("customer")){
                loginCustomer(tvUsername.getText().toString().trim(), tvPassword.getText().toString().trim());
            } else if (loginType.equals("agent")) {
                loginAgent(tvUsername.getText().toString().trim(), tvPassword.getText().toString().trim());
            }
        });
    }

    public void loginCustomer(String username, String password){
        boolean loginSuccessful = db.authenticateUser(this, username, password);

        if(loginSuccessful){
            Intent intent = new Intent(LoginActivity.this, HomePage.class);
            startActivity(intent);
        }
    }

    public void loginAgent(String username, String password){
        boolean loginSuccessful = db.authenticateUser(this, username, password);

        if(loginSuccessful){

        }
    }
}