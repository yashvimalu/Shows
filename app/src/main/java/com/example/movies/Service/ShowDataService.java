package com.example.movies.Service;

import com.example.movies.model.ShowsResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShowDataService {
    @GET("tv/popular")
    Call<ShowsResult> getPopularShows(@Query("api_key") String apiKey);
}