package com.example.electrictalk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.electrictalk.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void OnSignUp(View view) {
        Intent myInt2= new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(myInt2);
    }

    public void OnSignIn(View view) {
        Intent myInt2= new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(myInt2);
    }
}
