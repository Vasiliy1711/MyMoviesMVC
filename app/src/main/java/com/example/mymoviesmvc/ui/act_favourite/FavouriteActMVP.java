package com.example.mymoviesmvc.ui.act_favourite;

import android.view.View;

import com.example.mymoviesmvc.data.Movie;

import java.util.List;

public interface FavouriteActMVP
{
    interface MVPView
    {
        View getRootView();
        void registerPresenter(Presenter presenter);
        void setMovieList(List<Movie> movieList);
    }

    interface Presenter
    {

    }
}
