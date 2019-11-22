package com.example.electrictalk.Helpers;

import com.example.electrictalk.Models.WebResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
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
                .baseUrl("http://192.168.6.68/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        service = retrofit.create(WebApiService.class);
    }

    public void Login(String username, String password, final OnDataReceived<retrofit2.Response> callback)
    {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);

        Call<WebResponse<retrofit2.Response>> tokens = service.LogIn(map);
        tokens.enqueue(new Callback<WebResponse<retrofit2.Response>>() {
            @Override
            public void onResponse(Call<WebResponse<retrofit2.Response>> call, retrofit2.Response<WebResponse<retrofit2.Response>> response) {
                WebResponse<retrofit2.Response> responseBody = response.body();
                if(responseBody.isSuccessful()) {
                    callback.dataReceived(responseBody.getData());
                    return;
                }
            }

            @Override
            public void onFailure(Call<WebResponse<retrofit2.Response>> call, Throwable t) {

            }
        });
    }
}
