package com.example.mymoviesmvc.networking.api;


import com.example.mymoviesmvc.data.Review;
import com.example.mymoviesmvc.networking.pojo.MoviesResponse;
import com.example.mymoviesmvc.networking.pojo.ReviewsResponse;
import com.example.mymoviesmvc.networking.pojo.TrailerResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService
{
    @GET("3/discover/movie")
    Observable<MoviesResponse> getMoviesFromNetwork(@Query("api_key") String apiKey,
                                                    @Query("language") String lang,
                                                    @Query("sort_by") String sortBy,
                                                    @Query("vote_count.gte") String voteCount,
                                                    @Query("page") String page);

    @GET("3/movie/{movieId}/reviews")
    Observable<ReviewsResponse> getReviewFromNetwork(@Path("movieId") int movieId,
                                                     @Query("api_key") String apiKey,
                                                     @Query("page") String page);

    @GET("3/movie/{movieId}/videos")
    Observable<TrailerResponse> getTrailerFromNetwork(@Path("movieId") int movieId,
                                                      @Query("api_key") String apiKey);


}
