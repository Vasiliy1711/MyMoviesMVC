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
import com.example.mymoviesmvc.data.FavouriteMovie;
import com.example.mymoviesmvc.data.Movie;
import com.example.mymoviesmvc.data.Review;
import com.example.mymoviesmvc.networking.downloaders.ReviewsDownloader;
import com.example.mymoviesmvc.networking.pojo.ReviewsResponse;
import com.example.mymoviesmvc.networking.pojo.ServerReview;
import com.example.mymoviesmvc.ui.CallBack;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements DetailActMVP.Presenter
{
    private DetailActMVP.MVPView mvpView;
    private int id;
    private Movie movie;
    private List<Review> reviews;
    private FavouriteMovie favouriteMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mvpView = new DetailActMVPView(getLayoutInflater());
        mvpView.registerPresenter(this);
        setContentView(mvpView.getRootView());
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("id"))
        {
            Log.e("DETAIL", "VVVVVVVV");
            id = intent.getIntExtra("id", -1);
            int voteCount = intent.getIntExtra("vote_count", -1);
            String title = intent.getStringExtra("title");
            String originalTitle = intent.getStringExtra("original_title");
            String overview = intent.getStringExtra("overview");
            String posterPath = intent.getStringExtra("poster_path");
            String bigPosterPath = intent.getStringExtra("big_poster_path");
            String backdropPath = intent.getStringExtra("backdrop_path");
            Double voteAverage = intent.getDoubleExtra("vote_average", -1);
            String releaseDate = intent.getStringExtra("release_date");
            movie = new Movie(id, voteCount, title, originalTitle, overview, bigPosterPath, posterPath, backdropPath, voteAverage, releaseDate);
            mvpView.showMovie(movie);

        }else
        {
            finish();
        }

        ReviewsDownloader.getReviewsFromNetwork(movie.getId(), new CallBack<ReviewsResponse>()
        {
            @Override
            public void onSuccess(ReviewsResponse response)
            {
                Log.e("DETAIL", "ONSUCCESS");
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
                Log.e("DETAIL", "ONERROR");
            }
        });
    }
}

