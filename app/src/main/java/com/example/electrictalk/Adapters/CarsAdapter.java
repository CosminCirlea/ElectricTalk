package com.example.electrictalk.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.electrictalk.Activities.OneCarActivity;
import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.R;
import com.google.gson.Gson;

import java.util.List;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder> {

    private List<CarModel> myCars;
    private Context context;

    public CarsAdapter(List<CarModel> myCars, Context context) {
        this.myCars = myCars;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_my_cars, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int batteryLeft = myCars.get(position).getBatteryLeft();
        String company = myCars.get(position).getCompany();
        String model = myCars.get(position).getModel();
        final UUID carId = myCars.get(position).getId();

        holder.batteryLeftTv.setText(""+batteryLeft);
        holder.companyTv.setText(company);
        holder.modelTv.setText(model);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, OneCarActivity.class);
                String carIdSerialized = new Gson().toJson(carId);
                mIntent.putExtra("car_id",carIdSerialized);
                context.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myCars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView batteryLeftTv;
        private TextView companyTv;
        private TextView modelTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initializeViews(itemView);
        }

        private void initializeViews(View itemView) {
            batteryLeftTv = itemView.findViewById(R.id.tv_procent);
            companyTv = itemView.findViewById(R.id.tv_company);
            modelTv = itemView.findViewById(R.id.tv_model);
        }
    }
}
