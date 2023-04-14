package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dbms.Database.RealEstateDatabaseHelper;
import com.example.dbms.Model.Agent;
import com.example.dbms.Model.Property;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AgentDisplayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    RealEstateDatabaseHelper db;
    private AgentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_display);

        recyclerView = findViewById(R.id.rvProperty);


        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        db = new RealEstateDatabaseHelper(this);

        List<Agent> propertyList = db.getAgentData();

        adapter = new AgentAdapter(this, propertyList);

        recyclerView.setAdapter(adapter);

    }
}