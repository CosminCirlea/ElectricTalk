package com.example.electrictalk.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.electrictalk.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private MapView mMapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        initializeViews(rootView);

        mMapView.getMapAsync(this);
        return rootView;
    }

    public MapFragment() {

    }

    private void initializeViews(View rootView)
    {
        mMapView = rootView.findViewById(R.id.map_view);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap = googleMap;
    }
}
