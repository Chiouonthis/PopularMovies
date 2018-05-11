package com.chiouonthis.popularmovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("discover/movie?language=en-US&include_adult=false&include_video=false&page=1")
    Call<MovieResults> getMovies(@Query("api_key") String apiKey, @Query("sort_by") String sort_by);

    @GET("movie/popular")
    Call<MovieResults> getTrendingMovies(@Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<MovieResults> getTopRatedMovies(@Query("api_key") String api_key);

}
