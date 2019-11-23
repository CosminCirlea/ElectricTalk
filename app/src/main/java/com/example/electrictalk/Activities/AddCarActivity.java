package com.example.electrictalk.Activities;


import android.os.Bundle;
import android.view.View;

import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCarActivity extends BaseAppCompat {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
    }

    public void addCar(View view) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        HttpClientManager.getInstance().addCar("X3", "BMW", 2013, 400, 32, formatter.format(date), new HttpClientManager.OnDataReceived<CarModel>() {
            @Override
            public void dataReceived(CarModel data) {
                int a =0;
            }

            @Override
            public void onFailed() {

            }
        });
    }
}
