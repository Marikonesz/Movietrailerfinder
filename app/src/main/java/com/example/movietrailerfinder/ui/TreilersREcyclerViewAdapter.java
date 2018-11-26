package com.example.movietrailerfinder.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.movietrailerfinder.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class TreilersREcyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem;
        viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item, parent, false);
        TrailerViewHolder holder = new TrailerViewHolder(viewItem);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
TrailerViewHolder trailerViewHolder = (TrailerViewHolder) holder;
trailerViewHolder.youTubePlayerView.initialize("AIzaSyBhWKFl9cqepUBlsnjcwk3JbQrziSIYlIw", new YouTubePlayer.OnInitializedListener() {
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo("Pdpvd2eqfYc");
        youTubePlayer.play();
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
});
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        YouTubePlayerView youTubePlayerView;
        private ItemOnClickListener itemOnClickListener;

        public TrailerViewHolder(View trailerViewItem) {
            super(trailerViewItem);
            itemOnClickListener = trailerViewItem.findViewById(R.id.youtube_player);

            trailerViewItem.setOnClickListener(this);
        }

        public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
            this.itemOnClickListener = itemOnClickListener;
        }

        @Override
        public void onClick(View view) {
            itemOnClickListener.onClick(view, getAdapterPosition());

        }
    }

}
