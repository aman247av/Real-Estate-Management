package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.dbms.Model.Location;

import java.util.ArrayList;

public class FilterLocation extends AppCompatActivity {

    private RecyclerView RVLocation;
    private LocationAdapter adapter;
    private ArrayList<Location> locationModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_location2);

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

        ArrayList<Location> filteredlist = new ArrayList<Location>();

        for (Location item : locationModelArrayList) {

            if (item.getLocationName().toLowerCase().contains(text.toLowerCase())) {
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

        locationModelArrayList = new ArrayList<Location>();

//        db query fetching all locations

        locationModelArrayList.add(new Location("Lucknow"));
        locationModelArrayList.add(new Location("GHY"));
        locationModelArrayList.add(new Location("NDLS"));
        locationModelArrayList.add(new Location("Banaras"));
        locationModelArrayList.add(new Location("Kashi"));


        adapter = new LocationAdapter(locationModelArrayList, FilterLocation.this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        RVLocation.setHasFixedSize(true);

        RVLocation.setLayoutManager(manager);
        RVLocation.setAdapter(adapter);
    }
}