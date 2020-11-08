package com.example.mymoviesmvc.networking.downloaders;

import com.example.mymoviesmvc.common.Constants;
import com.example.mymoviesmvc.data.Movie;
import com.example.mymoviesmvc.networking.api.ApiFactory;
import com.example.mymoviesmvc.networking.api.ApiService;
import com.example.mymoviesmvc.networking.pojo.MoviesResponse;
import com.example.mymoviesmvc.networking.pojo.ServerMovie;
import com.example.mymoviesmvc.ui.CallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.mymoviesmvc.common.Constants.BASE_POSTER_URL;
import static com.example.mymoviesmvc.common.Constants.BIG_POSTER_SIZE;
import static com.example.mymoviesmvc.common.Constants.KEY_BACKDROP_PATH;
import static com.example.mymoviesmvc.common.Constants.KEY_ID;
import static com.example.mymoviesmvc.common.Constants.KEY_ORIGINAL_TITLE;
import static com.example.mymoviesmvc.common.Constants.KEY_OVERVIEW;
import static com.example.mymoviesmvc.common.Constants.KEY_POSTER_PATH;
import static com.example.mymoviesmvc.common.Constants.KEY_RELEASE_DATE;
import static com.example.mymoviesmvc.common.Constants.KEY_RESULTS;
import static com.example.mymoviesmvc.common.Constants.KEY_TITLE;
import static com.example.mymoviesmvc.common.Constants.KEY_VOTE_AVERAGE;
import static com.example.mymoviesmvc.common.Constants.KEY_VOTE_COUNT;
import static com.example.mymoviesmvc.common.Constants.SMALL_POSTER_SIZE;

public class MoviesDownloader
{
    public static void getMoviesFromNetwork(String methodOfSort, final CallBack<MoviesResponse> callBack)
    {


        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
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
    }
}


