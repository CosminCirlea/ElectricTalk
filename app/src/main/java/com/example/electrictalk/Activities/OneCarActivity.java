package com.example.electrictalk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.R;
import com.google.gson.Gson;

import java.util.UUID;

public class OneCarActivity extends AppCompatActivity {

    private TextView companyNameTv, modelNameTv, batteryTv, yearTv, autonomyTv, lastRevisionTv;
    private UUID carId;
    private CarModel carModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_car);

        Gson gson = new Gson();
        String aux = getIntent().getStringExtra("car_id");
        carId = gson.fromJson(aux, UUID.class);
        initializeViews();

        HttpClientManager.getInstance().getCar(carId, new HttpClientManager.OnDataReceived<CarModel>() {
            @Override
            public void dataReceived(CarModel data) {
                setCarData(data);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    private void setCarData(CarModel carModel)
    {
        companyNameTv.setText(carModel.getCompany());
        yearTv.setText(carModel.getYear());
        modelNameTv.setText(carModel.getModel());
        yearTv.setText(carModel.getModel());
        batteryTv.setText(carModel.getBatteryLeft());
        autonomyTv.setText(carModel.getAutonomy());
        lastRevisionTv.setText(carModel.getLastTechRevision());
    }

    private void initializeViews()
    {
        companyNameTv = findViewById(R.id.tv_company);
        modelNameTv = findViewById(R.id.tv_model);
        batteryTv = findViewById(R.id.tv_procent);
        autonomyTv = findViewById(R.id.tv_autonomy2);
        yearTv = findViewById(R.id.tv_year2);
        lastRevisionTv = findViewById(R.id.tv_revision2);
    }

    public void OnEditCar(View view) {
        Intent myInt2= new Intent(OneCarActivity.this,EditCarActivity.class);
        startActivity(myInt2);
    }
}
