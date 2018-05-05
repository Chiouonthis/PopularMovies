package com.chiouonthis.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MoviePosterAdapter.PosterClickListener, RetrofitInterface {


    private MoviePosterAdapter moviePosterAdapter;
    private RecyclerView posterRecyclerView;
    private ImageView moviePosterImage;
    private static final String TAG = "MainActivity ";
    private static int numberOfColumns = 3; //TODO: Make this dynamic depending on screen orientation
    private TextView moviePosterTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: Create dummy data to test population of image views

        posterRecyclerView = findViewById(R.id.rvMoviePosters);

        posterRecyclerView.setLayoutManager(new GridLayoutManager(this,numberOfColumns));
        moviePosterAdapter = new MoviePosterAdapter(numberOfColumns,this);

        posterRecyclerView.setAdapter(moviePosterAdapter);


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

   /* @Override
    public Call<List<Movie>> listMovies(String apiKey) {

        RetrofitClient.MovieDBService service = retrofit.create(RetrofitClient.MovieDBService.class);
        Call<List<Movie>> popularMoviesList = service.listMovies(apiKey);

        popularMoviesList.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {

                Log.d(TAG, response.toString());

            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

            }
        });

        return popularMoviesList;
    }*/


}

