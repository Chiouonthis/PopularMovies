package com.chiouonthis.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private final String TAG = "DetailActivity";
    private ImageView mDetailMoviePoster;
    private TextView mDetailMovieTitle;
    private TextView mDetailReleaseDate;
    private TextView mDetailAverageRating;
    private TextView mDetailSynopsis;
    public final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        mDetailMoviePoster = findViewById(R.id.dvMoviePosterImage);
        mDetailMovieTitle = findViewById(R.id.dvMovieTitle);
        mDetailReleaseDate = findViewById(R.id.dvReleaseDate);
        mDetailAverageRating = findViewById(R.id.dvVoteAverage);
        mDetailSynopsis = findViewById(R.id.dvPlotSynopsis);

        String posterUrl = POSTER_BASE_URL + intent.getExtras().getString("Movie Poster URL");
        Uri uri = Uri.parse(posterUrl);

        Picasso.get().load(uri).into(mDetailMoviePoster);
        mDetailMovieTitle.setText(intent.getExtras().getString("Movie Title"));
        mDetailReleaseDate.setText(intent.getExtras().getString("Movie Release Date"));
        Log.d(TAG, intent.getExtras().getString("Movie Release Date"));
        mDetailAverageRating.setText(intent.getExtras().getString("Movie Rating"));
        mDetailSynopsis.setText(intent.getExtras().getString("Movie Synopsis"));

    }
}
