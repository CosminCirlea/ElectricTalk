package com.example.electrictalk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Helpers.StorageHelper;
import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.Models.ChargingStationModel;
import com.example.electrictalk.Models.SignInResponse;
import com.example.electrictalk.Models.UserModel;
import com.example.electrictalk.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEt;
    private EditText passwordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeViews();

        usernameEt.setText("ursan.bernadeta@gmail.com");
        passwordEt.setText("iTEC2019!");

        if (!HttpClientManager.Token.equals(""))
        {
            getCars();
            getStations();
            getProfileData();
            Intent myInt2= new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(myInt2);
        }
    }

    public void OnSignUp(View view) {
        Intent myInt2= new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(myInt2);
    }

    public void OnSignIn(View view) {
        String username= usernameEt.getText().toString();
        String password = passwordEt.getText().toString();
        HttpClientManager.getInstance().Login(username, password, new HttpClientManager.OnDataReceived<SignInResponse>() {
            @Override
            public void dataReceived(SignInResponse data) {
                getCars();
                getStations();
                getProfileData();
                Intent myInt2= new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(myInt2);
            }

            @Override
            public void onFailed() {
            }
        });
    }

    private void getCars()
    {
        StorageHelper.MyCarList = new ArrayList<>();
        HttpClientManager.getInstance().getCars(new HttpClientManager.OnDataReceived<List<CarModel>>() {
            @Override
            public void dataReceived(List<CarModel> data) {
                StorageHelper.MyCarList.addAll(data);
            }

            @Override
            public void onFailed() {
            }
        });
    }

    private void getStations()
    {
        StorageHelper.myStationsList = new ArrayList<>();

        HttpClientManager.getInstance().getStations(new HttpClientManager.OnDataReceived<List<ChargingStationModel>>() {
            @Override
            public void dataReceived(List<ChargingStationModel> data) {
                StorageHelper.myStationsList.addAll(data);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    private void getProfileData()
    {
        StorageHelper.myUser = new UserModel();

        HttpClientManager.getInstance().getProfileData(new HttpClientManager.OnDataReceived<UserModel>() {
            @Override
            public void dataReceived(UserModel data) {
                StorageHelper.myUser = data;

            }

            @Override
            public void onFailed() {

            }
        });
    }

    private void initializeViews()
    {
        usernameEt = findViewById(R.id.et_email);
        passwordEt = findViewById(R.id.et_password);
    }
}
