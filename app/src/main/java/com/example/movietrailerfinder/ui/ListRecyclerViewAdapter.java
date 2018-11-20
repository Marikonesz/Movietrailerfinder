package com.example.movietrailerfinder.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.movietrailerfinder.R;

import java.util.List;

public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewAdapter.MovieViewHolder> {
    List results;

    public ListRecyclerViewAdapter(List results) {
        this.results = results;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView movieId;
        TextView movieTitle;

        public MovieViewHolder(View listViewItem) {
            super(listViewItem);
            movieId = (TextView) listViewItem.findViewById(R.id.movie_id);
            movieTitle =  (TextView)listViewItem.findViewById(R.id.movie_title);
        }
    }
}
