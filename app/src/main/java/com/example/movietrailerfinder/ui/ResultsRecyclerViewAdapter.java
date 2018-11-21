package com.example.movietrailerfinder.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movietrailerfinder.R;
import com.example.movietrailerfinder.entities.Movie;

import java.util.List;

public class ResultsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Movie> results;

    public ResultsRecyclerViewAdapter(List results) {
        this.results = results;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case R.id.list_view_button:
              viewItem   = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item, parent, false);
                holder = new ListViewHolder(viewItem);
                break;

            case R.id.image_view_button:
                 viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_view_item, parent, false);
                holder = new ImageViewHolder(viewItem);

            default:

        }
return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case R.id.list_view_button:
                ListViewHolder listViewHolder = (ListViewHolder) holder;
                listViewHolder.movieId.setText(results.get(position).getId().toString());
                listViewHolder.movieTitle.setText(results.get(position).getTitle());
                break;
            case R.id.image_view_button:
                ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
                Glide.with(imageViewHolder.poster.getContext()).load(imageViewHolder.poster.getContext().getString(R.string.poster_base_url) + results.get(position).getPosterPath()).into(imageViewHolder.poster);
                imageViewHolder.movieTitle.setText(results.get(position).getTitle());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return StartScreenFragment.kindOfView;
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView movieId;
        TextView movieTitle;

        public ListViewHolder(View listViewItem) {
            super(listViewItem);
            movieId = listViewItem.findViewById(R.id.movie_id);
            movieTitle = listViewItem.findViewById(R.id.movie_title);
        }
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView movieTitle;

        public ImageViewHolder(View listViewItem) {
            super(listViewItem);
            poster = listViewItem.findViewById(R.id.poster);
            movieTitle = listViewItem.findViewById(R.id.poster_title);
        }
    }
}
