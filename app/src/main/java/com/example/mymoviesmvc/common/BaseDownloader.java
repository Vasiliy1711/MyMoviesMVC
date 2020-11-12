package com.example.mymoviesmvc.common;

import com.example.mymoviesmvc.networking.api.ApiFactory;
import com.example.mymoviesmvc.networking.api.ApiService;
import com.example.mymoviesmvc.networking.pojo.MoviesResponse;
import com.example.mymoviesmvc.networking.pojo.ReviewsResponse;
import com.example.mymoviesmvc.networking.pojo.TrailerResponse;
import com.example.mymoviesmvc.ui.CallBack;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BaseDownloader
{
    private static ApiFactory apiFactory = ApiFactory.getInstance();
    private static ApiService apiService = apiFactory.getApiService();


    public static void getMoviesFromNetwork(CompositeDisposable compositeDisposable, String methodOfSort, final CallBack<MoviesResponse> callBack)
    {
        Disposable disposable = apiService.getMoviesFromNetwork(Constants.API_KEY
                , "ru"
                , methodOfSort
                , Constants.MIN_VOTE_COUNT_VALUE
                , "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MoviesResponse>()
                {
                    @Override
                    public void accept(MoviesResponse moviesResponse) throws Exception
                    {
                        callBack.onSuccess(moviesResponse);
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
        compositeDisposable.add(disposable);
    }

    public static void getReviewsFromNetwork(CompositeDisposable compositeDisposable, int movieId, final CallBack<ReviewsResponse> callBack)
    {
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
        compositeDisposable.add(disposable);
    }

    public static void getTrailersFromNetwork(CompositeDisposable compositeDisposable, int movieId, final CallBack<TrailerResponse> callBack)
    {
        Disposable disposable = apiService.getTrailerFromNetwork(movieId
                , Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TrailerResponse>()
                {
                    @Override
                    public void accept(TrailerResponse trailerResponse) throws Exception
                    {
                        callBack.onSuccess(trailerResponse);
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
        compositeDisposable.add(disposable);
    }

}
