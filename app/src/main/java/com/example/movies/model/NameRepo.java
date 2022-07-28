package com.example.movies.model;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import com.example.movies.R;
import com.example.movies.Service.NameDataService;
import com.example.movies.Service.NameRetrofitInstance;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NameRepo {    private ArrayList<Shows> shows = new ArrayList<>();
    private MutableLiveData<List<Shows>> mutableLiveData = new MutableLiveData<List<Shows>>();
    private Application application;
    private String name;

    public NameRepo(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Shows>> getMutableLiveData(String name1){
        name=name1;
        NameDataService nameDataService = NameRetrofitInstance.getService();
        Call<ShowsResult> call = nameDataService.getMovieByName(application.getApplicationContext().getString(R.string.api_key),name);

        call.enqueue(new Callback<ShowsResult>() {
            @Override
            public void onResponse(Call<ShowsResult> call, Response<ShowsResult> response) {
                ShowsResult result = response.body();
                if(result != null && result.getResults() !=null){
                    shows = (ArrayList<Shows>) result.getResults();
                    mutableLiveData.setValue(shows);
                }
            }

            @Override
            public void onFailure(Call<ShowsResult> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}