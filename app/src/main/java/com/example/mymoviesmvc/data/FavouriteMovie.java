package com.example.mymoviesmvc.data;

public class FavouriteMovie extends Movie
{
    public FavouriteMovie(int id, int voteCount, String title, String originalTitle, String overview
            , String posterPath, String bigPosterPath, String backdropPath, double voteAverage
            , String releaseDate)
    {
        super(id, voteCount, title, originalTitle, overview, posterPath, bigPosterPath
                , backdropPath, voteAverage, releaseDate);
    }
}
