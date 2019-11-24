package com.example.electrictalk.Helpers;

import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.Models.CategoryModel;
import com.example.electrictalk.Models.ChargingStationModel;
import com.example.electrictalk.Models.SignInResponse;
import com.example.electrictalk.Models.TopicModel;
import com.example.electrictalk.Models.UserModel;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebApiService {
    @POST("api/Auth/Register")
    Call<SignInResponse> Register(@Body Map<String, String> map);

    @POST("api/Auth/Login")
    Call<SignInResponse> LogIn(@Body Map<String, String> map);

    @POST("api/Auth/Update")
    Call<UserModel> updateProfile(@Body Map<String, String> map);

    @GET("api/Auth/Me")
    Call<UserModel> getProfileData();

    @POST("api/Cars")
    Call<CarModel> addCar(@Body Map<String, String> map);

    @GET("api/Cars/{id}")
    Call<CarModel> getCar(@Path(value = "id") UUID id);

    @PATCH("api/Cars/{id}")
    Call<CarModel> updateCar(@Path(value = "id") UUID id, @Body CarModel car);

    @DELETE("api/Cars/{id}")
    Call<Object> deleteCar(@Path(value = "id") UUID id);

    @GET("api/Cars")
    Call<List<CarModel>> getCars();

    @POST("api/Stations")
    Call<ChargingStationModel> addStation(@Body ChargingStationModel model);

    @GET("api/Stations")
    Call<List<ChargingStationModel>> getStations();

    @POST("api/Stations/{id}/Delete")
    Call<ChargingStationModel> deleteStation(@Path(value = "id") UUID id);

    @POST("api/Forum/Category")
    Call<CategoryModel> addCategory(@Body Map<String, String> map);

    @GET("api/Forum/Category")
    Call<List<CategoryModel>> getCategories();

    @GET("api/Forum/Category/{id}/Topics")
    Call<List<TopicModel>> getTopics(@Path(value = "id")UUID id);

    @POST("api/Forum/Category/{id}/Topics")
    Call<TopicModel> addTopic(@Path(value = "id")UUID id, @Body Map<String, String> map);
}
