package com.blogspot.ramannada.movieapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    public static final String MOVIE_DATA = "movie data";
    ArrayList<Movie> hotMovies = new ArrayList<>();
    ArrayList<Movie> recentMovies = new ArrayList<>();

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        hotMovies =(ArrayList<Movie>) Movie.getAllMovie();
        Log.d("array hot movies", hotMovies.toString());

        hotMovies.add(new Movie("Spider-Man 1", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        hotMovies.add(new Movie("Spider-Man 2", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        hotMovies.add(new Movie("Spider-Man 3", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        hotMovies.add(new Movie("Spider-Man 4", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        hotMovies.add(new Movie("Spider-Man 5", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        hotMovies.add(new Movie("Spider-Man 6", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        hotMovies.add(new Movie("Spider-Man 7", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        hotMovies.add(new Movie("Spider-Man 8", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        hotMovies.add(new Movie("Spider-Man 9", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));
        hotMovies.add(new Movie("Spider-Man 10", "Jon Watts", "2h 3m","July 5, 2017", R.drawable.pocdtnt));

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


        TextView tvLogout =  rootView.findViewById(R.id.tv_logout);
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedData.getSharedData().clearLoginStatus();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, new LoginFragment())
                        .commit();
            }
        });

        RecyclerView hotMovieRecyclerView =  rootView.findViewById(R.id.recycler_view_hot_movie);
        RecyclerView.Adapter hotMovieAdapter = new MovieAdapter(hotMovies);
        hotMovieRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        hotMovieRecyclerView.setAdapter(hotMovieAdapter);

        hotMovieRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), hotMovieRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                Fragment movieDetail = new MovieDetailFragment();
                movieDetail.setArguments(bundle);

                bundle.putParcelable(MOVIE_DATA, hotMovies.get(position));
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, movieDetail)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        RecyclerView recentMovieRecyclerView = rootView.findViewById(R.id.recycler_view_recent_movie);
        RecyclerView.Adapter recentMovieAdapter = new MovieAdapter(recentMovies);
        recentMovieRecyclerView.setAdapter(recentMovieAdapter);
        recentMovieRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        recentMovieRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recentMovieRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                Fragment movieDetail = new MovieDetailFragment();
                movieDetail.setArguments(bundle);

                bundle.putParcelable(MOVIE_DATA, recentMovies.get(position));
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, movieDetail)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return rootView;
    }



}
