package com.example.movietrailerfinder.ui;


import android.content.res.Configuration;
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
import com.example.movietrailerfinder.entities.MovieTrailers;
import com.example.movietrailerfinder.entities.SearchResult;
import com.example.movietrailerfinder.entities.Trailer;
import com.example.movietrailerfinder.tmdbapi.TmdbApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FullMovieInfoFragment extends android.app.Fragment implements View.OnClickListener {
    private View view;
    private ImageView poster;
    private TextView movieId;
    private TextView title;
    private RecyclerView trailersRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private TreilersREcyclerViewAdapter adapter;
    private Movie movie;
    private ArrayList<Trailer> trailers = new ArrayList<>();
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
        TmdbApi.getTmdbApi().getTrailers(movie.getId(), getResources().getString(R.string.api_key)).enqueue(new Callback<MovieTrailers>() {

            @Override
            public void onResponse(Call<MovieTrailers> call, Response<MovieTrailers> response) {
                trailers.clear();
                trailers.addAll(response.body().getResults());
                if (trailers.size() > 0) {
                    trailersRecyclerView = view.findViewById(R.id.trailers_recycler_view);
                    adapter = new TreilersREcyclerViewAdapter(trailers, getActivity());
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        gridLayoutManager = new GridLayoutManager(getActivity(), 3);
                    } else {
                        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                    }
                    trailersRecyclerView.setLayoutManager(gridLayoutManager);
                    trailersRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<MovieTrailers> call, Throwable t) {

            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {

        getActivity().getFragmentManager().popBackStack();

    }

}

