package com.example.dbms;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbms.Model.Property;

import java.util.List;


public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ViewHolder> {
    List<Property> propDetailsList;
    Context context;


    public HomePageAdapter(Context context, List<Property> propDetailsList) {
        this.context = context;
        this.propDetailsList = propDetailsList;
    }

    @NonNull
    @Override
    public HomePageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_property, parent, false);
        HomePageAdapter.ViewHolder viewHolder = new HomePageAdapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull HomePageAdapter.ViewHolder holder, int position) {
        holder.tvPrice.setText("₹" + propDetailsList.get(position).getSelling_price() + "");
        holder.tvCategory.setText(propDetailsList.get(position).getCategory());
        holder.tvType.setText(propDetailsList.get(position).getType());
        holder.tvPropertName.setText(propDetailsList.get(position).getPropertyName());
        holder.tvAreaAbout.setText(propDetailsList.get(position).getArea_size() + " sq. m");
        holder.tvAddress.setText(propDetailsList.get(position).getHouse_no() + ", " + propDetailsList.get(position).getStreet() + ", " + propDetailsList.get(position).getDistrict() + ", " + propDetailsList.get(position).getCity());

        holder.whole_btn.setOnClickListener(v -> {
            Intent intent = new Intent(context, PropertyDetails.class);
            intent.putExtra("property_id", propDetailsList.get(position).getProperty_id());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {

        return propDetailsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProperty;
        RelativeLayout whole_btn;
        TextView tvPrice,tvCategory,tvType,tvPropertName,tvAreaAbout,tvAddress;
        public ViewHolder(View view) {
            super(view);
            ivProperty = (ImageView) view.findViewById(R.id.ivProperty);
            tvPrice = (TextView) view.findViewById(R.id.tvPrice);
            tvCategory = (TextView) view.findViewById(R.id.tvCategory);
            tvType = (TextView) view.findViewById(R.id.tvType);
            tvPropertName = (TextView) view.findViewById(R.id.tvPropertName);
            tvAreaAbout = (TextView) view.findViewById(R.id.tvAreaAbout);
            tvAddress = (TextView) view.findViewById(R.id.tvAddress);
            whole_btn = view.findViewById(R.id.whole_btn);

        }
    }
}
