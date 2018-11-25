package com.example.movietrailerfinder.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movietrailerfinder.R;
import com.example.movietrailerfinder.entities.Movie;

/**
 * A simple {@link Fragment} subclass.
 */
public class FullMovieInfoFragment extends android.app.Fragment {
    private View view;
    private ImageView poster;
    private TextView movieId;
    private TextView title;
    private RecyclerView trailersRecyclerView;



    public FullMovieInfoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      view = inflater.inflate(R.layout.fragment_full_movie_info, container, false);
      poster = view.findViewById(R.id.full_info_poster);
      movieId = view.findViewById(R.id.full_info_id);
      title = view.findViewById(R.id.full_info_name);
        return view;
    }

}
