package com.chiouonthis.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MoviePosterAdapter.PosterClickListener {


    private MoviePosterAdapter moviePosterAdapter;
    private RecyclerView posterRecyclerView;
    private static final String TAG = "MainActivity ";
    private static int numberOfColumns = 4; //TODO: Make this dynamic depending on screen orientation
    private List<Movie> moviesList = new ArrayList<>();
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up RecyclerView
        posterRecyclerView = findViewById(R.id.rvMoviePosters);
        posterRecyclerView.setLayoutManager(new GridLayoutManager(this,numberOfColumns));
        posterRecyclerView.setHasFixedSize(true);

        //Initialize Adapter, even though it will be empty at first
        moviePosterAdapter = new MoviePosterAdapter(moviesList);
        posterRecyclerView.setAdapter(moviePosterAdapter);

        //Set up Retrofit
        retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);

        //Build request
        Call<MovieResults> request = retrofitInterface.getPopularMovies(getResources().getString(R.string.MovieDbAPIKey));

        //Make async request
        request.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {

                //Get MovieResults object and parse out list of movies from it
                MovieResults movieResults = response.body();
                moviesList = movieResults.getMovies();

                //Iterate through list of Movie objects
                for (Movie movie : moviesList) {
                    Log.d(TAG, movie.title);

                    Log.d(TAG, movie.poster_path);
                    Log.d(TAG, movie.overview);
                    Log.d(TAG, movie.release_date);
                    Log.d(TAG, movie.vote_average.toString());

                }

                moviePosterAdapter = new MoviePosterAdapter(moviesList);

                posterRecyclerView.setAdapter(moviePosterAdapter);


            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {

                Log.d(TAG, t.toString());

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch(itemId){

        //TODO fill this out with cases for favorites, popular, chronological, and alphabetical sorts

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPosterClick(int posterIndex) {

        Toast mToast = Toast.makeText(this, "POSTER CLICKED!", Toast.LENGTH_LONG);
        mToast.show();

    }

}

