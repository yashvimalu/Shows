package com.example.movies.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.movies.model.NameRepo;
import com.example.movies.model.Shows;
import java.util.List;

public class NameViewModel extends AndroidViewModel {

    private NameRepo nameRepo;
    private LiveData<List<Shows>> shows;
    private String name;

    public NameViewModel(@NonNull Application application) {
        super(application);
        nameRepo = new NameRepo(application);
    }

    public LiveData<List<Shows>> getAllShows(String name){
        return nameRepo.getMutableLiveData(name);
    }
}
