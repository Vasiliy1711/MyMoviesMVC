package com.example.mymoviesmvc.ui.act_detail;

import android.view.View;

import com.example.mymoviesmvc.data.Movie;
import com.example.mymoviesmvc.data.Review;
import com.example.mymoviesmvc.data.Trailer;

import java.util.List;

public interface DetailActMVP
{
    interface MVPView
    {
        View getRootView();
        void registerPresenter(Presenter presenter);
        void showMovie(Movie movie);
        void setReviews(List<Review> reviews);
        void setTrailers(List<Trailer> trailers);

    }

    interface Presenter
    {
        void addToFavourite(Movie movie);
    }
}
