package com.example.electrictalk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.electrictalk.R;

public class EditCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car);
    }

    public void OnSaveCar(View view) {
        Intent myInt2= new Intent(EditCarActivity.this,OneCarActivity.class);
        startActivity(myInt2);
    }
}
