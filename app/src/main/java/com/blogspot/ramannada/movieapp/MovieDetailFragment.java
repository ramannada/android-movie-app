package com.blogspot.ramannada.movieapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {


    public MovieDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final Context contextThemeManager = new ContextThemeWrapper(getActivity(), R.style.ChildActivityTheme);
        LayoutInflater layoutInflater = inflater.cloneInContext(contextThemeManager);

        View rootView = layoutInflater.inflate(R.layout.fragment_movie_detail, container, false);


//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);;

        ImageView ivPoster = rootView.findViewById(R.id.iv_poster);
        TextView tvTitle = rootView.findViewById(R.id.tv_title);
        TextView tvReleaseDate = rootView.findViewById(R.id.tv_release_date);
        TextView tvDirector = rootView.findViewById(R.id.tv_director);
        TextView tvDuration = rootView.findViewById(R.id.tv_duration);

        Movie movie = getArguments().getParcelable(MainFragment.MOVIE_DATA);

        ivPoster.setImageResource(movie.getPoster());
        tvTitle.setText(movie.getTitle());
        tvReleaseDate.setText(movie.getReleaseDate());
        tvDirector.setText(movie.getDirector());
        tvDuration.setText(movie.getDuration());

        return rootView;
    }

}
