package com.example.trainingfavemobile.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    private static String BASE_URL = "https://freetogame.com/api/";

    public static Retrofit getRetrofitInstance(){
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        if(retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .client(HttpClientService.getUnsafeOkHttpClient())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
