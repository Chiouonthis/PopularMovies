package com.chiouonthis.popularmovies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class MainActivity extends AppCompatActivity {


    private MoviePosterAdapter moviePosterAdapter;
    private RecyclerView posterRecyclerView;
    private static final String TAG = "MainActivity ";
    private List<Movie> moviesList = new ArrayList<>();
    private RetrofitInterface retrofitInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int numberOfColumns;
        if (getResources().getConfiguration().orientation != ORIENTATION_LANDSCAPE) {
            numberOfColumns = 2;
        } else {
            numberOfColumns = 4;
        }

        //Set up RecyclerView
        posterRecyclerView = findViewById(R.id.rvMoviePosters);
        posterRecyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        //posterRecyclerView.setHasFixedSize(true);

        //Initialize Adapter, even though it will be empty at first
        moviePosterAdapter = new MoviePosterAdapter(moviesList);
        posterRecyclerView.setAdapter(moviePosterAdapter);

        //Set up Retrofit
        retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);

        //Make initial request
        //TODO used shared preferences to persist user choice
        Call<MovieResults> request = retrofitInterface.getTrendingMovies(getResources().getString(R.string.MovieDbAPIKey));
        makeAPIRequest(request);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        Call<MovieResults> request;

        switch(itemId){

            case R.id.menuTrending:
                request = retrofitInterface.getTrendingMovies(getResources().getString(R.string.MovieDbAPIKey));
                makeAPIRequest(request);
                break;
            case R.id.menuHighestRated:

                request = retrofitInterface.getTopRatedMovies(getResources().getString(R.string.MovieDbAPIKey));
                makeAPIRequest(request);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void makeAPIRequest(Call<MovieResults> request) {

        //Make async request
        request.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(@NonNull Call<MovieResults> call, @NonNull Response<MovieResults> response) {

                //Get MovieResults object and parse out list of movies from it
                MovieResults movieResults = response.body();
                Log.d(TAG, Objects.requireNonNull(movieResults).toString());
                moviesList = movieResults.getMovies();

                //Iterate through list of Movie objects
                for (Movie movie : moviesList) {
                    Log.d(TAG, movie.title);
                    Log.d(TAG, "Poster path: " + movie.poster_path); //TODO gracefully handle Null poster path
                    Log.d(TAG, movie.overview);
                    Log.d(TAG, movie.release_date);
                    Log.d(TAG, movie.vote_average.toString());

                }

                //TODO get screen resolution and pass in to adapter to get correct poster image size
                moviePosterAdapter = new MoviePosterAdapter(moviesList);
                posterRecyclerView.setAdapter(moviePosterAdapter);


            }

            @Override
            public void onFailure(@NonNull Call<MovieResults> call, @NonNull Throwable t) {

                Log.d(TAG, t.toString());

            }
        });
    }


}

