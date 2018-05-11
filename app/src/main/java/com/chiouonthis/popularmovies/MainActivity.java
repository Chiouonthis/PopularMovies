package com.chiouonthis.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class MainActivity extends AppCompatActivity {


    private MoviePosterAdapter moviePosterAdapter;
    private RecyclerView posterRecyclerView;
    private static final String TAG = "MainActivity ";
    private static int numberOfColumns;
    private List<Movie> moviesList = new ArrayList<>();
    private RetrofitInterface retrofitInterface;

    private String mostPopularOption = "popularity.desc";
    private String topRatedOption = "vote_average.desc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getResources().getConfiguration().orientation != ORIENTATION_LANDSCAPE) {
            numberOfColumns = 2;
        } else {
            numberOfColumns = 4;
        }

        //Set up RecyclerView
        posterRecyclerView = findViewById(R.id.rvMoviePosters);
        posterRecyclerView.setLayoutManager(new GridLayoutManager(this,numberOfColumns));
        posterRecyclerView.setHasFixedSize(true);

        //Initialize Adapter, even though it will be empty at first
        moviePosterAdapter = new MoviePosterAdapter(moviesList, new MoviePosterAdapter.PosterClickListener() {
            @Override
            public void onPosterClick(Movie movie) {
            }
        }); //TODO pass in listener
        posterRecyclerView.setAdapter(moviePosterAdapter);

        //Set up Retrofit
        retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);

        //Make request

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

            case R.id.menuTrending:
                makeAPIRequest(mostPopularOption);
                break;
            case R.id.menuHighestRated:
                makeAPIRequest(topRatedOption);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void makeAPIRequest(String option) {

        Call<MovieResults> request = retrofitInterface.getPopularMovies(getResources().getString(R.string.MovieDbAPIKey), option);
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
                    Log.d(TAG, "Poster path: " + movie.poster_path); //TODO handle Null poster path
                    Log.d(TAG, movie.overview);
                    Log.d(TAG, movie.release_date);
                    Log.d(TAG, movie.vote_average.toString());

                }

                moviePosterAdapter = new MoviePosterAdapter(moviesList, new MoviePosterAdapter.PosterClickListener() {
                    @Override
                    public void onPosterClick(Movie movie) {
                        //TODO What goes here?

                    }
                });
                posterRecyclerView.setAdapter(moviePosterAdapter);


            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {

                Log.d(TAG, t.toString());

            }
        });
    }


}

