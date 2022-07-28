package com.example.movies.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.movies.model.ShowRepo;
import com.example.movies.model.Shows;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private ShowRepo movieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new ShowRepo(application);
    }

    // Live Data
    public LiveData<List<Shows>> getAllMovies(){
        return movieRepository.getMutableLiveData();
    }
}