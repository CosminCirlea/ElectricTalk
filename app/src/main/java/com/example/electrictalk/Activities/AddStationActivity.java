package com.example.electrictalk.Activities;

import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;

import com.example.electrictalk.Helpers.HttpClientManager;
import com.example.electrictalk.Models.ChargingStationModel;
import com.example.electrictalk.Models.LocationModel;
import com.example.electrictalk.R;

public class AddStationActivity extends BaseAppCompat {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station);
    }

    public void addStation(View view) {
        PointF locationModel = new PointF();
        locationModel.x = 23.3232f;
        locationModel.y = 45.3232f;

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
