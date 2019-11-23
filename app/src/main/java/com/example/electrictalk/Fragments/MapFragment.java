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
import com.example.electrictalk.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener  {
    private MapView mMapView;
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private FloatingActionButton addStationBtn;
    private boolean isLocationSet;
    private LatLng position;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        initializeViews(rootView);
        mapFragment.getMapAsync(this);

        addStationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLocationSet)
                {
                    Toast.makeText(getActivity(), "Please select a location for the station!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Bundle args = new Bundle();
                    args.putParcelable("position", position);
                    Intent intent = new Intent(getActivity(), AddStationActivity.class);
                    intent.putExtra("positionBundle",args);
                    startActivity(intent);
                }
            }
        });

        return rootView;
    }

    public MapFragment() {
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
    }

    private void initializeViews(View rootView)
    {
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        addStationBtn = rootView.findViewById(R.id.btn_add_station);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        isLocationSet = !isLocationSet;
        MarkerOptions markerOptions = new MarkerOptions();

        if (!isLocationSet){
            markerOptions.position(new LatLng(latLng.latitude, latLng.latitude));
            position = latLng;
        }
        else
        {
            mMapView.setVisibility(View.GONE);
        }
    }
}
