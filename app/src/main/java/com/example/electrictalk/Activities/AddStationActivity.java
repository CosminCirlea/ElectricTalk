package com.example.electrictalk.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Helpers.StorageHelper;
import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.Models.ChargingStationModel;
import com.example.electrictalk.Models.LocationModel;
import com.example.electrictalk.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.UUID;

public class AddStationActivity extends BaseAppCompat {
    private LatLng position;
    private EditText nameEt, totalSocketsEt, freeSocketsEt,latitudeEt, longitudeEt;
    private String name;
    private FloatingActionButton editBtn, deleteBtn;
    private Button addStationBtn;
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

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStation();
            }
        });
    }

    private void deleteStation()
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure you want to delete this car?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        HttpClientManager.getInstance().deleteStation(UUID.fromString(station.getId()), new HttpClientManager.OnDataReceived<ChargingStationModel>() {
                            @Override
                            public void dataReceived(ChargingStationModel data) {
                                finish();
                                Toast.makeText(AddStationActivity.this, "Station deleted!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailed() {

                            }
                        });
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
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
        addStationBtn.setVisibility(View.GONE);

//        if (!station.getUserId().equals(StorageHelper.myUser.getId().toString()))
//        {
//            deleteBtn.setEnabled(false);
//            editBtn.setEnabled(false);
//        }
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
        addStationBtn = findViewById(R.id.btn_add_station);
    }
}
