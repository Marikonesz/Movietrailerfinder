package com.example.movietrailerfinder.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.movietrailerfinder.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment {
    View view;
    RecyclerView listRecyclerView;
    ListRecyclerViewAdapter adapter = new ListRecyclerViewAdapter(StartScreenFragment.getResults());
    public ListViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     view = inflater.inflate(R.layout.fragment_list_view, container, false);
     listRecyclerView = view.findViewById(R.id.list_recycler_view);
     listRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
     listRecyclerView.setAdapter(adapter);
        return view;

    }

}