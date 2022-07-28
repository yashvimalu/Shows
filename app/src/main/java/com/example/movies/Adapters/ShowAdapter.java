package com.example.movies.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movies.R;
import com.example.movies.View.MovieActivity;
import com.example.movies.databinding.MovieListItemBinding;
import com.example.movies.model.Shows;
import java.util.ArrayList;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.MovieViewHolder>{
    private Context context;
    private ArrayList<Shows> movieArrayList;

    public ShowAdapter(Context context, ArrayList<Shows> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListItemBinding movieListItemBinding  = DataBindingUtil.inflate
                (LayoutInflater.from(parent.getContext()),
                R.layout.movie_list_item,
                parent,
                false);

        return new MovieViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Shows shows = movieArrayList.get(position);
        holder.movieListItemBinding.setMovie(shows);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    // View Holder Class
    public class MovieViewHolder extends RecyclerView.ViewHolder{
        private MovieListItemBinding movieListItemBinding;

        public MovieViewHolder(MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding = movieListItemBinding;

            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION){
                        Shows selectedMovie = movieArrayList.get(position);

                        Intent i = new Intent(context, MovieActivity.class);

                        i.putExtra("movie", selectedMovie);
                        context.startActivity(i);

                    }
                }
            });
        }
    }
}