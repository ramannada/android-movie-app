package com.blogspot.ramannada.movieapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by ramannada on 10/23/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private List<Movie> listMovie;

    public MovieAdapter(List<Movie> listMovie) {
        this.listMovie = listMovie;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.column_movie, parent, false);
        return new MovieHolder(v);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        Movie movie = listMovie.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        private ImageView ivPoster;
        private TextView tvTitle;
        private TextView tvReleaseDate;

        public MovieHolder(View itemView) {
            super(itemView);

            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvReleaseDate = itemView.findViewById(R.id.tv_release_date);
        }

        private void bind(Movie movie) {
            ivPoster.setImageResource(movie.getPoster());
            tvTitle.setText(movie.getTitle());
            tvReleaseDate.setText(movie.getReleaseDate());
        }
    }
}
