package com.example.movietrailerfinder.adapters;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.movietrailerfinder.R;
import com.example.movietrailerfinder.entities.Trailer;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import java.util.List;

public class TreilersREcyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Trailer> trailers;
    Activity activity;

    public TreilersREcyclerViewAdapter(List trailers, Activity activity) {
        this.trailers = trailers;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem;
        TrailerViewHolder holder;
        if (trailers.size() > 0) {
            viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_view_item, parent, false);

        } else {
            viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.no_trailer_item, parent, false);

        }
        holder = new TrailerViewHolder(viewItem);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (trailers.size() > 0) {
            TrailerViewHolder trailerViewHolder = (TrailerViewHolder) holder;
            trailerViewHolder.youTubePlayerFragment = (YouTubePlayerFragment) activity.getFragmentManager().findFragmentById(R.id.youtube_fragment);
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            trailerViewHolder.youTubePlayerFragment.initialize(activity.getResources().getString(R.string.youtube_key), new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                    youTubePlayer.cueVideo(trailers.get(position).getKey());
                    youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                        @Override
                        public void onFullscreen(boolean b) {
                            youTubePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);
                            if (!youTubePlayer.isPlaying())
                                youTubePlayer.play();
                        }
                    });

                }


                @Override
                public void onInitializationFailure(YouTubePlayer.Provider
                                                            provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            });
        }else {
            EmptyViewHolder emptyViewHolder = (EmptyViewHolder) holder;
            emptyViewHolder.noTrailerPicture.setImageDrawable(activity.getDrawable(R.drawable.no_trailer));
        }
    }

    @Override
    public int getItemCount() {
        int count;
        if (trailers.size() > 0) {
            count = trailers.size();
        } else count = 1;
        return count;
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder {
        YouTubePlayerFragment youTubePlayerFragment;


        public TrailerViewHolder(View trailerViewItem) {
            super(trailerViewItem);
            youTubePlayerFragment = YouTubePlayerFragment.newInstance();

        }


    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        ImageView noTrailerPicture;

        public EmptyViewHolder(View trailerViewItem) {
            super(trailerViewItem);
            noTrailerPicture = trailerViewItem.findViewById(R.id.no_trailer_image);

        }


    }

}
