package com.example.movietrailerfinder.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movietrailerfinder.R;
import com.example.movietrailerfinder.entities.Movie;
import com.example.movietrailerfinder.entities.SearchResult;
import com.example.movietrailerfinder.tmdbapi.TmdbApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StartScreenFragment extends Fragment implements View.OnClickListener {
    private View view;
    private EditText queryField;
    private Button listViewButton;
    private Button imageViewButton;
    private static ArrayList<Movie> results = new ArrayList();
    public static final String TAG = StartScreenFragment.class.getCanonicalName();
    public static int kindOfView;


    public StartScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_start_screen, container, false);
        queryField = view.findViewById(R.id.query_field);
        listViewButton = view.findViewById(R.id.list_view_button);
        listViewButton.setOnClickListener(this);
        imageViewButton = view.findViewById(R.id.image_view_button);
        imageViewButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

        kindOfView = view.getId();
        sendSearchReqest(queryField.getText().toString());


    }

    private void sendSearchReqest(String query) {
        TmdbApi.getTmdbApi().serchMovies(getActivity().getString(R.string.api_key), query).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (!results.isEmpty())
                    results.clear();
                if (response.code() == 200) {
                    results.addAll(response.body().getResults());
                    if (results.size() > 0) {
                        inflateResultsViewFragment();
                    } else {
                        showToast("No movies were found");
                        queryField.setText("");
                    }
                } else if (response.code() == 422) {
                    showToast("Empty request");

                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                showToast("Please check your network connection");
            }
        });
    }

    private void inflateResultsViewFragment() {
        if (getActivity().getFragmentManager().findFragmentByTag(ResultsViewFragment.TAG) == null) {
            getActivity().getFragmentManager().beginTransaction().replace(R.id.fragments_container, new ResultsViewFragment(), ResultsViewFragment.TAG)
                    .addToBackStack(null).commit();
        }
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    public static List getResults() {
        return results;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("results", results);


    }
}
