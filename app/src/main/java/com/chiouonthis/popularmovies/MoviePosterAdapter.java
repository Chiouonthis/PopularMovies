package com.chiouonthis.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.MovieViewHolder>{

    private static int viewHolderCount;
    private int itemCount;
    PosterClickListener mListener;


    public MoviePosterAdapter(int itemCount,PosterClickListener listener ) {
        this.itemCount = itemCount;
        mListener = listener;
        viewHolderCount = 0;
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
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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





