package com.example.movies.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import com.example.movies.R;
import com.example.movies.databinding.ActivityMovieBinding;
import com.example.movies.model.Shows;

public class MovieActivity extends AppCompatActivity {
    private Shows shows;
    private ActivityMovieBinding activityMovieBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);

        Intent i = getIntent();

        if (i != null) {
            shows = i.getParcelableExtra("movie");
            activityMovieBinding.setMovie(shows);
            getSupportActionBar().setTitle(shows.getName());
        }
    }
}