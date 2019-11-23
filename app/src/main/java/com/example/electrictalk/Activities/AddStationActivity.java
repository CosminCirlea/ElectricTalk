package com.example.electrictalk.Activities;

import android.os.Bundle;
import android.view.View;

import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Models.ChargingStationModel;
import com.example.electrictalk.Models.LocationModel;
import com.example.electrictalk.R;
import com.google.android.gms.maps.model.LatLng;

public class AddStationActivity extends BaseAppCompat {
    private LatLng position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station);
        super.SetToolbarTitle("Station");

        Bundle bundle = getIntent().getParcelableExtra("positionBundle");
        position = bundle.getParcelable("position");
    }

    public void addStation(View view) {
        LocationModel locationModel = new LocationModel((float)position.latitude,(float)position.longitude);

        HttpClientManager.getInstance().addStation("Station test", 5, 1, locationModel, new HttpClientManager.OnDataReceived<ChargingStationModel>() {
            @Override
            public void dataReceived(ChargingStationModel data) {
                int a =0;
            }

            @Override
            public void onFailed() {

            }
        });
    }
}
