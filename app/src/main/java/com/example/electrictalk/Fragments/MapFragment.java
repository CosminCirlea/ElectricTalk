package com.example.electrictalk.Fragments;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.electrictalk.Activities.AddStationActivity;
import com.example.electrictalk.Enums.ActivityType;
import com.example.electrictalk.Helpers.StorageHelper;
import com.example.electrictalk.Models.ChargingStationModel;
import com.example.electrictalk.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private FloatingActionButton addStationBtn;
    private boolean isLocationSet;
    private LatLng position;
    private MarkerOptions markerOptions;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        initializeViews(rootView);
        mapFragment.getMapAsync(this);

        addStationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddStationActivity.class);
                intent.putExtra("activity_type", ActivityType.EDIT);
                startActivity(intent);
            }
        });

        displayStations();

        return rootView;
    }

    private void displayStations(){
        for (ChargingStationModel station: StorageHelper.myStationsList) {
            Marker marker = googleMap.addMarker(new MarkerOptions()
            .position(new LatLng(station.getLocation().x, station.getLocation().y))
            .title(station.getName()));
            marker.setTag(station);
        }
    }

    public MapFragment() {
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.7494, 21.2272), 13));
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                isLocationSet = !isLocationSet;
                if (isLocationSet){
                    markerOptions = new MarkerOptions();
                    position = latLng;
                    markerOptions.position(new LatLng(latLng.latitude, latLng.latitude));
                    googleMap.addMarker(markerOptions);
                    markerOptions.visible(true);
                }
                else
                {
                    markerOptions.visible(false);
                }
            }
        });
    }

    private void initializeViews(View rootView)
    {
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        addStationBtn = rootView.findViewById(R.id.btn_add_station);
    }
}
