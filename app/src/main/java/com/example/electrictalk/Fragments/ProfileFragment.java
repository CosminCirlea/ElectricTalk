package com.example.electrictalk.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Helpers.StorageHelper;
import com.example.electrictalk.Models.UserModel;
import com.example.electrictalk.R;

public class ProfileFragment extends Fragment {

    private EditText lastName, firstName;
    private Button update;
    private String firstname, lastname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        InitializeViews(view);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                HttpClientManager.getInstance().updateProfile(firstname, lastname, new HttpClientManager.OnDataReceived<UserModel>() {
                    @Override
                    public void dataReceived(UserModel data) {
                    }

                    @Override
                    public void onFailed() {

                    }
                });
            }
        });

        return view;
    }

    private void getData()
    {
        firstname = firstName.getText().toString();
        lastname = lastName.getText().toString();
    }

    private void InitializeViews(View view){
        lastName = view.findViewById(R.id.et_last_name);
        firstName = view.findViewById(R.id.et_first_name);
        update = view.findViewById(R.id.btn_edit);

        lastName.setText(StorageHelper.myUser.getName());
        firstName.setText(StorageHelper.myUser.getFirstname());
    }

}
