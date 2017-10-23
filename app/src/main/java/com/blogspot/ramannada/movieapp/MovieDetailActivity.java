package com.blogspot.ramannada.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView ivPoster = findViewById(R.id.iv_poster);
        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvReleaseDate = findViewById(R.id.tv_release_date);
        TextView tvDirector = findViewById(R.id.tv_director);
        TextView tvDuration = findViewById(R.id.tv_duration);

        Movie movie = getIntent().getParcelableExtra(MainActivity.MOVIE_DATA);

        ivPoster.setImageResource(movie.getPoster());
        tvTitle.setText(movie.getTitle());
        tvReleaseDate.setText(movie.getReleaseDate());
        tvDirector.setText(movie.getDirector());
        tvDuration.setText(movie.getDuration());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
