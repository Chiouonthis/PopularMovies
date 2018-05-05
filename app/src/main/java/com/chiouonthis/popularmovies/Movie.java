package com.chiouonthis.popularmovies;

import android.net.Uri;

public class Movie {


    public String title;
    public String releaseDate;
    public Float averageVote;
    public String synopsis;
    public Uri posterUrl;

    public Uri getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(Uri posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Float getAverageVote() {
        return averageVote;
    }

    public void setAverageVote(Float averageVote) {
        this.averageVote = averageVote;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
