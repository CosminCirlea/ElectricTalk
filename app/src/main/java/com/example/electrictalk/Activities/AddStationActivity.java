package com.example.electrictalk.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Helpers.StorageHelper;
import com.example.electrictalk.Models.ChargingStationModel;
import com.example.electrictalk.Models.LocationModel;
import com.example.electrictalk.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

public class AddStationActivity extends BaseAppCompat {
    private LatLng position;
    private EditText nameEt, totalSocketsEt, freeSocketsEt,latitudeEt, longitudeEt;
    private String name;
    private FloatingActionButton editBtn, deleteBtn;
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
        if (station != null)
        {
            setData(station);
        }
    }

    private void setData(ChargingStationModel station)
    {
        nameEt.setText(station.getName());
        totalSocketsEt.setText(String.valueOf(station.getTotalSocket()));
        freeSocketsEt.setText(String.valueOf(station.getFreeSockets()));
        latitudeEt.setText(String.valueOf(station.getLocation().x));
        longitudeEt.setText(String.valueOf(station.getLocation().y));

        nameEt.setEnabled(false);
        totalSocketsEt.setEnabled(false);
        freeSocketsEt.setEnabled(false);
        latitudeEt.setEnabled(false);
        longitudeEt.setEnabled(false);

        if (!station.getUserId().equals(StorageHelper.myUser.getId().toString()))
        {
            deleteBtn.setEnabled(false);
            editBtn.setEnabled(false);
        }
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
        editBtn = findViewById(R.id.btn_edit);
        deleteBtn = findViewById(R.id.btn_delete);
    }
}
