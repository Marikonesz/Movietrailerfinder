package com.example.movietrailerfinder.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movietrailerfinder.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultsViewFragment extends android.app.Fragment {
    View view;
    public static RecyclerView resultsRecyclerView;
    ResultsRecyclerViewAdapter adapter = new ResultsRecyclerViewAdapter(StartScreenFragment.getResults());
    public static final String TAG = ResultsViewFragment.class.getCanonicalName();

    public ResultsViewFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_view, container, false);
        resultsRecyclerView = view.findViewById(R.id.list_recycler_view);
        resultsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        resultsRecyclerView.setAdapter(adapter);
        if (getActivity().getFragmentManager().findFragmentByTag(ResultsViewFragment.TAG) == null) {
//TODO add fragment moviefullinfo
        }

        return view;

    }
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("areResultsDisplayed", true);


    }

}
