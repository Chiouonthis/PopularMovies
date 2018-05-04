package com.chiouonthis.popularmovies;

import com.chiouonthis.popularmovies.Movie;
import com.chiouonthis.popularmovies.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class NetworkUtils {

private final String BASE_URL = "https://api.themoviedb.org";

     //example URL https://api.themoviedb.org/3/movie/76341?api_key={api_key}


    public interface MovieDBService {
        @GET(" http://image.tmdb.org/t/p/w185/{movieId}?api_key={api_key}")
        Call<List<Movie>> listMovies(@Path("movieId") String movieId, @Query("api_key") String apiKey);
    }




}
