package com.example.mymoviesmvc.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymoviesmvc.R;
import com.example.mymoviesmvc.data.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>
{
    private List<Review> reviews;

    public ReviewAdapter()
    {
        reviews = new ArrayList<>();
    }

    public void setReviews(List<Review> reviews)
    {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item
                , parent, false);

        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder reviewViewHolder, int position)
    {
        Review review = reviews.get(position);
        reviewViewHolder.textViewAuthor.setText(review.getAuthor());
        reviewViewHolder.textViewContent.setText(review.getContent());
    }

    @Override
    public int getItemCount()
    {
        if (reviews == null)
        {
            return 0;
        }
        return reviews.size();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textViewAuthor;
        private TextView textViewContent;

        public ReviewViewHolder(@NonNull View itemView)
        {
            super(itemView);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewContent = itemView.findViewById(R.id.textViewContent);
        }
    }
}
