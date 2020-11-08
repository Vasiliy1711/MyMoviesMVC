package com.example.mymoviesmvc.networking.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewsResponse
{

    @SerializedName("results")
    @Expose
    private List<ServerReview> results = null;


    public List<ServerReview> getResults() {
        return results;
    }

    public void setResults(List<ServerReview> results) {
        this.results = results;
    }
}
