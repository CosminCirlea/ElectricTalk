package com.example.electrictalk.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electrictalk.Adapters.CarsAdapter;
import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CarsFragment extends Fragment {

    private FloatingActionButton addCarBtn;
    private CarsAdapter carsAdapter;
    private RecyclerView recyclerView;
    private List<CarModel> carList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_cars, container, false);
        initializeViews(rootView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        // Inflate the layout for this fragment
        carList.add(new CarModel("Panda", "FIAT", 20));
        carList.add(new CarModel("Focus", "FORD", 40));
        carList.add(new CarModel("A4", "AUDI", 50));
        carList.add(new CarModel("Model S", "Tesla", 50));

        carsAdapter = new CarsAdapter(carList,getContext());
        recyclerView.setAdapter(carsAdapter);

        addCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        return rootView;
    }

    public CarsFragment() {
    }

    private void initializeViews(View rootView) {
        addCarBtn = rootView.findViewById(R.id.btn_add_car);
        recyclerView = rootView.findViewById(R.id.rv_listed_cars);
    }
}
