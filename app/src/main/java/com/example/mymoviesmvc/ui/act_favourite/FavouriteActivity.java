package com.example.mymoviesmvc.ui.act_favourite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mymoviesmvc.R;
import com.example.mymoviesmvc.common.BaseActivity;

public class FavouriteActivity extends BaseActivity
        implements FavouriteActMVP.Presenter
{
    private FavouriteActMVP.MVPView mvpView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mvpView = new FavouriteActMVPView(getLayoutInflater());
        mvpView.registerPresenter(this);
        setContentView(mvpView.getRootView());
    }
}