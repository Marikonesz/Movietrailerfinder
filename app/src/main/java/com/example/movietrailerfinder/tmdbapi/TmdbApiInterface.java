package com.example.movietrailerfinder.tmdbapi;

import com.example.movietrailerfinder.entities.MovieTrailers;
import com.example.movietrailerfinder.entities.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbApiInterface {

    @GET("3/search/movie")
    Call<SearchResult>serchMovies(@Query("api_key") String apiKey, @Query("query") String query);

    @GET("3/movie/{movie}/videos")
    Call<MovieTrailers>getTrailers(@Path("movie")int movieId, @Query("api_key") String apiKey);

}
