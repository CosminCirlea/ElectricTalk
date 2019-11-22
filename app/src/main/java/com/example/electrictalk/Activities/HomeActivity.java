package com.example.electrictalk.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.electrictalk.Fragments.CarsFragment;
import com.example.electrictalk.Fragments.ForumFragment;
import com.example.electrictalk.Fragments.MapFragment;
import com.example.electrictalk.Fragments.ProfileFragment;
import com.example.electrictalk.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private CarsFragment carsFragment;
    private ForumFragment forumFragment;
    private MapFragment mapFragment;
    private ProfileFragment profileFragment;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_home);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        initializeViews();
        LoadFragment(profileFragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.navigation_profile:
                fragment = profileFragment;
                break;

            case R.id.navigation_cars:
                fragment = carsFragment;
                break;

            case R.id.navigation_map:
                fragment = mapFragment;
                break;

            case R.id.navigation_forum:
                fragment = forumFragment;
                break;
        }
        return LoadFragment(fragment);
    }

    private boolean LoadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        } else
            return false;
    }

    public void initializeViews() {
        carsFragment = new CarsFragment();
        profileFragment = new ProfileFragment();
        mapFragment = new MapFragment();
        forumFragment = new ForumFragment();
    }
}
