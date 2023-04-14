package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbms.Database.RealEstateDatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    TextView tvUsername, tvPassword;

    Button btSignIn;

    RealEstateDatabaseHelper db;

    public static final String FILENAME = "com.example.dbms.LoginType";

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
            }else if(loginType.equals("admin")){
                if(tvUsername.getText().toString().trim().equals("admin") && tvPassword.getText().toString().trim().equals("123")){
                    SharedPreferences.Editor editor = getSharedPreferences(FILENAME, MODE_PRIVATE).edit();
                    editor.putString("loginType", "admin");

                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, AdministratorPanel.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void loginCustomer(String username, String password){
        int customer_id = db.authenticateUser(this, "Customer",username, password);

        if(customer_id != -1){
            SharedPreferences.Editor editor = getSharedPreferences(FILENAME, MODE_PRIVATE).edit();
            editor.putString("loginType", "customer");
            editor.putString("login_id", String.valueOf(customer_id));
            editor.apply();
            Intent intent = new Intent(LoginActivity.this, HomePage.class);
            startActivity(intent);
        }
    }

    public void loginAgent(String username, String password){
        int agent_id = db.authenticateUser(this, "Agent", username, password);

        if(agent_id != -1){
            SharedPreferences.Editor editor = getSharedPreferences(FILENAME, MODE_PRIVATE).edit();
            editor.putString("loginType", "agent");
            editor.putString("login_id", String.valueOf(agent_id));
            editor.apply();
            Intent intent = new Intent(LoginActivity.this, HomePage.class);
            startActivity(intent);
        }
    }
}