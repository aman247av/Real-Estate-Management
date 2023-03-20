package com.example.dbms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ViewHolder> {
    ArrayList propImgList, propDetailsList;
    Context context;


    public HomePageAdapter(Context context, ArrayList propImgList, ArrayList propDetailsList) {
        this.context = context;
        this.propImgList = propImgList;
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

//        int res = (int) propDetailsList.get(position);
//            holder.tvPropertName.setText(propDetailsList.get(res));
    }

    @Override
    public int getItemCount() {

        return propDetailsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProperty;
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


        }
    }
}
