package com.chiouonthis.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

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

        Picasso.get().load(uri).into(mDetailMoviePoster);
        mDetailMovieTitle.setText(intent.getExtras().getString("Movie Title"));
        mDetailReleaseDate.setText(intent.getExtras().getString("Movie Release Date"));
        String TAG = "DetailActivity";
        Log.d(TAG, intent.getExtras().getString("Movie Release Date"));
        mDetailAverageRating.setText(intent.getExtras().getString("Movie Rating"));
        mDetailSynopsis.setText(intent.getExtras().getString("Movie Synopsis"));

    }
}
