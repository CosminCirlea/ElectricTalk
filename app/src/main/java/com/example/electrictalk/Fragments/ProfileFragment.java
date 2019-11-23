package com.example.electrictalk.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.electrictalk.R;

public class ProfileFragment extends Fragment {

    private EditText lastName, firstName;
    private Button update;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        InitializeViews(view);
        return view;
    }

    private void InitializeViews(View view){
        lastName = view.findViewById(R.id.et_last_name);
        firstName = view.findViewById(R.id.et_first_name);
        update = view.findViewById(R.id.btn_edit);
    }

}
