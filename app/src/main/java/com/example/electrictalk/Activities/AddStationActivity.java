package com.example.electrictalk.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Helpers.StorageHelper;
import com.example.electrictalk.Models.ChargingStationModel;
import com.example.electrictalk.Models.LocationModel;
import com.example.electrictalk.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

public class AddStationActivity extends BaseAppCompat {
    private LatLng position;
    private EditText nameEt, totalSocketsEt, freeSocketsEt,latitudeEt, longitudeEt;
    private String name;
    private int freeSockets, totalSockets;
    private float latitude, longitude;
    private ChargingStationModel station;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station);
        super.SetToolbarTitle("Station");
        initializeViews();

        String charginStation = getIntent().getStringExtra("charging_station");
        Gson gson = new Gson();
        station = gson.fromJson(charginStation, ChargingStationModel.class);
    }

    private void setData()
    {

    }

    public void addStation(View view) {
        getData();
        LocationModel locationModel = new LocationModel(latitude,longitude);
        HttpClientManager.getInstance().addStation(name, totalSockets, freeSockets, locationModel, new HttpClientManager.OnDataReceived<ChargingStationModel>() {
            @Override
            public void dataReceived(ChargingStationModel data) {
                StorageHelper.myStationsList.add(data);
                finish();
            }

            @Override
            public void onFailed() {

            }
        });
    }

    private void getData()
    {
        name = nameEt.getText().toString();
        freeSockets = Integer.valueOf(freeSocketsEt.getText().toString());
        totalSockets = Integer.valueOf(totalSocketsEt.getText().toString());
        latitude = Float.valueOf(latitudeEt.getText().toString());
        longitude = Float.valueOf(longitudeEt.getText().toString());
    }

    private void initializeViews()
    {
        nameEt = findViewById(R.id.et_add_station_name);
        totalSocketsEt = findViewById(R.id.et_add_station_total_sockets);
        freeSocketsEt = findViewById(R.id.et_add_station_free_sockets);
        latitudeEt = findViewById(R.id.et_add_station_location);
        longitudeEt = findViewById(R.id.et_add_station_location_long);
    }
}
