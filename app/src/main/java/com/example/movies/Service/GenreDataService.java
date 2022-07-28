package com.example.movies.Service;

import com.example.movies.model.GenreResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GenreDataService {

    @GET("genre/tv/list")
    Call<GenreResult> getGenres(@Query("api_key")String api_key);
}
