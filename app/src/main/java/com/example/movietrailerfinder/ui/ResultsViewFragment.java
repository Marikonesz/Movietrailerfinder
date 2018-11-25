package com.example.movietrailerfinder.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.movietrailerfinder.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultsViewFragment extends android.app.Fragment implements View.OnClickListener {
    View view;
    public static RecyclerView resultsRecyclerView;
    Button returnButton;
    ResultsRecyclerViewAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    public static final String TAG = ResultsViewFragment.class.getCanonicalName();

    public ResultsViewFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_results_view, container, false);
        returnButton = view.findViewById(R.id.return_button);
        returnButton.setOnClickListener(this);
        resultsRecyclerView = view.findViewById(R.id.list_recycler_view);
        if (StartScreenFragment.kindOfView == R.id.image_view_button) {
            linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        } else {
            linearLayoutManager = new LinearLayoutManager(getActivity());
        }
        resultsRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = adapter = new ResultsRecyclerViewAdapter(StartScreenFragment.getResults(), getActivity());
        resultsRecyclerView.setAdapter(adapter);


        return view;

    }


    @Override
    public void onClick(View view) {
        if (getActivity().getFragmentManager().findFragmentByTag(StartScreenFragment.TAG) == null)
            getActivity().getFragmentManager().beginTransaction().replace(R.id.fragments_container, new StartScreenFragment(), StartScreenFragment.TAG)
                    .commit();
    }
}
