package com.example.movietrailerfinder.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.movietrailerfinder.R;
import com.example.movietrailerfinder.entities.Movie;

import java.util.List;

public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewAdapter.MovieViewHolder> {
    List<Movie> results;

    public ListRecyclerViewAdapter(List results) {
        this.results = results;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.movieId.setText(results.get(position).getId().toString());
        holder.movieTitle.setText(results.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView movieId;
        TextView movieTitle;

        public MovieViewHolder(View listViewItem) {
            super(listViewItem);
            movieId = (TextView) listViewItem.findViewById(R.id.movie_id);
            movieTitle = (TextView) listViewItem.findViewById(R.id.movie_title);
        }
    }
}
