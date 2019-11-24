package com.example.electrictalk.Fragments;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
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
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private FloatingActionButton addStationBtn;
    private boolean isLocationSet;
    private LatLng position;
    private MarkerOptions markerOptions;
    ChargingStationModel max;
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

        return rootView;
    }

    private void displayStations(){
        Drawable circleDrawable = getResources().getDrawable(R.drawable.ic_station);

        for (ChargingStationModel station: StorageHelper.myStationsList) {
            Marker marker = googleMap.addMarker(new MarkerOptions()
            .position(new LatLng(station.getLocation().x, station.getLocation().y))
            .icon(getMarkerIconFromDrawable(circleDrawable))
            .title(station.getName()));
            marker.setTag(station);
        }
    }

    private BitmapDescriptor getMarkerIconFromDrawable(Drawable drawable) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    public MapFragment() {
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;
        displayStations();

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

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                max = (ChargingStationModel) marker.getTag();
                Gson gson = new Gson();
                String charginStation = gson.toJson(max);
                Intent intent = new Intent(getActivity(), AddStationActivity.class);
                intent.putExtra("charging_station", charginStation);
                startActivity(intent);
                return true;
            }
        });
    }

    private void initializeViews(View rootView)
    {
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        addStationBtn = rootView.findViewById(R.id.btn_add_station);
    }
}
