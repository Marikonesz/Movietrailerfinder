package com.example.movietrailerfinder.tmdbapi;

import android.app.Application;

import com.example.movietrailerfinder.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TmdbApi extends Application {
    private static TmdbApiInterface tmdbApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = new Retrofit.Builder().baseUrl(this.getString(R.string.api_base_url)).addConverterFactory(GsonConverterFactory.create()).build();
        tmdbApi = retrofit.create(TmdbApiInterface.class);
    }
    public static TmdbApiInterface getTmdbApi() {
        return tmdbApi;
    }
}
