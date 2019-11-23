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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
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
