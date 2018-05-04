package com.chiouonthis.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MoviePosterAdapter.PosterClickListener, NetworkUtils.MovieDBService{


    private MoviePosterAdapter moviePosterAdapter;
    private RecyclerView posterRecyclerView;
    private ImageView moviePosterImage;
    private static final String TAG = "MainActivity ";
    private static int numberOfColumns = 3; //TODO: Make this dynamic depending on screen orientation
    private static final String BASE_API_URL = "https://api.themoviedb.org";
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

        try {

            Response<List<Movie>> listResponse = listMovies(getResources().getString(R.string.MovieDbAPIKey)).enqueue(new Callback<List<Movie>>() {
                @Override
                public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                    response = response.body();
                }

                @Override
                public void onFailure(Call<List<Movie>> call, Throwable t) {

                }
            });
            Log.d(TAG, listResponse.toString());

            // TODO Parse JSON?
            List<Movie> movies = listResponse.body();
            for (int i = 0; i < movies.size(); i++) {

                moviePosterImage = findViewById(R.id.ivMoviePosterImage);
                moviePosterTitle = findViewById(R.id.tvMovieTitle);
                // EXAMPLE http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
                moviePosterTitle.setText(movies.get(i).getTitle());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


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

    @Override
    public Call<List<Movie>> listMovies(String apiKey) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkUtils.MovieDBService service = retrofit.create(NetworkUtils.MovieDBService.class);
        Call<List<Movie>> popularMoviesList = service.listMovies(apiKey);

        return popularMoviesList;
    }
    

}

