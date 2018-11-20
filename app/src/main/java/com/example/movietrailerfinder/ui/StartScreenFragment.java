package com.example.movietrailerfinder.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
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
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.list_view_button:
                sendSearchReqest(queryField.getText().toString());
                if(getActivity().getFragmentManager().findFragmentByTag(ListViewFragment.TAG) == null){
                    getActivity().getFragmentManager().beginTransaction().add(R.id.fragments_container, new ListViewFragment(), ListViewFragment.TAG)
                            .commit();                }
                break;
            case R.id.image_view_button:
                sendSearchReqest(queryField.getText().toString());
        }


    }

    private void sendSearchReqest(String query) {
        TmdbApi.getTmdbApi().serchMovies(getActivity().getString(R.string.api_key), query).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                results.clear();
                if (response.code() == 200)
                    results.addAll(response.body().getResults());
                else {
                    Toast.makeText(getActivity(), response.code() + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {

            }
        });
    }
    public static List getResults(){
        return results;
    }
}
