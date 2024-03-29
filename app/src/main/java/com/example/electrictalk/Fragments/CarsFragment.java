package com.example.electrictalk.Fragments;


import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electrictalk.Activities.AddCarActivity;
import com.example.electrictalk.Adapters.CarsAdapter;
import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Helpers.StorageHelper;
import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        populateRecycler();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Calendar calendar= Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        Date date = calendar.getTime();
        String currentDate = dateFormat.format(date);

        for (CarModel car : StorageHelper.MyCarList) {
            if (car.getLastTechRevision().compareTo(currentDate) < 0)
            {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "someId")
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle("Car might need checking")
                        .setContentText("Please check this car: ".concat(car.getModel()))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManager mNotificationManager =

                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(001, builder.build());
            }
        }
    }

    private void populateRecycler()
    {
        carList = StorageHelper.MyCarList;
        carsAdapter = new CarsAdapter(carList,getContext());
        recyclerView.setAdapter(carsAdapter);

        addCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddCarActivity.class));
            }
        });
    }

    public CarsFragment() {
    }

    private void initializeViews(View rootView) {
        addCarBtn = rootView.findViewById(R.id.btn_add_car);
        recyclerView = rootView.findViewById(R.id.rv_listed_cars);
    }
}
