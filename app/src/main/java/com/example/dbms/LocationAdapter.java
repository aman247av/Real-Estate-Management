package com.example.dbms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbms.Model.Location;
import com.example.dbms.Model.Property;

import java.util.ArrayList;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {


    private OnItemClick mCallback;
    private List<String> locationModelArrayList;


    public LocationAdapter(List<String> locationModelArrayList, Context context,OnItemClick listener) {
        this.locationModelArrayList = locationModelArrayList;
        mCallback = listener;
    }


    public void filterList(List<String> filterlist) {

        locationModelArrayList = filterlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.ViewHolder holder, int position) {

        String model = locationModelArrayList.get(position);
        holder.locName.setText(model);
        holder.locName.setOnClickListener(view -> {mCallback.onClick(model); holder.locName.setBackgroundResource(R.color.btnColor);});

    }

    @Override
    public int getItemCount() {

        return locationModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView locName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            locName = itemView.findViewById(R.id.item_loc);

        }
    }
}
