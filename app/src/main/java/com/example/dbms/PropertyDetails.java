package com.example.dbms;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbms.Database.RealEstateDatabaseHelper;
import com.example.dbms.Model.Agent;
import com.example.dbms.Model.Property;
import com.example.dbms.Model.Transactions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class PropertyDetails extends AppCompatActivity {

    TextView tvPropertyName, tvLocation, tvPrice, tvCategory, tvType, tvBHK, tvArea, tvAboutAgent;

    TextView fabBuy;
    private final int[] flatsImageResources = {R.drawable.flat1, R.drawable.flat2, R.drawable.flat3, R.drawable.flat4,R.drawable.flat5};
    private final int[] housesImageResources = {R.drawable.house1, R.drawable.house2, R.drawable.house3, R.drawable.house4,R.drawable.house5};

    RealEstateDatabaseHelper db;

    public static final String FILENAME = "com.example.dbms.LoginType";

    TextView tvCall,tvEmail;
    private ImageView ivPropImag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);

        tvPropertyName = findViewById(R.id.tvPropertyName);
        ivPropImag = findViewById(R.id.ivProperty);
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
        int propImgIdx = getIntent().getIntExtra("property_img", 0);


        List<Property> propertyList = db.getData();

        Property property = null;

        for (int i = 0; i < propertyList.size(); i++){
            System.out.println(propertyList.get(i).getProperty_id() + " " + property_id);
            if(propertyList.get(i).getProperty_id() == property_id){
                property = propertyList.get(i);
                break;
            }
        }

        if(property.getCategory().equals("flat")){
            ivPropImag.setImageResource(flatsImageResources[propImgIdx]);
        }else{
            ivPropImag.setImageResource(housesImageResources[propImgIdx]);
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

        Property finalProperty = property;

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
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

                                    String transaction_id = String.format(Locale.UK, "%s%s", agent.getAgent_id(), dateFormat.format(new Date()));

                                    SharedPreferences preferences = getSharedPreferences(FILENAME, MODE_PRIVATE);
                                    String customer_id = preferences.getString("login_id", null);

                                    Transactions transactions;

                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                                    i.putExtra("transaction_id", transaction_id);
                                    i.putExtra("agent_name", agent.getName());
                                    i.putExtra("purchase_date", sdf.format(new Date()));
                                    i.putExtra("customer_id", customer_id);


                                    if(finalProperty.getType().equals("rent")){

                                        Calendar c = Calendar.getInstance();

                                        c.setTime(new Date());

                                        c.add(Calendar.MONTH, 6);


                                        String date = sdf.format(c.getTime());

                                        transactions = new Transactions(Integer.parseInt(transaction_id), agent.getAgent_id(), Integer.parseInt(customer_id), String.valueOf(LocalDate.now()), date, finalProperty.getRent());

                                        i.putExtra("amount", finalProperty.getRent());

                                        db.buyProperty(transactions);
                                    }
                                    else{
                                        transactions = new Transactions(Integer.parseInt(transaction_id), agent.getAgent_id(), Integer.parseInt(customer_id), String.valueOf(LocalDate.now()), "Present", finalProperty.getSelling_price());
                                        i.putExtra("amount", finalProperty.getSelling_price());
                                        db.buyProperty(transactions);
                                    }

                                    progressDialog.dismiss();
                                    finish();
                                    startActivity(i);
                                }
                            }.start();
                        }})
                    .setNegativeButton(android.R.string.no, null).show();
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