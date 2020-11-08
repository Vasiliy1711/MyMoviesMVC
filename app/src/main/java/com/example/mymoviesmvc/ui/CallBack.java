package com.example.mymoviesmvc.ui;

public interface CallBack<T>
{
    void onSuccess(T response);
    void onError();
}
