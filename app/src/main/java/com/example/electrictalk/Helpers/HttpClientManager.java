package com.example.electrictalk.Helpers;

import com.example.electrictalk.Models.SignInResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
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
}
