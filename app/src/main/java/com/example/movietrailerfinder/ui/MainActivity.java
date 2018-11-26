package com.example.movietrailerfinder.ui;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.movietrailerfinder.R;
import com.google.android.youtube.player.YouTubeBaseActivity;

public class MainActivity extends YouTubeBaseActivity {
    FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            if (fragmentManager.findFragmentByTag(StartScreenFragment.TAG) == null)
                fragmentManager.beginTransaction().replace(R.id.fragments_container, new StartScreenFragment(), StartScreenFragment.TAG)
                        .commit();
        }
    }
}




