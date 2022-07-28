package com.example.movies.model;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import com.example.movies.R;
import com.example.movies.Service.GenreDataService;
import com.example.movies.Service.GenreRetrofiInstance;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreRepo {

    private ArrayList<Genre> genre =new ArrayList<>();
    private MutableLiveData<List<Genre>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public GenreRepo(Application application){this.application = application;}

    public MutableLiveData<List<Genre>> getMutableLiveData() {
        GenreDataService genreDataService = GenreRetrofiInstance.getService();
        Call<GenreResult> call = genreDataService.getGenres(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<GenreResult>() {
            @Override
            public void onResponse(Call<GenreResult> call, Response<GenreResult> response) {
                GenreResult result = response.body();
                if(result != null && result.getGenres() !=null){
                    genre = (ArrayList<Genre>) result.getGenres();
                    mutableLiveData.setValue(genre);
                }
            }

            @Override
            public void onFailure(Call<GenreResult> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
