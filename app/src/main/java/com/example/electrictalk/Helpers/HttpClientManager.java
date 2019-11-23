package com.example.electrictalk.Helpers;

import android.graphics.PointF;
import android.location.Location;

import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.Models.ChargingStationModel;
import com.example.electrictalk.Models.LocationModel;
import com.example.electrictalk.Models.SignInResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClientManager {
    public interface OnDataReceived<T> {
        /**
         * This method is called whenever an api call has been successful.
         *
         * @param data The data from the api call with a generic type.
         */
        void dataReceived(T data);

        /**
         * This method is called whenever an api call has failed.
         */
        void onFailed();
    }
    private static final HttpClientManager instance = new HttpClientManager();
    public static HttpClientManager getInstance() {
        return instance;
    }

    public static String Token = "";

    private WebApiService service;

    private HttpClientManager() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
                                      @Override
                                      public Response intercept(Interceptor.Chain chain) throws IOException {
                                          Request original = chain.request();

                                          Request.Builder request = original.newBuilder()
                                                  .header("TEAM_KEY", "9BBATHJ847Z5V")
                                                  .method(original.method(), original.body());
                                          if (!Token.equals(""))
                                          {
                                              request.header("Authorization", "Bearer "+Token);
                                          }

                                          return chain.proceed(request.build());
                                      }
                                  });

                Gson gson = new GsonBuilder().
                setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.6.62:5030/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        service = retrofit.create(WebApiService.class);
    }

    public void Login(String username, String password, final OnDataReceived<SignInResponse> callback)
    {
        Map<String, String> map = new HashMap<>();
        map.put("email", username);
        map.put("password", password);

        Call<SignInResponse> tokens = service.LogIn(map);
        tokens.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, retrofit2.Response<SignInResponse> response) {
                if(response.isSuccessful()) {
                    SignInResponse a = response.body();
                    Token = a.token;
                    callback.dataReceived(a);
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
            }
        });
    }

    public void register(String email, String password, String firstname, String lastname,final OnDataReceived<SignInResponse> callback)
    {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);
        map.put("firstName", firstname);
        map.put("lastName", lastname);

        Call<SignInResponse> tokens = service.Register(map);
        tokens.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, retrofit2.Response<SignInResponse> response) {
                if(response.isSuccessful()) {
                    SignInResponse a = response.body();
                    callback.dataReceived(a);
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {

            }
        });
    }

    public void addCar(String model, String company, int year, int autonomy, int batteryLeft, String lastTechRevision, final OnDataReceived<CarModel> callback)
    {
        Map<String, String> map = new HashMap<>();
        map.put("model", model);
        map.put("company", company);
        map.put("year", String.valueOf(year));
        map.put("autonomy",  String.valueOf(autonomy));
        map.put("batteryLeft",  String.valueOf(batteryLeft));
        map.put("lastTechRevision",  lastTechRevision);

        Call<CarModel> tokens = service.addCar(map);
        tokens.enqueue(new Callback<CarModel>() {
            @Override
            public void onResponse(Call<CarModel> call, retrofit2.Response<CarModel> response) {
                if(response.isSuccessful()) {
                    CarModel a = response.body();
                    callback.dataReceived(a);
                }
            }

            @Override
            public void onFailure(Call<CarModel> call, Throwable t) {
            }
        });
    }

    public void getCars(final OnDataReceived<List<CarModel>> callback)
    {
        Call tokens = service.getCars();
        tokens.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if(response.isSuccessful()) {
                    Object a = response.body();
//                    callback.dataReceived(a);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public void addStation(String name, int totalSockets, int freeSockets, PointF location, final OnDataReceived<ChargingStationModel> callback)
    {
        Gson gson = new Gson();
        String toSend = gson.toJson(location);
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("totalSockets", String.valueOf(totalSockets));
        map.put("freeSockets",  String.valueOf(freeSockets));
        map.put("location",  toSend);

        Call<ChargingStationModel> tokens = service.addStation(map);
        tokens.enqueue(new Callback<ChargingStationModel>() {
            @Override
            public void onResponse(Call<ChargingStationModel> call, retrofit2.Response<ChargingStationModel> response) {
                if(response.isSuccessful()) {
                    ChargingStationModel aux = response.body();
                    callback.dataReceived(aux);
                }
            }

            @Override
            public void onFailure(Call<ChargingStationModel> call, Throwable t) {

            }
        });
    }
}
