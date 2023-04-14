package com.example.dbms;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.dbms.Database.RealEstateDatabaseHelper;
import com.example.dbms.Model.Agent;
import com.example.dbms.Model.Property;
import com.example.dbms.Model.Transactions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class PropertyDetails extends AppCompatActivity {

    TextView tvPropertyName, tvLocation, tvPrice, tvCategory, tvType, tvBHK, tvArea, tvAboutAgent;

    TextView fabBuy;

    RealEstateDatabaseHelper db;

    TextView tvCall,tvEmail;
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
        fabBuy = findViewById(R.id.fab_btn);

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

        fabBuy.setText(property.getType().toString().equals("rent")?"RENT":"BUY");

        tvPropertyName.setText(property.getPropertyName());
        tvType.setText(property.getType());
        tvPrice.setText("₹ " + property.getSelling_price());
        tvLocation.setText(property.getHouse_no() + ", " + property.getStreet() + ", " + property.getDistrict() + ", " + property.getCity());
        tvCategory.setText(property.getCategory());
        tvBHK.setText(property.getBedroom_count() + "");
        tvArea.setText(property.getArea_size() + " sq. m");

        Agent agent = db.getPropertyAgent(property);

        String aboutAgent = String.format(Locale.UK, "Name : %s\nMobile : %s\nE-Mail : %s\nOffice address : %s", agent.getName(),agent.getContact(),agent.getE_mail(),agent.getOffice_address());

        tvAboutAgent.setText(aboutAgent);

        tvCall=findViewById(R.id.tvCall);
        tvEmail=findViewById(R.id.tvEmail);

        ProgressDialog progressDialog = new ProgressDialog(PropertyDetails.this);
        progressDialog.setMessage("Processing Request...");
        progressDialog.setCancelable(false);

        fabBuy.setOnClickListener(view -> {
//            startActivity(new Intent(this,AgentDisplayActivity.class));
            new AlertDialog.Builder(this)
                    .setTitle("Proceed Further")
                    .setMessage("Do you really want to buy/rent this Property?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            Intent i=new Intent(PropertyDetails.this,BillingActivity.class);
                            progressDialog.show();
                            long duration= TimeUnit.SECONDS.toMillis(2);
                            new CountDownTimer(duration, 1000) {
                                @Override
                                public void onTick(long l) {
                                    String sDuration=String.format(Locale.ENGLISH,"%02d"
                                            , TimeUnit.MILLISECONDS.toSeconds(l));
                                }
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                @Override
                                public void onFinish() {
                                    String transaction_id = String.format(Locale.UK, "%s%s", agent.getAgent_id(), LocalDate.now());

                                    progressDialog.dismiss();
                                    finish();
                                    startActivity(i);
                                }
                            }.start();
                        }})
                    .setNegativeButton(android.R.string.no, null).show();
            Intent i=new Intent(this,BillingActivity.class);
            progressDialog.show();
            long duration= TimeUnit.SECONDS.toMillis(2);
            new CountDownTimer(duration, 1000) {
                @Override
                public void onTick(long l) {
                    String sDuration=String.format(Locale.ENGLISH,"%02d"
                            , TimeUnit.MILLISECONDS.toSeconds(l));
                }
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onFinish() {
                    String transaction_id = String.format(Locale.UK, "%s%s", agent.getAgent_id(), LocalDate.now());

//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime();
//
//                    Transactions transactions = new Transactions(transaction_id, agent.getAgent_id(), , String.valueOf(LocalDate.now()), )

                    progressDialog.dismiss();
                    finish();
                    startActivity(i);
                }
            }.start();
        });


        tvCall.setOnClickListener(view -> {
            String phno="";
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:+917317270278"));
            startActivity(callIntent);
        });

        tvEmail.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:recipient@example.com?subject=" + Uri.encode("Property Related Query"));
            intent.setData(data);
            startActivity(intent);
        });


    }
}