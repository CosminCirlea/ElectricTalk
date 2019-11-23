package com.example.electrictalk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.R;
import com.google.gson.Gson;

public class EditCarActivity extends AppCompatActivity {

    private EditText companyEt, modelEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car);
        initializeViews();

        Gson gson = new Gson();
        CarModel car = gson.fromJson(getIntent().getStringExtra("car_model"), CarModel.class);
        setEditData(car);
    }

    private void setEditData(CarModel car)
    {
        companyEt.setText(car.getCompany());
        modelEt.setText(car.getModel());
    }

    private void initializeViews()
    {
        companyEt = findViewById(R.id.tv_company);
        modelEt = findViewById(R.id.tv_model);
    }

    public void OnSaveCar(View view) {
        Intent myInt2= new Intent(EditCarActivity.this,OneCarActivity.class);
        startActivity(myInt2);
    }
}
