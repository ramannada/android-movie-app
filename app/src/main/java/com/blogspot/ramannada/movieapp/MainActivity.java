package com.blogspot.ramannada.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String MOVIE_DATA = "movie data";
    ArrayList<Movie> hotMovies = new ArrayList<>();
    ArrayList<Movie> recentMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hotMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        hotMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        hotMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        hotMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        hotMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        hotMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        hotMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        hotMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        hotMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        hotMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));


        recentMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        recentMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        recentMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        recentMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        recentMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        recentMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        recentMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        recentMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        recentMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        recentMovies.add(new Movie("Spider-Man: Homecoming", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));


        TextView tvLogout = findViewById(R.id.tv_logout);
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedData.getSharedData().clearLoginStatus();

                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

        RecyclerView hotMovieRecyclerView = findViewById(R.id.recycler_view_hot_movie);
        RecyclerView.Adapter hotMovieAdapter = new MovieAdapter(hotMovies);
        hotMovieRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        hotMovieRecyclerView.setAdapter(hotMovieAdapter);

        hotMovieRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, hotMovieRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(MainActivity.this, MovieDetailActivity.class);
                i.putExtra(MOVIE_DATA, hotMovies.get(position));
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        RecyclerView recentMovieRecyclerView = findViewById(R.id.recycler_view_recent_movie);
        RecyclerView.Adapter recentMovieAdapter = new MovieAdapter(recentMovies);
        recentMovieRecyclerView.setAdapter(recentMovieAdapter);
        recentMovieRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recentMovieRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recentMovieRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(MainActivity.this, MovieDetailActivity.class);
                i.putExtra(MOVIE_DATA, hotMovies.get(position));
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}
