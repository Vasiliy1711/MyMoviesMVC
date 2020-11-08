package com.example.mymoviesmvc.ui.act_favourite;

import android.view.View;

public class FavouriteActMVPView implements FavouriteActMVP.MVPView
{
    private View rootView;
    private FavouriteActMVP.Presenter presenter;

    @Override
    public View getRootView()
    {
        return rootView;
    }

    @Override
    public void registerPresenter(FavouriteActMVP.Presenter presenter)
    {
        this.presenter = presenter;
    }
}
