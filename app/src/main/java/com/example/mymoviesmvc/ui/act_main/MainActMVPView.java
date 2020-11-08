package com.example.mymoviesmvc.ui.act_main;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymoviesmvc.R;
import com.example.mymoviesmvc.adapters.MovieAdapter;
import com.example.mymoviesmvc.data.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActMVPView implements MainActMVP.MVPView
{
    private View rootView;
    private MainActMVP.Presenter presenter;
    private LayoutInflater inflater;
    private RecyclerView recyclerViewPosters;
    private MovieAdapter adapter;
    private Switch switchSort;
    private TextView textViewTopRated;
    private TextView textViewPopularity;
    private ProgressBar progressBarLoading;




    public MainActMVPView(LayoutInflater inflater)
    {
        this.inflater = inflater;
        rootView = inflater.inflate(R.layout.activity_main, null, false);
        switchSort = rootView.findViewById(R.id.switchSort);
        textViewTopRated = rootView.findViewById(R.id.textViewTopRated);
        textViewPopularity = rootView.findViewById(R.id.textViewPopularity);
        progressBarLoading = rootView.findViewById(R.id.progressBarLoading);
        recyclerViewPosters = rootView.findViewById(R.id.recyclerViewPosters);
        recyclerViewPosters.setLayoutManager(new GridLayoutManager(rootView.getContext(), 2));
        adapter = new MovieAdapter();
        recyclerViewPosters.setAdapter(adapter);

        textViewPopularity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switchSort.setChecked(false);
            }
        });

        textViewTopRated.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switchSort.setChecked(true);
            }
        });

        adapter.setOnPosterClickListener(new MovieAdapter.OnPosterClickListener()
        {
            @Override
            public void onPosterClick(int position)
            {
                presenter.onPosterClicked(position);
            }
        });

        switchSort.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                Log.e("SWITCH", "" + isChecked);
                presenter.onSwitchChanged(isChecked);
            }
        });
}



    @Override
    public View getRootView()
    {
        return rootView;
    }

    @Override
    public void registerPresenter(MainActMVP.Presenter presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public void setMovieList(List<Movie> movieList)
    {
        adapter.setMovieList(movieList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setTextViewColor(boolean isTopRated)
    {
        if (isTopRated)
        {
            textViewTopRated.setTextColor(rootView.getContext().getColor(R.color.colorAccent));
            textViewPopularity.setTextColor(rootView.getContext().getColor(R.color.colorWhite));
        }
        else
        {
            textViewTopRated.setTextColor(rootView.getContext().getColor(R.color.colorWhite));
            textViewPopularity.setTextColor(rootView.getContext().getColor(R.color.colorAccent));
        }
    }


}
