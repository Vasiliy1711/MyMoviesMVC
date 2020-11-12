package com.example.mymoviesmvc.ui.act_detail;

import android.view.View;

import com.example.mymoviesmvc.common.BaseActMVP;
import com.example.mymoviesmvc.data.Movie;
import com.example.mymoviesmvc.data.Review;
import com.example.mymoviesmvc.data.Trailer;

import java.util.List;

public interface DetailActMVP
{
    interface MVPView extends BaseActMVP<Presenter>
    {

        void showMovie(Movie movie);
        void setReviews(List<Review> reviews);
        void setTrailers(List<Trailer> trailers);

    }

    interface Presenter
    {
        void addToFavourite(Movie movie);
    }
}
