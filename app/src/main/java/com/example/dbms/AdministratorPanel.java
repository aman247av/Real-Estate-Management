package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdministratorPanel extends AppCompatActivity {

    private CardView cvAgent,cvProperty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_panel);

        cvAgent=findViewById(R.id.cvAgent);
        cvProperty=findViewById(R.id.cvProperty);

        cvAgent.setOnClickListener(view -> {
            startActivity(new Intent(this,AgentDisplayActivity.class));
        });

        cvProperty.setOnClickListener(view -> {
            Intent i=new Intent(this,HomePage.class);
            startActivity(i);
        });




    }
}