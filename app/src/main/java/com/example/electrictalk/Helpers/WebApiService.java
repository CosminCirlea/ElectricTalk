package com.example.electrictalk.Helpers;

import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.Models.ChargingStationModel;
import com.example.electrictalk.Models.SignInResponse;
import com.example.electrictalk.Models.UserModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebApiService {
    @POST("api/Auth/Register")
    Call<SignInResponse> Register(@Body Map<String, String> map);

    @POST("api/Auth/Login")
    Call<SignInResponse> LogIn(@Body Map<String, String> map);

    @POST("api/Auth/Update")
    Call<UserModel> updateProfile(@Body Map<String, String> map);

    @POST("api/Cars")
    Call<CarModel> addCar(@Body Map<String, String> map);

    @GET("api/Cars")
    Call<List<CarModel>> getCars();

    @POST("api/Stations")
    Call<ChargingStationModel> addStation(@Body ChargingStationModel model);

    @GET("api/Stations")
    Call<List<ChargingStationModel>> getStations();
}
