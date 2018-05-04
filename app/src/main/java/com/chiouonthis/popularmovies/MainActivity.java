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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface MovieDBService {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("discover/movie?sort_by=popularity.desc?language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&api_key=")
    Call<List<Movie>> listMovies(@Query("api_key") String apiKey);


}

public class MainActivity extends AppCompatActivity implements MoviePosterAdapter.PosterClickListener, MovieDBService {


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

        listMovies(getResources().getString(R.string.MovieDbAPIKey))
        ;
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
    public Call<List<Movie>> listMovies(String apiKey) {
        MovieDBService service = retrofit.create(MovieDBService.class);
        Call<List<Movie>> popularMoviesList = service.listMovies(apiKey);

        popularMoviesList.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {

                moviePosterTitle.setText(response.body().toString());
                Log.d(TAG, response.body().toString());

            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

                Log.d(TAG, t.toString());

            }
        });

        return popularMoviesList;

    }

    @Override
    public void onPosterClick(int posterIndex) {

        Toast mToast = Toast.makeText(this, "POSTER CLICKED!", Toast.LENGTH_LONG);
        mToast.show();

    }
}

