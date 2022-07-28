package com.example.movies.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NameRetrofitInstance {
    private static Retrofit retrofit=null;

    private static String base_url ="https://api.themoviedb.org/3/";

    //Singleton to create RF instance

    public static NameDataService getService(){

        if( retrofit==null)
        {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        // return instance for interface
        return retrofit.create(NameDataService.class);
    }
}