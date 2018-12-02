package com.example.movietrailerfinder.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
        RecyclerView.ViewHolder holder;
        if (trailers.size() > 0) {
            viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_view_item, parent, false);
            holder = new TrailerViewHolder(viewItem);
        } else {
            viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.no_trailer_item, parent, false);
            holder = new EmptyViewHolder(viewItem);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (trailers.size() > 0) {
            TrailerViewHolder trailerViewHolder = (TrailerViewHolder) holder;
            trailerViewHolder.setItemOnClickListener(new ItemOnClickListener() {
                @Override
                public void onClick(View v, final int position) {
                    final YouTubePlayerFragment youTubePlayerFragment = new YouTubePlayerFragment();
                    activity.getFragmentManager().beginTransaction().replace(R.id.fragments_container, youTubePlayerFragment).addToBackStack(null).commit();

                    youTubePlayerFragment.initialize(activity.getResources().getString(R.string.youtube_key), new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
                            youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                                @Override
                                public void onLoading() {

                                }

                                @Override
                                public void onLoaded(String s) {
                                    youTubePlayer.play();
                                    youTubePlayer.setFullscreen(true);
                                }

                                @Override
                                public void onAdStarted() {

                                }

                                @Override
                                public void onVideoStarted() {

                                }

                                @Override
                                public void onVideoEnded() {
                                    activity.getFragmentManager().popBackStack();
                                }

                                @Override
                                public void onError(YouTubePlayer.ErrorReason errorReason) {

                                }
                            });

                            youTubePlayer.setShowFullscreenButton(false);
                            youTubePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);

                            youTubePlayer.cueVideo(trailers.get(position).getKey());
                        }


                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider
                                                                    provider, YouTubeInitializationResult youTubeInitializationResult) {
                            activity.getFragmentManager().popBackStack();
                        }
                    });
                }
            });


        } else {
            EmptyViewHolder emptyViewHolder = (EmptyViewHolder) holder;
            emptyViewHolder.noTrailerPicture.setImageDrawable(activity.getDrawable(R.drawable.no_trailer));
        }
    }

    @Override
    public int getItemCount() {
        int count = 1;
        if (trailers.size() > 0) {
            count = trailers.size();
        } else if (trailers.size() > 20) {
            count = 20;
        }
        return count;
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageButton playVideoButton;
        private ItemOnClickListener itemOnClickListener;


        public TrailerViewHolder(View trailerViewItem) {
            super(trailerViewItem);
            playVideoButton = trailerViewItem.findViewById(R.id.play_video_button);
            playVideoButton.setImageDrawable(activity.getDrawable(R.drawable.play_video));
            playVideoButton.setOnClickListener(this);
        }

        public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
            this.itemOnClickListener = itemOnClickListener;
        }

        @Override
        public void onClick(View view) {
            itemOnClickListener.onClick(view, getAdapterPosition());
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
