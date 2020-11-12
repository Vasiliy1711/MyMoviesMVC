package com.example.mymoviesmvc.ui.act_main;

import android.view.View;

import com.example.mymoviesmvc.common.BaseActMVP;
import com.example.mymoviesmvc.data.Movie;
import com.example.mymoviesmvc.ui.CallBack;

import java.util.List;

public interface MainActMVP
{
    interface MVPView extends BaseActMVP<Presenter>    //  generic
    {

        void setMovieList(List<Movie> movieList);
        void setTextViewColor(boolean isTopRated);
    }

    interface Presenter
    {
        void onPosterClicked(int position);
        void onSwitchChanged(boolean isChecked);
    }
}
