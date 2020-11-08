package com.example.mymoviesmvc.common;

import android.app.Application;

import com.example.mymoviesmvc.common.dependency_injection.CompositionRoot;

public class CustomApplication extends Application
{
    private CompositionRoot mCompositionRoot;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mCompositionRoot = new CompositionRoot();
    }

    public CompositionRoot getCompositionRoot()
    {
        return mCompositionRoot;
    }
}
