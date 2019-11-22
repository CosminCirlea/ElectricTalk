package com.example.electrictalk.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.R;

import java.util.ArrayList;
import java.util.List;

public class CarsFragment extends Fragment {

    private List<CarModel> carList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        carList.add(new CarModel("fage","FIAT",20));
        carList.add(new CarModel("fage","FORD",40));
        carList.add(new CarModel("fage","AUDI",50));

        return inflater.inflate(R.layout.fragment_cars, container, false);

    }

    public CarsFragment() {
        // Required empty public constructor
    }
}
