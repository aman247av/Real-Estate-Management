package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.dbms.Database.RealEstateDatabaseHelper;
import com.example.dbms.Model.Location;
import com.example.dbms.Model.Property;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FilterLocation extends AppCompatActivity implements OnItemClick{

    private RecyclerView RVLocation;
    private LocationAdapter adapter;
    private List<String> locationModelArrayList;
    private RealEstateDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_location2);

        db = new RealEstateDatabaseHelper(this);

        RVLocation = findViewById(R.id.idRVLocation);
        buildRecyclerView();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {

        List<String> filteredlist = new ArrayList<>();

        for (String item : locationModelArrayList) {

            if (item.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            adapter.filterList(filteredlist);
        }
    }

    private void buildRecyclerView() {

        locationModelArrayList = db.getLocations();

//        db query fetching all locations


        adapter = new LocationAdapter(locationModelArrayList, FilterLocation.this,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        RVLocation.setHasFixedSize(true);

        RVLocation.setLayoutManager(manager);
        RVLocation.setAdapter(adapter);
    }

    @Override
    public void onClick(String value) {
        Intent intent = new Intent();
        intent.putExtra("value", value);
        setResult(RESULT_OK, intent);
        finish();
    }
}