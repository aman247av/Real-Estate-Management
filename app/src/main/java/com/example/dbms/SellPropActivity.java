package com.example.dbms;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SellPropActivity extends AppCompatActivity {
    private final static int MY_REQUEST_CODE = 1;
    TextView tvSearchLoc;
    TextView tvRent, tvSell;
    Button btn_list;
    HashMap<String,String> hashMap=new HashMap<>();

    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_prop);

        tvSearchLoc = findViewById(R.id.tvSearchLoc);

        tvRent = findViewById(R.id.tvRent);
        tvSell = findViewById(R.id.tvSell);

        TextView tvHouse = findViewById(R.id.tvHouse);
        TextView tvFlat = findViewById(R.id.tvFlat);
        EditText etYOC = findViewById(R.id.etYOC);
        



        TextView tv_1BHK = findViewById(R.id.tv_1BHK);
        TextView tv_2BHK = findViewById(R.id.tv2BHK);
        TextView tv_3BHK = findViewById(R.id.tv_3BHK);

        btn_list = findViewById(R.id.btn_list);
        Button btn_reset = findViewById(R.id.btn_reset);
        EditText etPrice = findViewById(R.id.etPrice);
        EditText etArea = findViewById(R.id.etArea);
        EditText etPropName = findViewById(R.id.etPropertyName);

        
//        tvSell.setBackgroundResource(R.color.btnColor);
//        hashMap.put("type","buy");
//        tvHouse.setBackgroundResource(R.color.btnColor);
//        hashMap.put("category","house");
//        tv_1BHK.setBackgroundResource(R.color.btnColor);
//        hashMap.put("Bedrooms","1");

    
        final String[] type = {"sale"};
        tvSell.setOnClickListener(view -> {
            hashMap.put("type","buy");
            type[0] ="sale";
            tvSell.setBackgroundResource(R.color.btnColor);
            tvRent.setBackgroundResource(R.color.white);
        });

        tvRent.setOnClickListener(view -> {
            hashMap.put("type","rent");
            type[0]="rent";
            tvRent.setBackgroundResource(R.color.btnColor);
            tvSell.setBackgroundResource(R.color.white);
        });

        tvHouse.setOnClickListener(view -> {
            hashMap.put("category","house");
            tvHouse.setBackgroundResource(R.color.btnColor);
            tvFlat.setBackgroundResource(R.color.white);
        });

        tvFlat.setOnClickListener(view -> {
            hashMap.put("category","flat");
            tvFlat.setBackgroundResource(R.color.btnColor);
            tvHouse.setBackgroundResource(R.color.white);
        });

        tv_1BHK.setOnClickListener(view -> {
            hashMap.put("no_of_bedrooms","1");
            tv_1BHK.setBackgroundResource(R.color.btnColor);
            tv_2BHK.setBackgroundResource(R.color.white);
            tv_3BHK.setBackgroundResource(R.color.white);
        });

        tv_2BHK.setOnClickListener(view -> {
            hashMap.put("no_of_bedrooms","2");
            tv_2BHK.setBackgroundResource(R.color.btnColor);
            tv_1BHK.setBackgroundResource(R.color.white);
            tv_3BHK.setBackgroundResource(R.color.white);

        });

        tv_3BHK.setOnClickListener(view -> {
            hashMap.put("no_of_bedrooms","3");

            tv_1BHK.setBackgroundResource(R.color.white);
            tv_2BHK.setBackgroundResource(R.color.white);
            tv_3BHK.setBackgroundResource(R.color.btnColor);
        });



        ProgressDialog progressDialog = new ProgressDialog(SellPropActivity.this);
        progressDialog.setMessage("Listing Property...");
        progressDialog.setCancelable(false);



        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean check=true;

                if(etPropName.getText().toString().isEmpty()){
                    etPropName.requestFocus();
                }else{
                    //propertyName
                    hashMap.put("p_name",etPropName.getText().toString());
                }

                if(tvSearchLoc.getText().toString().isEmpty()){
                    tvSearchLoc.requestFocus();
                }else{
                    //address (Partially)
                    String[] address=tvSearchLoc.getText().toString().split(",");
                    if(address.length!=6){
                        Toast.makeText(SellPropActivity.this, "Invalid Format", Toast.LENGTH_SHORT).show();
                        tvSearchLoc.requestFocus();
                    }
                    else{
                        hashMap.put("house_no",address[0]);
                        hashMap.put("street",address[1]);
                        hashMap.put("district",address[2]);
                        hashMap.put("city",address[3]);
                        hashMap.put("state",address[4]);
                        hashMap.put("pincode",address[5]);
                    }
                }


                if(!hashMap.containsKey("type")){
                    Toast.makeText(SellPropActivity.this, "Purpose not set!", Toast.LENGTH_SHORT).show();
                }



                //rent (Done)
                if(etPrice.getText().toString().isEmpty()){
                    etPrice.requestFocus();
                }
                else {
                    if (type[0].equals("sale")) {
                        hashMap.put("selling_price", etPrice.getText().toString());
                        hashMap.put("rent", String.valueOf((Integer.parseInt(etPrice.getText().toString()) * 0.002)));
                    } else {
                        hashMap.put("rent", etPrice.getText().toString());
                        hashMap.put("selling_price", String.valueOf((Integer.parseInt(etPrice.getText().toString()) * 20)));
                    }
                }

                //status (Done)
                hashMap.put("status", String.valueOf(0));

                if(etYOC.getText().toString().isEmpty()){
                    etYOC.requestFocus();
                    check=false;
                }else {
                    //YOC
                    hashMap.put("year_of_const", etYOC.getText().toString());
                }
                //category (Above Done)

                //type (Above)
                if(etArea.getText().toString().isEmpty()){
                    etArea.requestFocus();
                }else {
                    //area
                    hashMap.put("area_size",etArea.getText().toString());
                }

                //dateList
                Date cDate = new Date();
                String fDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);
                hashMap.put("dateList",fDate);

                if(etPropName.getText().toString().isEmpty()){
                    etPropName.requestFocus();
                }else {
                    //propertyName
                    hashMap.put("p_name",etPropName.getText().toString());
                }




                System.out.println(hashMap);

                if(hashMap.size()!=16){
                    Toast.makeText(SellPropActivity.this, "Incomplete Details!", Toast.LENGTH_SHORT).show();
                }
                else{



                    Toast.makeText(SellPropActivity.this, "All OK", Toast.LENGTH_SHORT).show();


                    progressDialog.show();
                    long duration= TimeUnit.SECONDS.toMillis(2);
                    new CountDownTimer(duration, 1000) {
                        @Override
                        public void onTick(long l) {
                            String sDuration = String.format(Locale.ENGLISH, "%02d"
                                    , TimeUnit.MILLISECONDS.toSeconds(l));
                        }

                        @Override
                        public void onFinish() {

                            progressDialog.dismiss();
                            startActivity(new Intent(SellPropActivity.this,ListingActivity.class));
                            finish();
                        }
                    }.start();


                }
                
                

                
//                String[] minBudget = spinner_min_budget.getSelectedItem().toString().split(" ");
//                String[] maxBudget = spinner_max_budget.getSelectedItem().toString().split(" ");
//
//                if(!tvSearchLoc.getText().toString().isEmpty()) {
//                    if (Integer.parseInt(minBudget[minBudget.length - 1]) < Integer.parseInt(maxBudget[maxBudget.length - 1])) {
//                        hashMap.put("minBudget", minBudget[minBudget.length - 1]);
//                        hashMap.put("maxBudget", maxBudget[maxBudget.length - 1]);
//                        hashMap.put("city", tvSearchLoc.getText().toString().trim());
//
//                        System.out.println(bundle);
//
//                        Intent intent = new Intent();
//                        intent.putExtra("filterDetails", bundle);
//                        setResult(RESULT_OK, intent);
//                        finish();
//
//                    } else {
//                        Toast.makeText(Filters.this, "Invalid Budget Range!!!", Toast.LENGTH_SHORT).show();
//                    }
//                }else{
//                    Toast.makeText(Filters.this, "Invalid Location", Toast.LENGTH_SHORT).show();
//                }
            }
        });


        btn_reset.setOnClickListener(view -> {
            finish();
            Intent i=new Intent(this,SellPropActivity.class);
            startActivity(i);
        });


    }


}