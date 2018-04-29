package com.chiouonthis.popularmovies;

public class MovieDetails {


    public String title;
    public String releaseDate;
    public Float averageVote;
    public String synopsis;

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
