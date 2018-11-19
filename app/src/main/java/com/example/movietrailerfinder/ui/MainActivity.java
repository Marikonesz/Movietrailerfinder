package com.example.movietrailerfinder.ui;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.movietrailerfinder.R;

public class MainActivity extends AppCompatActivity {
     FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (fragmentManager.findFragmentByTag(StartScreenFragment.TAG) == null)
            fragmentManager.beginTransaction().add(R.id.fragments_container, new StartScreenFragment(), StartScreenFragment.TAG)
                    .commit();

    }
}
