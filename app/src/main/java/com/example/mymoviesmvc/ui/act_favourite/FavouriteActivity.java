package com.example.mymoviesmvc.ui.act_favourite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mymoviesmvc.R;

public class FavouriteActivity extends AppCompatActivity implements FavouriteActMVP.Presenter
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
    }
}