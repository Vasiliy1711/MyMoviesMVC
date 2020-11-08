package com.example.mymoviesmvc.networking.api;

import com.example.mymoviesmvc.common.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory
{
    private static ApiFactory apiFactory;
    private static Retrofit retrofit;

    private ApiFactory()
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();
    }

    public static ApiFactory getInstance()
    {
        if (apiFactory == null)
        {
            apiFactory = new ApiFactory();
        }
        return apiFactory;
    }

    public ApiService getApiService()
    {
        return retrofit.create(ApiService.class);
    }
}
