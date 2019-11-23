package com.example.electrictalk.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.electrictalk.Activities.HomeActivity;
import com.example.electrictalk.Activities.OneCarActivity;
import com.example.electrictalk.Activities.TopicsActivity;
import com.example.electrictalk.Fragments.ForumFragment;
import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.R;
import com.google.gson.Gson;

import java.util.List;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder>{

    private List<CarModel> myCategories;
    private Context context;

    public CategoriesAdapter(List<CarModel> myCategories, Context context) {
        this.myCategories = myCategories;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_categories, parent, false);
        CategoriesAdapter.ViewHolder holder = new CategoriesAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String category = myCategories.get(position).getCompany();

        holder.categoryTv.setText(category);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, TopicsActivity.class);
                context.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView categoryTv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initializeViews(itemView);
        }

        private void initializeViews(View itemView) {
            categoryTv = itemView.findViewById(R.id.tv_category);
        }
    }
}
