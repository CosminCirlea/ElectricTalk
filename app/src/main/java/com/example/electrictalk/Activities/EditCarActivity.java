package com.example.electrictalk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.R;
import com.google.gson.Gson;

public class EditCarActivity extends AppCompatActivity {

    private EditText companyEt, modelEt, revisionEt, autonomyEt, yearEt, batteryEt;
    private CarModel car;
    private String company,model,date;
    private int year, autonomy, battery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car);
        initializeViews();

        Gson gson = new Gson();
        car = gson.fromJson(getIntent().getStringExtra("car_model"), CarModel.class);
        setEditData(car);
    }

    private void setEditData(CarModel car)
    {
        company = car.getCompany();
        model = car.getModel();
        date = car.getLastTechRevision();
        year = car.getYear();
        battery = car.getBatteryLeft();
        autonomy = car.getAutonomy();

        companyEt.setText(company);
        modelEt.setText(model);
        revisionEt.setText(date.substring(0,10));
        autonomyEt.setText(String.valueOf(autonomy));
        yearEt.setText(String.valueOf(year));
        batteryEt.setText(String.valueOf(battery));
    }

    private void getCarUpdate()
    {
        car.setCompany(companyEt.getText().toString());
        car.setModel(modelEt.getText().toString());
        car.setYear(Integer.valueOf(yearEt.getText().toString()));
        car.setLastTechRevision(revisionEt.getText().toString());
        car.setBatteryLeft(Integer.valueOf(batteryEt.getText().toString()));
        car.setAutonomy(Integer.valueOf(autonomyEt.getText().toString()));
    }

    private void initializeViews()
    {
        companyEt = findViewById(R.id.tv_company);
        modelEt = findViewById(R.id.tv_model);
        revisionEt = findViewById(R.id.tv_revision2);
        autonomyEt = findViewById(R.id.tv_autonomy2);
        yearEt = findViewById(R.id.tv_year2);
        batteryEt = findViewById(R.id.tv_procent);
    }

    public void OnSaveCar(View view) {
        getCarUpdate();
        HttpClientManager.getInstance().updateCar(car.getId(), car, new HttpClientManager.OnDataReceived<CarModel>() {
            @Override
            public void dataReceived(CarModel data) {
                CarModel aux = data;
                finish();
            }

            @Override
            public void onFailed() {

            }
        });
//        Intent myInt2= new Intent(EditCarActivity.this,OneCarActivity.class);
//        startActivity(myInt2);
    }
}
