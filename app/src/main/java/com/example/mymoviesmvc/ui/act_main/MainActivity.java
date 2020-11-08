package com.example.mymoviesmvc.ui.act_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.mymoviesmvc.R;
import com.example.mymoviesmvc.common.Constants;
import com.example.mymoviesmvc.data.Movie;
import com.example.mymoviesmvc.networking.downloaders.MoviesDownloader;
import com.example.mymoviesmvc.networking.pojo.MoviesResponse;
import com.example.mymoviesmvc.networking.pojo.ServerMovie;
import com.example.mymoviesmvc.ui.CallBack;
import com.example.mymoviesmvc.ui.act_detail.DetailActivity;
import com.example.mymoviesmvc.ui.act_favourite.FavouriteActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.mymoviesmvc.common.Constants.BASE_POSTER_URL;
import static com.example.mymoviesmvc.common.Constants.BIG_POSTER_SIZE;
import static com.example.mymoviesmvc.common.Constants.SMALL_POSTER_SIZE;

public class MainActivity extends AppCompatActivity implements MainActMVP.Presenter
{
    private MainActMVP.MVPView mvpView;
    private List<Movie> movieList;
    private String methodOfSort;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.itemMain:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.itemFavourite:
                Intent intent1 = new Intent(this, FavouriteActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private int getColumnCount()
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = (int) (displayMetrics.widthPixels / displayMetrics.density);
        return width / 185 > 2 ? width / 185 : 2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mvpView = new MainActMVPView(getLayoutInflater());
        mvpView.registerPresenter(this);
        setContentView(mvpView.getRootView());
        downloadData(methodOfSort);

    }

    @Override
    public void onPosterClicked(int position)
    {
        Movie movie = movieList.get(position);
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("id", movie.getId());
        intent.putExtra("vote_count", movie.getVoteCount());
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("original_title", movie.getOriginalTitle());
        intent.putExtra("overview", movie.getOverview());
        intent.putExtra("poster_path", movie.getPosterPath());
        intent.putExtra("big_poster_path", movie.getBigPosterPath());
        intent.putExtra("backdrop_path", movie.getBackdropPath());
        intent.putExtra("vote_average", movie.getVoteAverage());
        intent.putExtra("release_date", movie.getReleaseDate());
        startActivity(intent);
    }

    @Override
    public void onSwitchChanged(boolean isChecked)
    {
        Log.e("SWITCHhhhh", "" + isChecked);

        if (isChecked)
        {
            methodOfSort = Constants.SORT_BY_TOP_RATED;
            mvpView.setTextViewColor(true);
            downloadData(methodOfSort);
        }
        else
        {
            methodOfSort = Constants.SORT_BY_POPULARITY;
            mvpView.setTextViewColor(false);
            downloadData(methodOfSort);
        }
    }





    private void downloadData(String methodOfSort)
    {
        MoviesDownloader.getMoviesFromNetwork(methodOfSort, new CallBack<MoviesResponse>()
        {
            @Override
            public void onSuccess(MoviesResponse response)
            {
                movieList = new ArrayList<>();
                Log.e("TEST", "ONSUCCESS");
                List<ServerMovie> serverMovies = response.getResults();
                for (int i = 0; i < serverMovies.size(); i++)
                {
                    ServerMovie serverMovie = serverMovies.get(i);
                    int id = serverMovie.getId();
                    int voteCount = serverMovie.getVoteCount();
                    String title = serverMovie.getTitle();
                    String originalTitle = serverMovie.getOriginalTitle();
                    String overview = serverMovie.getOverview();
                    String posterPath = BASE_POSTER_URL + SMALL_POSTER_SIZE + serverMovie.getPosterPath();
                    String bigPosterPath = BASE_POSTER_URL + BIG_POSTER_SIZE + serverMovie.getPosterPath();
                    String backdropPath = serverMovie.getBackdropPath();
                    double voteAverage = serverMovie.getVoteAverage();
                    String releaseDate = serverMovie.getReleaseDate();
                    Movie movie = new Movie(id, voteCount, title, originalTitle, overview, posterPath, bigPosterPath, backdropPath, voteAverage, releaseDate);
                    movieList.add(movie);
                }
                mvpView.setMovieList(movieList);
            }
            @Override
            public void onError()
            {
                Log.e("TEST", "ONERROR");
            }
        });
    }
}





//    public String getMethodOfSort()
//    {
//        return methodOfSort;
//    }
//
//    private void setMethodOfSort(boolean isTopRated)
//    {
//        if (isTopRated)
//        {
//            mvpView.setTextViewColor(true);
//        }
//        else
//        {
//            mvpView.setTextViewColor(false);
//        }
//    }

//        switchSort.setChecked(false);
//    public void onClickSetPopularity(View view)
//    {
//        setMethodOfSort(false);
//        switchSort.setChecked(false);
//    }
//
//    public void onClickSetTopRated(View view)
//    {
//        setMethodOfSort(true);
//        switchSort.setChecked(true);
//    }
//
//
//    private void setMethodOfSort(boolean isTopRated)
//    {
//        if (isTopRated)
//        {
//            textViewTopRated.setTextColor(getResources().getColor(R.color.colorAccent));
//            textViewPopularity.setTextColor(getResources().getColor(R.color.colorWhite));
//            methodOfSort = NetworkUtils.TOP_RATED;
//        } else
//        {
//            textViewTopRated.setTextColor(getResources().getColor(R.color.colorWhite));
//            textViewPopularity.setTextColor(getResources().getColor(R.color.colorAccent));
//            methodOfSort = NetworkUtils.POPULARITY;
//        }
//        downloadData(methodOfSort, page);
//    }
