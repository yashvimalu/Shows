package com.example.movies.Service;

import com.example.movies.model.ShowsResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NameDataService {

    @GET("search/tv")
    Call<ShowsResult> getMovieByName(
            @Query("api_key") String apiKey,
            @Query("query") String query);

}