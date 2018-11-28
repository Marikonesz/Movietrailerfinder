package com.example.movietrailerfinder.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.movietrailerfinder.R;
import com.example.movietrailerfinder.entities.Trailer;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

public class TreilersREcyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Trailer> trailers;
    Context context;

    public TreilersREcyclerViewAdapter(List trailers, Context context) {
        this.trailers = trailers;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem;
        viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_view_item, parent, false);
        TrailerViewHolder holder = new TrailerViewHolder(viewItem);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        TrailerViewHolder trailerViewHolder = (TrailerViewHolder) holder;
        trailerViewHolder.youTubePlayerView.initialize(context.getResources().getString(R.string.youtube_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.cueVideo(trailers.get(position).getKey());
                youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                    @Override
                    public void onPlaying() {
                        youTubePlayer.setFullscreen(true);
                    }

                    @Override
                    public void onPaused() {

                    }

                    @Override
                    public void onStopped() {
                        youTubePlayer.setFullscreen(false);

                    }

                    @Override
                    public void onBuffering(boolean b) {

                    }

                    @Override
                    public void onSeekTo(int i) {

                    }
                });


            }


            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        YouTubePlayerView youTubePlayerView;
        private ItemOnClickListener itemOnClickListener;

        public TrailerViewHolder(View trailerViewItem) {
            super(trailerViewItem);

            youTubePlayerView = trailerViewItem.findViewById(R.id.youtube_player);

            trailerViewItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemOnClickListener.onClick(view, getAdapterPosition());

        }
    }

}
