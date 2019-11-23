package com.example.electrictalk.Helpers;

import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.Models.ChargingStationModel;
import com.example.electrictalk.Models.SignInResponse;

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

    @POST("api/Cars")
    Call<CarModel> addCar(@Body Map<String, String> map);

    @GET("api/Cars")
    Call<List<CarModel>> getCars();

    @POST("api/Cars")
    Call<ChargingStationModel> addStation(@Body Map<String, String> map);

//    @GET("api/Auth/Login")
//    Call<WebResponse<List>> GetTokensByLocation(@QueryMap Map<String, String> map);
}
