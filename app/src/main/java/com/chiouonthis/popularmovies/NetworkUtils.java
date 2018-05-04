package com.chiouonthis.popularmovies;

import com.chiouonthis.popularmovies.Movie;
import com.chiouonthis.popularmovies.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class NetworkUtils {


private static final String BASE_MOVIE_POSTER_URL = "http://image.tmdb.org/t/p/";
private static final String POPULAR_MOVIES_BASE_URL = "/discover/movie?sort_by=popularity.desc";

     //example URL https://api.themoviedb.org/3/movie/76341?api_key={api_key}
    //example poster URL http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg


    public interface MovieDBService {
        @GET(POPULAR_MOVIES_BASE_URL+ "?api_key={api_key}")
        Call<List<Movie>> listMovies(@Query("api_key") String apiKey);
    }




}
