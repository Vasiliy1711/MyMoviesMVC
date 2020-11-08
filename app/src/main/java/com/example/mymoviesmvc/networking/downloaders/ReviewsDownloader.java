package com.example.mymoviesmvc.networking.downloaders;

import com.example.mymoviesmvc.common.Constants;
import com.example.mymoviesmvc.data.Movie;
import com.example.mymoviesmvc.data.Review;
import com.example.mymoviesmvc.networking.api.ApiFactory;
import com.example.mymoviesmvc.networking.api.ApiService;
import com.example.mymoviesmvc.networking.pojo.ReviewsResponse;
import com.example.mymoviesmvc.ui.CallBack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ReviewsDownloader
{


    public static void getReviewsFromNetwork(int movieId, final CallBack<ReviewsResponse> callBack)
    {

        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        Disposable disposable = apiService.getReviewFromNetwork(movieId
                , Constants.API_KEY
                , "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReviewsResponse>()
                {
                    @Override
                    public void accept(ReviewsResponse response) throws Exception
                    {
                        callBack.onSuccess(response);
                    }
                }, new Consumer<Throwable>()
                {
                    @Override
                    public void accept(Throwable throwable) throws Exception
                    {
                        throwable.printStackTrace();
                        callBack.onError();
                    }
                });
    }
}
