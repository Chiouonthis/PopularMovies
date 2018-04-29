package com.chiouonthis.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    MoviePosterAdapter moviePosterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: Create dummy data to test population of image views

        RecyclerView recyclerView = findViewById(R.id.rvMoviePosters);
        int numberOfColumns = 3; //TODO: Make this dynamic depending on screen orientation
        recyclerView.setLayoutManager(new GridLayoutManager(this,numberOfColumns));
        moviePosterAdapter = new MoviePosterAdapter(numberOfColumns);

        recyclerView.setAdapter(moviePosterAdapter);


    }


}
