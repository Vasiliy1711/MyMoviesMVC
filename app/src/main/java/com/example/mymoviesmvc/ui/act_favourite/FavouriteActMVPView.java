package com.example.mymoviesmvc.ui.act_favourite;

import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymoviesmvc.R;
import com.example.mymoviesmvc.adapters.MovieAdapter;
import com.example.mymoviesmvc.data.Movie;

import java.util.List;

public class FavouriteActMVPView implements FavouriteActMVP.MVPView
{
    private View rootView;
    private FavouriteActMVP.Presenter presenter;
    private LayoutInflater inflater;
    private RecyclerView recyclerViewFavouriteMovies;
    private MovieAdapter adapter;

    public FavouriteActMVPView(LayoutInflater inflater)
    {
        this.inflater = inflater;
        rootView = inflater.inflate(R.layout.activity_favourite, null, false);
        recyclerViewFavouriteMovies = rootView.findViewById(R.id.recyclerViewFavouriteMovies);
        recyclerViewFavouriteMovies.setLayoutManager(new GridLayoutManager(rootView.getContext(), 2));
        adapter = new MovieAdapter();
        recyclerViewFavouriteMovies.setAdapter(adapter);

    }

    @Override
    public View getRootView()
    {
        return rootView;
    }

    @Override
    public void registerPresenter(FavouriteActMVP.Presenter presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public void setMovieList(List<Movie> movieList)
    {
        adapter.setMovieList(movieList);
        adapter.notifyDataSetChanged();
    }
}
