package com.a7apps.tvbase.ui.movies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.a7apps.tvbase.R;
import com.a7apps.tvbase.adapter.AdapMovies;

public class MoviesFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private static final long START_TIME_IN_MILLIS = 7000;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private boolean mTimmerRunning;
    private CountDownTimer mCountDownTimer;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movies, container, false);
        recyclerView = root.findViewById(R.id.rvMovies1);
        progressBar = root.findViewById(R.id.progressMovies);
        doSomething();
        return root;
    }

    public void doSomething(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                recyclerView.setAdapter(new AdapMovies(getContext()));
            }
        };
        thread.start();
    }
    public void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                progressBar.setVisibility(View.INVISIBLE);
                mTimmerRunning = false;
                recyclerView.setAdapter(new AdapMovies(getContext()));
            }
        }.start();
    }
}