package com.example.electrictalk.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electrictalk.Activities.AddCarActivity;
import com.example.electrictalk.Activities.AddCategoryActivity;
import com.example.electrictalk.Adapters.CarsAdapter;
import com.example.electrictalk.Adapters.CategoriesAdapter;
import com.example.electrictalk.Helpers.StorageHelper;
import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.Models.CategoryModel;
import com.example.electrictalk.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ForumFragment extends Fragment {

    private FloatingActionButton addCategoryBtn;
    private CategoriesAdapter categoriesAdapter;
    private RecyclerView recyclerView;
    private List<CategoryModel> categoryModelList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_forum, container, false);
        addCategoryBtn = rootView.findViewById(R.id.btn_add_category);
        recyclerView = rootView.findViewById(R.id.rv_listed_categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        addCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddCategoryActivity.class));
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        populateRecycler();
    }

    private void populateRecycler()
    {
        categoryModelList = StorageHelper.myCategoriesList;
        categoriesAdapter = new CategoriesAdapter(categoryModelList,getContext());
        recyclerView.setAdapter(categoriesAdapter);
    }

    public ForumFragment() {
    }

}
