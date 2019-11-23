package com.example.electrictalk.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Helpers.StorageHelper;
import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
                carModel = data;
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
        yearTv.setText(String.valueOf(carModel.getYear()));
        modelNameTv.setText(carModel.getModel());
        batteryTv.setText(String.valueOf(carModel.getBatteryLeft()).concat("%"));
        autonomyTv.setText(String.valueOf(carModel.getAutonomy()));
        String date = carModel.getLastTechRevision().substring(0, 10);
        lastRevisionTv.setText(date);
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
        Gson gson = new Gson();
        String serializedCar = gson.toJson(carModel);
        myInt2.putExtra("car_model", serializedCar);
        startActivity(myInt2);
    }

    public void deleteCar(View view) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure you want to delete this car?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        HttpClientManager.getInstance().deleteCar(carId, new HttpClientManager.OnDataReceived() {
                            @Override
                            public void dataReceived(Object data) {
                                for (CarModel car : StorageHelper.MyCarList) {
                                    if (car.getId().equals(carId))
                                    {
                                        StorageHelper.MyCarList.remove(car);
                                    }
                                }
                                Toast.makeText(OneCarActivity.this, "Car deleted!", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onFailed() {

                            }
                        });
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
