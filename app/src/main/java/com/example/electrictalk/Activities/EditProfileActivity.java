package com.example.electrictalk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.electrictalk.Fragments.ProfileFragment;
import com.example.electrictalk.R;

public class EditProfileActivity extends BaseAppCompat {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

    public void OnSaveProfile(View view) {
        Intent myInt2= new Intent(EditProfileActivity.this, ProfileFragment.class);
        startActivity(myInt2);
    }
}
