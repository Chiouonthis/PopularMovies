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
    private static int numberOfColumns = 3; //TODO: Make this dynamic depending on screen orientation
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


        moviePosterAdapter = new MoviePosterAdapter(moviesList);

        posterRecyclerView.setAdapter(moviePosterAdapter);

        //Set up Retrofit
        retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);

        //Build request
        Call<List<Movie>> request = retrofitInterface.getPopularMovies(getResources().getString(R.string.MovieDbAPIKey));

        //Make async request
        request.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {

                moviesList = response.body();
                Log.d(TAG, moviesList.toString());

                moviePosterAdapter = new MoviePosterAdapter(moviesList);

                posterRecyclerView.setAdapter(moviePosterAdapter);

            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

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

