package com.example.movies.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movies.model.Genre;
import com.example.movies.model.GenreRepo;

import java.util.List;

public class GenreViewModel extends AndroidViewModel {

    private GenreRepo genreRepo;
    private LiveData<List<GenreViewModel>> movies;

    public GenreViewModel(@NonNull Application application) {
        super(application);
        genreRepo = new GenreRepo(application);
    }

    public LiveData<List<Genre>> getAllGenre(){
        return genreRepo.getMutableLiveData();
    }
}
