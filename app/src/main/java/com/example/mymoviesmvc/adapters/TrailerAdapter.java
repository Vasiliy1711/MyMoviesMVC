package com.example.mymoviesmvc.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymoviesmvc.R;
import com.example.mymoviesmvc.data.Trailer;

import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>
{

    private List<Trailer> trailers;

    public TrailerAdapter()
    {
        trailers = new ArrayList<>();
    }

    public void setTrailers(List<Trailer> trailers)
    {
        this.trailers = trailers;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item
                , parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder trailerViewHolder, int position)
    {
        Trailer trailer = trailers.get(position);
        trailerViewHolder.textViewNameOfVideo.setText(trailer.getName());
    }

    @Override
    public int getItemCount()
    {
        if (trailers == null)
        {
            return 0;
        }
        return trailers.size();
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageViewPlay;
        private TextView textViewNameOfVideo;

        public TrailerViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageViewPlay = itemView.findViewById(R.id.imageViewPlay);
            textViewNameOfVideo = itemView.findViewById(R.id.textViewNameOfVideo);
        }
    }
}
