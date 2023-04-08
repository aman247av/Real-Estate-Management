package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.dbms.Database.RealEstateDatabaseHelper;
import com.example.dbms.Model.Agent;
import com.example.dbms.Model.Property;

import java.util.List;
import java.util.Locale;

public class PropertyDetails extends AppCompatActivity {

    TextView tvPropertyName, tvLocation, tvPrice, tvCategory, tvType, tvBHK, tvArea, tvAboutAgent;

    RealEstateDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);

        tvPropertyName = findViewById(R.id.tvPropertyName);
        tvLocation = findViewById(R.id.tvLocation);
        tvPrice = findViewById(R.id.tvPriceDetails);
        tvCategory = findViewById(R.id.tvCategory);
        tvType = findViewById(R.id.tvType);
        tvBHK = findViewById(R.id.tvBHK);
        tvArea = findViewById(R.id.tvArea);
        tvAboutAgent = findViewById(R.id.tvAboutAgent);

        db = new RealEstateDatabaseHelper(this);

        int property_id = getIntent().getIntExtra("property_id", 0);

        List<Property> propertyList = db.getData();

        Property property = null;

        for (int i = 0; i < propertyList.size(); i++){
            if(propertyList.get(i).getProperty_id() == property_id){
                property = propertyList.get(i);
                break;
            }
        }

        tvPropertyName.setText(property.getPropertyName());
        tvType.setText(property.getType());
        tvPrice.setText("₹" + property.getSelling_price());
        tvLocation.setText(property.getHouse_no() + ", " + property.getStreet() + ", " + property.getDistrict() + ", " + property.getCity());
        tvCategory.setText(property.getCategory());
        tvBHK.setText(property.getBedroom_count() + "");
        tvArea.setText(property.getArea_size() + "");

        Agent agent = db.getPropertyAgent(property);

        String aboutAgent = String.format(Locale.UK, "Name : %s\nMobile : %s\nE-Mail : %s\nOffice address : %s", agent.getName(),agent.getContact(),agent.getE_mail(),agent.getOffice_address());

        tvAboutAgent.setText(aboutAgent);
    }
}