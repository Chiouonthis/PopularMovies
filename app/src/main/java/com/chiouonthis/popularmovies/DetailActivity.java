package com.chiouonthis.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity ";
    private MovieAdapter movieAdapter;
    private RecyclerView trailerRecyclerView;
    private RecyclerView reviewsRecyclerView;
    private RetrofitInterface retrofitInterface;
    private List<Movie> moviesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        ImageView mDetailMoviePoster = findViewById(R.id.dvMoviePosterImage);
        TextView mDetailMovieTitle = findViewById(R.id.dvMovieTitle);
        TextView mDetailReleaseDate = findViewById(R.id.dvReleaseDate);
        TextView mDetailAverageRating = findViewById(R.id.dvVoteAverage);
        TextView mDetailSynopsis = findViewById(R.id.dvPlotSynopsis);

        String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185";
        String posterUrl = POSTER_BASE_URL + Objects.requireNonNull(intent.getExtras()).getString("Movie Poster URL");
        Uri uri = Uri.parse(posterUrl);

        //Load Poster and Text Elements from original API call in Main Activity
        Picasso.get().load(uri).into(mDetailMoviePoster);
        mDetailMovieTitle.setText(intent.getExtras().getString("Movie Title"));
        mDetailReleaseDate.setText(intent.getExtras().getString("Movie Release Date"));
        String TAG = "DetailActivity";
        Log.d(TAG, intent.getExtras().getString("Movie Release Date"));
        mDetailAverageRating.setText(intent.getExtras().getString("Movie Rating"));
        mDetailSynopsis.setText(intent.getExtras().getString("Movie Synopsis"));


        trailerRecyclerView = findViewById(R.id.rvTrailers);

        reviewsRecyclerView = findViewById(R.id.rvReviews);

        //Initialize Adapter, even though it will be empty at first

        //TODO instantiate list to pass to adapter. Should be movie or other based on retrofit call?
        movieAdapter = new MovieAdapter(moviesList);

        trailerRecyclerView.setAdapter(movieAdapter);
        reviewsRecyclerView.setAdapter(movieAdapter);

        //Set up Retrofit
        retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);

        //TODO create recyclerview to store reviews
        //TODO set up API call for reviews
        //TODO populate element with results from above call

        //TODO create recyclerview for trailers
        //TODO set up API call for trailers
        //TODO populate view with response using adapter



    }
}
