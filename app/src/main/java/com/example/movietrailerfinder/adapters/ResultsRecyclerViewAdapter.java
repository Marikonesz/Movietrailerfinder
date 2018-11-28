package com.example.movietrailerfinder.adapters;

import android.app.Activity;
import android.os.Bundle;
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
import com.example.movietrailerfinder.ui.FullMovieInfoFragment;
import com.example.movietrailerfinder.ui.StartScreenFragment;

import java.util.List;

public class ResultsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Movie> results;
    private Activity rootAcivity;

    public ResultsRecyclerViewAdapter(List results, Activity rootAcivity) {

        this.results = results;
        this.rootAcivity = rootAcivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case R.id.list_view_button:
                viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item, parent, false);
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
                listViewHolder.setItemOnClickListener(new ItemOnClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        openFullMovieInfoFragment(position);
                    }
                });
                break;
            case R.id.image_view_button:
                ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
                Glide.with(imageViewHolder.poster.getContext()).load(imageViewHolder.poster.getContext().getString(R.string.poster_base_url) + results.get(position).getPosterPath()).error(R.drawable.emty_poster).into(imageViewHolder.poster);
                imageViewHolder.movieTitle.setText(results.get(position).getTitle());
                imageViewHolder.setItemOnClickListener(new ItemOnClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        openFullMovieInfoFragment(position);

                    }
                });
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

    void openFullMovieInfoFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", results.get(position));
        FullMovieInfoFragment fullMovieInfoFragment = new FullMovieInfoFragment();
        fullMovieInfoFragment.setArguments(bundle);
        rootAcivity.getFragmentManager().beginTransaction().replace(R.id.fragments_container, fullMovieInfoFragment).addToBackStack(null).commit();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView movieId;
        TextView movieTitle;
        private ItemOnClickListener itemOnClickListener;

        public ListViewHolder(View resultsViewItem) {
            super(resultsViewItem);
            movieId = resultsViewItem.findViewById(R.id.movie_id);
            movieTitle = resultsViewItem.findViewById(R.id.movie_title);
            resultsViewItem.setOnClickListener(this);
        }

        public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
            this.itemOnClickListener = itemOnClickListener;
        }

        @Override
        public void onClick(View view) {
            itemOnClickListener.onClick(view, getAdapterPosition());

        }
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView poster;
        TextView movieTitle;
        private ItemOnClickListener itemOnClickListener;

        public ImageViewHolder(View resultsViewItem) {
            super(resultsViewItem);
            poster = resultsViewItem.findViewById(R.id.poster);
            movieTitle = resultsViewItem.findViewById(R.id.poster_title);
            resultsViewItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemOnClickListener.onClick(view, getAdapterPosition());

        }

        public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
            this.itemOnClickListener = itemOnClickListener;
        }
    }
}
