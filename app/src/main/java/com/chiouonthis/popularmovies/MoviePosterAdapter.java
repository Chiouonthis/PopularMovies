package com.chiouonthis.popularmovies;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.MovieViewHolder> {

    private int itemCount;

    public MoviePosterAdapter(int itemCount) {
        this.itemCount = itemCount;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView moviePosterImageView;


        public MovieViewHolder(View itemView) {
            super(itemView);

            moviePosterImageView = (ImageView) itemView.findViewById(R.id.ivMoviePosterImage);
        }


        void bind(int listIndex) {

            //TODO complete method body to set the image of the image view from API response

        }

    }



}





