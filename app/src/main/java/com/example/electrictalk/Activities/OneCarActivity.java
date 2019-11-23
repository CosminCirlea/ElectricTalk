package com.example.electrictalk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.R;
import com.google.gson.Gson;

import java.util.UUID;

public class OneCarActivity extends AppCompatActivity {

    private UUID carId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_car);

        Gson gson = new Gson();
        String aux = getIntent().getStringExtra("car_id");
        carId = gson.fromJson(aux, UUID.class);

    }

}
