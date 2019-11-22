package com.example.electrictalk.Helpers;

import com.example.electrictalk.Models.UserModel;
import com.example.electrictalk.Models.WebResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface WebApiService {
    @POST("api/Auth/Register")
    Call<WebResponse> Register(@Body UserModel body);

    @POST("api/Auth/Login")
    Call<WebResponse<Response>> LogIn(@Body Map<String, String> map);

//    @GET("api/Auth/Login")
//    Call<WebResponse<List>> GetTokensByLocation(@QueryMap Map<String, String> map);
}
