package com.blogspot.ramannada.movieapp;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("array list movie db ", String.valueOf(db.getAllMovie().isEmpty()));
        hotMovies = db.getAllMovie();

//        hotMovies.add(new Movie("Spider-Man 1", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
//        hotMovies.add(new Movie("Spider-Man 2", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
//        hotMovies.add(new Movie("Spider-Man 3", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
//        hotMovies.add(new Movie("Spider-Man 4", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
//        hotMovies.add(new Movie("Spider-Man 5", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
//        hotMovies.add(new Movie("Spider-Man 6", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
//        hotMovies.add(new Movie("Spider-Man 7", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
//        hotMovies.add(new Movie("Spider-Man 8", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
//        hotMovies.add(new Movie("Spider-Man 9", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
//        hotMovies.add(new Movie("Spider-Man 10", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));


        recentMovies.add(new Movie("Spider-Man 11", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        recentMovies.add(new Movie("Spider-Man 12", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        recentMovies.add(new Movie("Spider-Man 13", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        recentMovies.add(new Movie("Spider-Man 14", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        recentMovies.add(new Movie("Spider-Man 15", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        recentMovies.add(new Movie("Spider-Man 16", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        recentMovies.add(new Movie("Spider-Man 17", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        recentMovies.add(new Movie("Spider-Man 18", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        recentMovies.add(new Movie("Spider-Man 19", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        recentMovies.add(new Movie("Spider-Man 20", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));


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
