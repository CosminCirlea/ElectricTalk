package com.example.electrictalk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Models.SignInResponse;
import com.example.electrictalk.R;

public class SignupActivity extends AppCompatActivity {
    private EditText mEmail;
    private EditText mPassword;
    private EditText mFirstName;
    private EditText mLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initializeViews();
    }

    private void initializeViews()
    {
        mEmail = findViewById(R.id.et_confirm_password);
        mPassword = findViewById(R.id.et_matricol);
        mFirstName = findViewById(R.id.et_username);
        mLastName = findViewById(R.id.et_password);
    }

    public void OnRegister(View view) {
        String firstName= mFirstName.getText().toString();
        String lastName= mLastName.getText().toString();
        String email= mEmail.getText().toString();
        String password= mPassword.getText().toString();

        HttpClientManager.getInstance().register(email, password, firstName, lastName, new HttpClientManager.OnDataReceived<SignInResponse>() {
            @Override
            public void dataReceived(SignInResponse data) {
                if (data.token != null)
                {
                    HttpClientManager.Token = data.token;
                    startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                }
            }

            @Override
            public void onFailed() {
            }
        });
    }
}
