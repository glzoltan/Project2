package com.example.project2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Movie {
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("overview")
    private String releaseDate;
    /*@SerializedName("genre_ids")
    private List<Integer> genreIds=new ArrayList<Integer>();*/
    @SerializedName("id")
    private Integer id;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("title")
    private String title;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("popularity")
    private String popularity;
    @SerializedName("video")
    private Boolean video;
    @SerializedName("vote_average")
    private String voteAverage;
    String baseImageUrl="https://image.tmdb.org/t/p/w500";

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w500"+posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Integer getId() {
        return id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public String getPopularity() {
        return popularity;
    }

    public Boolean getVideo() {
        return video;
    }

    public String getVoteAverage() {
        return voteAverage;
    }
}
