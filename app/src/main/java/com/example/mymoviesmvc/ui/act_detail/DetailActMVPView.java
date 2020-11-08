package com.example.mymoviesmvc.ui.act_detail;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymoviesmvc.R;
import com.example.mymoviesmvc.adapters.ReviewAdapter;
import com.example.mymoviesmvc.adapters.TrailerAdapter;
import com.example.mymoviesmvc.data.Movie;
import com.example.mymoviesmvc.data.Review;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActMVPView implements DetailActMVP.MVPView
{
    private View rootView;
    private LayoutInflater inflater;
    private DetailActMVP.Presenter presenter;
    private ImageView imageViewBigPoster;
    private ImageView imageViewAddToFavourite;
    private TextView textViewTitle;
    private TextView textViewOriginalTitle;
    private TextView textViewRating;
    private TextView textViewReleaseDate;
    private TextView textViewOverview;
    private ScrollView scrollViewInfo;
    private RecyclerView recyclerViewTrailers;
    private RecyclerView recyclerViewReviews;
    private ReviewAdapter reviewAdapter;
    private TrailerAdapter trailerAdapter;

    public DetailActMVPView(LayoutInflater inflater)
    {
        this.inflater = inflater;
        rootView = inflater.inflate(R.layout.activity_detail, null, false);
        imageViewBigPoster = rootView.findViewById(R.id.imageViewBigPoster);
        imageViewAddToFavourite = rootView.findViewById(R.id.imageViewAddToFavourite);
        textViewTitle = rootView.findViewById(R.id.textViewTitle);
        textViewOriginalTitle = rootView.findViewById(R.id.textViewOriginalTitle);
        textViewRating = rootView.findViewById(R.id.textViewRating);
        textViewReleaseDate = rootView.findViewById(R.id.textViewReleaseDate);
        textViewOverview = rootView.findViewById(R.id.textViewOverview);
        scrollViewInfo = rootView.findViewById(R.id.scrollViewInfo);
        recyclerViewReviews = rootView.findViewById(R.id.recyclerViewReviews);
        recyclerViewReviews.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        reviewAdapter = new ReviewAdapter();
        recyclerViewReviews.setAdapter(reviewAdapter);
        recyclerViewTrailers = rootView.findViewById(R.id.recyclerViewTrailers);
        trailerAdapter = new TrailerAdapter();
    }

    @Override
    public View getRootView()
    {
        return rootView;
    }

    @Override
    public void registerPresenter(DetailActMVP.Presenter presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public void showMovie(Movie movie)
    {
        Picasso.get().load(movie.getBigPosterPath()).into(imageViewBigPoster);
        textViewTitle.setText(movie.getTitle());
        textViewOriginalTitle.setText(movie.getOriginalTitle());
        textViewRating.setText(Double.toString(movie.getVoteAverage()));
        textViewReleaseDate.setText(movie.getReleaseDate());
        textViewOverview.setText(movie.getOverview());
    }

    @Override
    public void setReviews(List<Review> reviews)
    {
        reviewAdapter.setReviews(reviews);
        reviewAdapter.notifyDataSetChanged();
    }
}




