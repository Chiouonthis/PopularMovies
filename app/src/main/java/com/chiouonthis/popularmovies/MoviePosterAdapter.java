package com.chiouonthis.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;


public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.MovieViewHolder> {


    public interface PosterClickListener {

        void onPosterClick(Movie movie);
    }

    private final PosterClickListener mListener;
    private final List<Movie> movies;
    private static final String POSTER_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185";


    public MoviePosterAdapter(List<Movie> movies, PosterClickListener listener) {
        this.movies = movies;
        this.mListener = listener;
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.movie_poster;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        holder.bind(movies.get(position), mListener);
        String posterUrl = POSTER_IMAGE_BASE_URL + movies.get(position).getPoster_path();
        Uri uri = Uri.parse(posterUrl);
        Picasso.get().load(uri).into(holder.moviePosterImageView);

        //holder.movieTitle.setText(movies.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    // MovieView Holder Class
    static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView moviePosterImageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            moviePosterImageView = itemView.findViewById(R.id.ivMoviePosterImage);
        }


        void bind(final Movie movie, final PosterClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    listener.onPosterClick(movie);
                    Toast.makeText(itemView.getContext(), movie.getTitle(),
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);

                    intent.putExtra("Movie Title", movie.title)
                            .putExtra("Movie Synopsis", movie.overview)
                            .putExtra("Movie Rating", movie.vote_average.toString())
                            .putExtra("Movie Release Date", movie.release_date)
                            .putExtra("Movie Poster URL", movie.poster_path);

                    v.getContext().startActivity(intent);

                }
            });

        }


    }
}






