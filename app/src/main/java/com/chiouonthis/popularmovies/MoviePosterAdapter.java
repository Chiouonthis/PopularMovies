package com.chiouonthis.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import retrofit2.Callback;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.MovieViewHolder>{

    private static int viewHolderCount;
    PosterClickListener mListener;

    private List<Movie> movies;


    public MoviePosterAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    public MoviePosterAdapter(int numberOfColumns, Callback<List<Movie>> callback, List<Movie> moviesList) {
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.movie_poster;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        holder.bind(position);
        holder.moviePosterImageView.setImageURI(movies.get(position).getPosterUrl());

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView moviePosterImageView;

        public MovieViewHolder(View itemView) {
            super(itemView);

            moviePosterImageView = (ImageView) itemView.findViewById(R.id.ivMoviePosterImage);
            itemView.setOnClickListener(this);
        }


        void bind(int listIndex) {

            //TODO complete method body to set the image of the image view from API response

        }


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mListener.onPosterClick(clickedPosition);
        }
    }

    public interface PosterClickListener{
        void onPosterClick(int posterIndex);

    }

}





