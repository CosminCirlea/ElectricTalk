package com.example.electrictalk.Helpers;

import com.example.electrictalk.Models.SignInResponse;
import com.example.electrictalk.Models.UserModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WebApiService {
    @POST("api/Auth/Register")
    Call<SignInResponse> Register(@Body UserModel body);

    @POST("api/Auth/Login")
    Call<SignInResponse> LogIn(@Body Map<String, String> map);

//    @GET("api/Auth/Login")
//    Call<WebResponse<List>> GetTokensByLocation(@QueryMap Map<String, String> map);
}
