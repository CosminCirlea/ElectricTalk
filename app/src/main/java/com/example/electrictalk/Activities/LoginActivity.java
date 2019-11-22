package com.example.electrictalk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Models.SignInResponse;
import com.example.electrictalk.R;

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

        if (!HttpClientManager.Token.equals(""))
        {
            Intent myInt2= new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(myInt2);
        }
        usernameEt.setText("ursan.bernadeta@gmail.com");
        passwordEt.setText("iTEC2019!");
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
                Intent myInt2= new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(myInt2);
            }

            @Override
            public void onFailed() {
            }
        });
    }

    private void initializeViews()
    {
        usernameEt = findViewById(R.id.et_username);
        passwordEt = findViewById(R.id.et_password);
    }
}
