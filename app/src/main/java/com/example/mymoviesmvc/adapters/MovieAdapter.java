package com.example.mymoviesmvc.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymoviesmvc.R;
import com.example.mymoviesmvc.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
{
    private List<Movie> movieList;
    private OnPosterClickListener onPosterClickListener;
    private OnReachEndListener onReachEndListener;


    public MovieAdapter()
    {
        movieList = new ArrayList<>();
    }

    public List<Movie> getMovieList()
    {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList)
    {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public interface OnPosterClickListener
    {
        void onPosterClick(int position);
    }

    public void setOnPosterClickListener(OnPosterClickListener onPosterClickListener)
    {
        this.onPosterClickListener = onPosterClickListener;
    }

    public interface OnReachEndListener
    {
        void onReachEnd();
    }

    public void setOnReachEndListener(OnReachEndListener onReachEndListener)
    {
        this.onReachEndListener = onReachEndListener;
    }

    public void clear()
    {
        this.movieList.clear();
        notifyDataSetChanged();
    }

    public void addMovies(List<Movie> movieList)
    {
        this.movieList.addAll(movieList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position)
    {
        Movie movie = movieList.get(position);
        Picasso.get().load(movie.getPosterPath()).into(holder.imageViewSmallPoster);
    }

    @Override
    public int getItemCount()
    {
        if (movieList == null )
        {
            return 0;
        }
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageViewSmallPoster;

        public MovieViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageViewSmallPoster = itemView.findViewById(R.id.imageViewSmallPoster);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    onPosterClickListener.onPosterClick(getAdapterPosition());
                }
            });
        }
    }
}
