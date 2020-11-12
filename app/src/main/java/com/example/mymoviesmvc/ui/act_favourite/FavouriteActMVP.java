package com.example.mymoviesmvc.ui.act_favourite;

import android.view.View;

import com.example.mymoviesmvc.common.BaseActMVP;
import com.example.mymoviesmvc.data.Movie;

import java.util.List;

public interface FavouriteActMVP
{
    interface MVPView extends BaseActMVP<Presenter>
    {
        void setMovieList(List<Movie> movieList);
    }

    interface Presenter
    {

    }
}
