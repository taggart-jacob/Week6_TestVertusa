package com.example.week6_testvertusa;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class RetrofitVertusa {
    //COME BACK TO THIS IF THERE ARE ISSUES
    public static final String BASE_URL = "https://data.cityofnewyork.us/";

    public Retrofit getRetroFitInstance(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)).build();

        return new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public RetrofitApiService getService(){
        return getRetroFitInstance().create(RetrofitApiService.class);
    }

    public interface RetrofitApiService{
        @GET
        Call<ArrayList<HighSchool>> getHighSchools(@Url String api_url);

        @GET
        Call<ArrayList<SAT>> getSAT(@Url String api_url);
    }
}
