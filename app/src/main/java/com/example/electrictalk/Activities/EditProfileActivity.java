package com.example.electrictalk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Helpers.StorageHelper;
import com.example.electrictalk.Models.UserModel;
import com.example.electrictalk.R;

public class EditProfileActivity extends BaseAppCompat {

    private String firstname, lastname;
    private EditText firstNameEt, lastNameEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        firstNameEt = findViewById(R.id.et_first_name);
        lastNameEt = findViewById(R.id.et_last_name);
    }

    private void onSave()
    {
        firstname = firstNameEt.getText().toString();
        lastname = lastNameEt.getText().toString();
    }

    public void OnSaveProfile(View view) {
        onSave();
        HttpClientManager.getInstance().updateProfile(firstname, lastname, new HttpClientManager.OnDataReceived<UserModel>() {
            @Override
            public void dataReceived(UserModel data) {
                StorageHelper.myUser = data;
                finish();
            }

            @Override
            public void onFailed() {

            }
        });
    }
}
