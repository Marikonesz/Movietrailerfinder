package com.example.movietrailerfinder.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movietrailerfinder.R;
import com.example.movietrailerfinder.entities.Movie;

/**
 * A simple {@link Fragment} subclass.
 */
public class FullMovieInfoFragment extends android.app.Fragment implements View.OnClickListener{
    private View view;
    private ImageView poster;
    private TextView movieId;
    private TextView title;
    private Button returnButton;
    private RecyclerView trailersRecyclerView;
    private GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),20);
    private ResultsRecyclerViewAdapter adapter;
    private Movie movie;
    public static final String TAG = FullMovieInfoFragment.class.getCanonicalName();


    public FullMovieInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_full_movie_info, container, false);
        movie = this.getArguments().getParcelable("movie");
        poster = view.findViewById(R.id.full_info_poster);
        Glide.with(poster.getContext()).load(getString(R.string.poster_base_url) + movie.getPosterPath()).into(poster);
        movieId = view.findViewById(R.id.full_info_id);
        movieId.setText(movie.getId().toString());
        title = view.findViewById(R.id.full_info_name);
        title.setText(movie.getTitle());
        returnButton = view.findViewById(R.id.return_button_full_info);
        returnButton.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        if (getActivity().getFragmentManager().findFragmentByTag(ResultsViewFragment.TAG) == null)
            getActivity().getFragmentManager().beginTransaction().replace(R.id.fragments_container, new ResultsViewFragment(), StartScreenFragment.TAG)
                    .commit();

        }

    }

