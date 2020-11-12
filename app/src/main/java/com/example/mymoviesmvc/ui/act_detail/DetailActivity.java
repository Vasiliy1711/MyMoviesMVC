package com.example.mymoviesmvc.ui.act_detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.mymoviesmvc.R;
import com.example.mymoviesmvc.adapters.ReviewAdapter;
import com.example.mymoviesmvc.adapters.TrailerAdapter;
import com.example.mymoviesmvc.common.BaseActivity;
import com.example.mymoviesmvc.common.BaseDownloader;
import com.example.mymoviesmvc.data.FavouriteMovie;
import com.example.mymoviesmvc.data.Movie;
import com.example.mymoviesmvc.data.Review;
import com.example.mymoviesmvc.data.Trailer;


import com.example.mymoviesmvc.networking.pojo.ReviewsResponse;
import com.example.mymoviesmvc.networking.pojo.ServerReview;
import com.example.mymoviesmvc.networking.pojo.ServerTrailer;
import com.example.mymoviesmvc.networking.pojo.TrailerResponse;
import com.example.mymoviesmvc.ui.CallBack;
import com.example.mymoviesmvc.ui.act_favourite.FavouriteActivity;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends BaseActivity implements DetailActMVP.Presenter
{
    private DetailActMVP.MVPView mvpView;
    private int id;
    private Movie movie;
    private List<Review> reviews;
    private List<Trailer> trailers;
    private FavouriteMovie favouriteMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mvpView = new DetailActMVPView(getLayoutInflater());
        mvpView.registerPresenter(this);
        setContentView(mvpView.getRootView());
        Intent intent = getIntent();
        if (intent != null )
        {
            Log.e("DETAIL", "VVVVVVVV");
            movie = (Movie) intent.getSerializableExtra("movie");
            mvpView.showMovie(movie);

        }else
        {
            finish();
        }

        BaseDownloader.getReviewsFromNetwork(compositeDisposable, movie.getId(), new CallBack<ReviewsResponse>()
        {
            @Override
            public void onSuccess(ReviewsResponse response)
            {
                Log.e("REVIEWS", "ONSUCCESS");
                reviews = new ArrayList<>();
                List<ServerReview> serverReviews = response.getResults();
                for (int i = 0; i < serverReviews.size(); i++)
                {
                    ServerReview serverReview = serverReviews.get(i);
                    String author = serverReview.getAuthor();
                    String content = serverReview.getContent();
                    Review review = new Review(author, content);
                    reviews.add(review);
                }
                mvpView.setReviews(reviews);
            }

            @Override
            public void onError()
            {
                Log.e("REVIEWS", "ONERROR");
            }
        });


        BaseDownloader.getTrailersFromNetwork(compositeDisposable, movie.getId(), new CallBack<TrailerResponse>()
        {
            @Override
            public void onSuccess(TrailerResponse response)
            {
                Log.e("TRAILERS", "ONSUCCESS");
                trailers = new ArrayList<>();
                List<ServerTrailer> serverTrailers = response.getResults();
                for (int i = 0; i < serverTrailers.size(); i++)
                {
                    ServerTrailer serverTrailer = serverTrailers.get(i);
                    String key = serverTrailer.getKey();
                    String name = serverTrailer.getName();
                    Trailer trailer = new Trailer(key, name);
                    trailers.add(trailer);
                }
                mvpView.setTrailers(trailers);
            }

            @Override
            public void onError()
            {
                Log.e("TRAILERS", "ONERROR");
            }
        });
    }

    @Override
    public void addToFavourite(Movie movie)
    {
        Intent addToFavouriteIntent = new Intent(DetailActivity.this, FavouriteActivity.class);
        addToFavouriteIntent.putExtra("id", movie.getId());
        addToFavouriteIntent.putExtra("vote_count", movie.getVoteCount());
        addToFavouriteIntent.putExtra("title", movie.getTitle());
        addToFavouriteIntent.putExtra("original_title", movie.getOriginalTitle());
        addToFavouriteIntent.putExtra("overview", movie.getOverview());
        addToFavouriteIntent.putExtra("poster_path", movie.getPosterPath());
        addToFavouriteIntent.putExtra("big_poster_path", movie.getBigPosterPath());
        addToFavouriteIntent.putExtra("backdrop_path", movie.getBackdropPath());
        addToFavouriteIntent.putExtra("vote_average", movie.getVoteAverage());
        addToFavouriteIntent.putExtra("release_date", movie.getReleaseDate());
        startActivity(addToFavouriteIntent);
    }
}

