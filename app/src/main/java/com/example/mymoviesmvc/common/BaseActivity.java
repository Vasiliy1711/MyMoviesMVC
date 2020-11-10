package com.example.mymoviesmvc.common;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseActivity extends AppCompatActivity
{
    protected CompositeDisposable compositeDisposable;

    public BaseActivity()
    {
        compositeDisposable = new CompositeDisposable();
    }

}
