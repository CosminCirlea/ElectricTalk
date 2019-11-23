package com.example.electrictalk.Activities;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Helpers.StorageHelper;
import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCarActivity extends BaseAppCompat {
    private EditText companyEt, modelEt, batteryEt, yearEt, autonomyEt, lastTechEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        initializeViews();
    }

    public void addCar(View view) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));

        String company = companyEt.getText().toString();
        String model = modelEt.getText().toString();
        int battery = Integer.valueOf(batteryEt.getText().toString());
        int autonomy = Integer.valueOf(autonomyEt.getText().toString());
        int year = Integer.valueOf(yearEt.getText().toString());
        String lastRevision = lastTechEt.getText().toString();


        HttpClientManager.getInstance().addCar(model, company, year, autonomy, battery,"2019-11-23T08:30:04.093Z", new HttpClientManager.OnDataReceived<CarModel>() {
            @Override
            public void dataReceived(CarModel data) {
                StorageHelper.MyCarList.add(data);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    private void initializeViews(){
        companyEt = findViewById(R.id.et_company);
        modelEt = findViewById(R.id.et_model);
        batteryEt = findViewById(R.id.et_batteryLeft);
        yearEt = findViewById(R.id.et_year);
        autonomyEt = findViewById(R.id.et_autonomy);
        lastTechEt = findViewById(R.id.et_revision);
    }
}
