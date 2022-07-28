package com.example.movies.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.movies.model.ShowRepo;
import com.example.movies.model.Shows;

import java.util.List;

public class ShowActivityViewModel extends AndroidViewModel {

    private ShowRepo showRepo;
    private LiveData<List<Shows>> shows;

    public ShowActivityViewModel(@NonNull Application application) {
        super(application);
        showRepo = new ShowRepo(application);
    }

    public LiveData<List<Shows>> getAllShows(){
        return showRepo.getMutableLiveData();
    }
}
