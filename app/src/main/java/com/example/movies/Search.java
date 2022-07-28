package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.example.movies.Adapters.ShowAdapter;
import com.example.movies.ViewModel.NameViewModel;
import com.example.movies.ViewModel.ShowActivityViewModel;
import com.example.movies.model.Genre;
import com.example.movies.ViewModel.GenreViewModel;
import java.util.ArrayList;
import java.util.List;
import com.example.movies.databinding.ActivitySearchBinding;
import com.example.movies.model.Shows;

public class Search extends AppCompatActivity {

    GenreViewModel genreViewModel;
    ArrayList<Genre> genresList = new ArrayList<>();
    private ActivitySearchBinding activitySearchBindingBinding;
    ArrayList<Shows> showsList = new ArrayList<>();
    ShowActivityViewModel showActivityViewModel;
    ShowAdapter showAdapter;
    NameViewModel nameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.searchOpt, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        activitySearchBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        activitySearchBindingBinding.spinner1.setAdapter(adapter1);

        genreViewModel = new ViewModelProvider(this).get(GenreViewModel.class);
        showActivityViewModel = new ViewModelProvider(this).get(ShowActivityViewModel.class);
        nameViewModel = new ViewModelProvider(this).get(NameViewModel.class);

        activitySearchBindingBinding.spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();

                if (text.equalsIgnoreCase("by genre")) {
                    genreViewModel.getAllGenre().observe(Search.this, new Observer<List<Genre>>() {
                        @Override
                        public void onChanged(List<Genre> genres) {

                            activitySearchBindingBinding.srch.setVisibility(View.INVISIBLE);
                            activitySearchBindingBinding.find.setVisibility(View.INVISIBLE);
                            activitySearchBindingBinding.rv1.setVisibility(View.INVISIBLE);
                            activitySearchBindingBinding.genre.setVisibility(View.VISIBLE);

                            genresList = (ArrayList<Genre>) genres;

                            ArrayList<String> l1 = new ArrayList<>();
                            System.out.println("Genres are");
                            for (Genre i : genresList) {
                                l1.add(i.getName());
                            }
                            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(Search.this,
                                    android.R.layout.simple_spinner_item, l1);
                            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            activitySearchBindingBinding.genre.setAdapter(adapter1);
                            adapter1.notifyDataSetChanged();
                        }
                    });
                }

                else if (text.equalsIgnoreCase("popular")) {
                    activitySearchBindingBinding.srch.setVisibility(View.INVISIBLE);
                    activitySearchBindingBinding.find.setVisibility(View.INVISIBLE);
                    activitySearchBindingBinding.genre.setVisibility(View.INVISIBLE);

                    showActivityViewModel.getAllShows().observe(Search.this, new Observer<List<Shows>>() {
                        @Override
                        public void onChanged(List<Shows> shows) {

                            activitySearchBindingBinding.rv1.setVisibility(View.VISIBLE);

                            showsList = (ArrayList<Shows>) shows;
                            showAdapter = new ShowAdapter(Search.this, showsList);

                            if (Search.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                                activitySearchBindingBinding.rv1.setLayoutManager(new GridLayoutManager(Search.this, 1));
                            } else {
                                activitySearchBindingBinding.rv1.setLayoutManager(new GridLayoutManager(Search.this, 4));
                            }

                            activitySearchBindingBinding.rv1.setItemAnimator(new DefaultItemAnimator());
                            activitySearchBindingBinding.rv1.setAdapter(showAdapter);
                            showAdapter.notifyDataSetChanged();
                        }
                    });
                }

                else if(text.equalsIgnoreCase("by title")) {

                            activitySearchBindingBinding.rv1.setVisibility(View.INVISIBLE);
                            activitySearchBindingBinding.genre.setVisibility(View.INVISIBLE);
                            activitySearchBindingBinding.srch.setVisibility(View.VISIBLE);
                            activitySearchBindingBinding.find.setVisibility(View.VISIBLE);

                            activitySearchBindingBinding.find.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String name= activitySearchBindingBinding.srch.getText().toString();
                                    activitySearchBindingBinding.srch.setVisibility(View.INVISIBLE);
                                    activitySearchBindingBinding.find.setVisibility(View.INVISIBLE);

                                    nameViewModel.getAllShows(name).observe(Search.this, new Observer<List<Shows>>() {
                                        @Override
                                        public void onChanged(List<Shows> shows) {
                                            activitySearchBindingBinding.rv1.setVisibility(View.VISIBLE);

                                            showsList = (ArrayList<Shows>) shows;
                                            showAdapter = new ShowAdapter(Search.this, showsList);

                                            if (Search.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                                                activitySearchBindingBinding.rv1.setLayoutManager(new GridLayoutManager(Search.this, 1));
                                            } else {
                                                activitySearchBindingBinding.rv1.setLayoutManager(new GridLayoutManager(Search.this, 4));
                                            }

                                            activitySearchBindingBinding.rv1.setItemAnimator(new DefaultItemAnimator());
                                            activitySearchBindingBinding.rv1.setAdapter(showAdapter);
                                            showAdapter.notifyDataSetChanged();
                                        }
                                    });
//                                    NameDataService nameDataService = NameRetrofitInstance.getService();
//                                    Call<ShowsResult> call = nameDataService.getMovieByName(getApplicationContext().getString(R.string.api_key),name);
//
//                                    call.enqueue(new Callback<ShowsResult>() {
//                                        @Override
//                                        public void onResponse(Call<ShowsResult> call, Response<ShowsResult> response) {
//                                            ShowsResult result = response.body();
//                                            if(result != null && result.getResults() !=null){
//                                                showsList = (ArrayList<Shows>) result.getResults();
//                                            }
//                                        }
//                                        @Override
//                                        public void onFailure(Call<ShowsResult> call, Throwable t) {
//
//                                        }
//                                    });
//
//                                    activitySearchBindingBinding.rv1.setVisibility(View.VISIBLE);
//                                    Toast.makeText(Search.this,"Showlist" +showsList,Toast.LENGTH_LONG).show();
//                                    showAdapter = new ShowAdapter(Search.this, showsList);
//
//                                    if (Search.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//                                        activitySearchBindingBinding.rv1.setLayoutManager(new GridLayoutManager(Search.this, 1));
//                                    } else {
//                                        activitySearchBindingBinding.rv1.setLayoutManager(new GridLayoutManager(Search.this, 4));
//                                    }
//
//                                    activitySearchBindingBinding.rv1.setItemAnimator(new DefaultItemAnimator());
//                                    activitySearchBindingBinding.rv1.setAdapter(showAdapter);
//                                    showAdapter.notifyDataSetChanged();
                                }
                            });
                            }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}