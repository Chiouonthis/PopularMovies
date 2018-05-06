package com.chiouonthis.popularmovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

        @GET("discover/movie?sort_by=popularity.desc?language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&api_key=")
        Call<MovieResults> getPopularMovies(@Query("api_key") String apiKey);

}
