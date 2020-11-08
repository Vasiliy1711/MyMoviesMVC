package com.example.mymoviesmvc.ui.act_favourite;

import android.view.View;

public interface FavouriteActMVP
{
    interface MVPView
    {
        View getRootView();
        void registerPresenter(Presenter presenter);
    }

    interface Presenter
    {

    }
}
