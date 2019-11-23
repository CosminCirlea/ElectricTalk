package com.example.electrictalk.Helpers;

import android.graphics.PointF;
import android.location.Location;

import com.example.electrictalk.Models.CarModel;
import com.example.electrictalk.Models.ChargingStationModel;
import com.example.electrictalk.Models.LocationModel;
import com.example.electrictalk.Models.SignInResponse;
import com.example.electrictalk.Models.UserModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
                .baseUrl("http://192.168.1.5:5030/")
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
        Call<List<CarModel>> tokens = service.getCars();
        tokens.enqueue(new Callback<List<CarModel>>() {
            @Override
            public void onResponse(Call<List<CarModel>> call, retrofit2.Response<List<CarModel>> response) {
                if (response.isSuccessful())
                {
                    List<CarModel> carModelList = new ArrayList<>();
                    carModelList = response.body();
                    callback.dataReceived(carModelList);
                }
            }

            @Override
            public void onFailure(Call<List<CarModel>> call, Throwable t) {

            }
        });

    }

    public void addStation(String name, int totalSockets, int freeSockets, LocationModel location, final OnDataReceived<ChargingStationModel> callback)
    {
        ChargingStationModel model = new ChargingStationModel();
        model.name = name;
        model.setTotalSocket(totalSockets);
        model.setFreeSockets(freeSockets);
        model.setLocation(location);

        Call<ChargingStationModel> tokens = service.addStation(model);
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

    public void getStations(final OnDataReceived<List<ChargingStationModel>> callback)
    {
        Call<List<ChargingStationModel>> tokens = service.getStations();
        tokens.enqueue(new Callback<List<ChargingStationModel>>() {
            @Override
            public void onResponse(Call<List<ChargingStationModel>> call, retrofit2.Response<List<ChargingStationModel>> response) {
                if (response.isSuccessful())
                {
                    List<ChargingStationModel> chargingStationModels = new ArrayList<>();
                    chargingStationModels = response.body();
                    callback.dataReceived(chargingStationModels);
                }
            }

            @Override
            public void onFailure(Call<List<ChargingStationModel>> call, Throwable t) {

            }
        });
    }

    public void updateProfile(String firstname, String lastname, final OnDataReceived<UserModel> callback)
    {
        Map<String, String> map = new HashMap<>();
        map.put("firstname", firstname);
        map.put("lastname", lastname);

        Call<UserModel> tokens = service.updateProfile(map);
        tokens.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, retrofit2.Response<UserModel> response) {
                if(response.isSuccessful()) {
                    UserModel aux = response.body();
                    callback.dataReceived(aux);
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }

    public void getProfileData(final OnDataReceived<UserModel> callback)
    {
        Call<UserModel> tokens = service.getProfileData();
        tokens.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, retrofit2.Response<UserModel> response) {
                if(response.isSuccessful()) {
                    UserModel aux = response.body();
                    callback.dataReceived(aux);
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
            }
        });
    }

    public void getCar(UUID id, final OnDataReceived<CarModel> callback)
    {
        Map<String, String> map = new HashMap<>();
        map.put("id", id.toString());

        Call<CarModel> tokens = service.getCar(id);
        tokens.enqueue(new Callback<CarModel>() {
            @Override
            public void onResponse(Call<CarModel> call, retrofit2.Response<CarModel> response) {
                if(response.isSuccessful()) {
                    CarModel aux = response.body();
                    callback.dataReceived(aux);
                }
            }

            @Override
            public void onFailure(Call<CarModel> call, Throwable t) {

            }
        });

    }
}
