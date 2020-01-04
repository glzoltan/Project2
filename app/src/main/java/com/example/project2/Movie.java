package com.example.project2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Movie {
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("overview")
    private String overview;
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

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
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

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Movie(String posterPath, boolean adult, String releaseDate, String overview, Integer id, String originalLanguage, String title, String originalTitle, String popularity, Boolean video, String voteAverage) {
        this.posterPath = posterPath;
        this.adult = adult;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.id = id;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.originalTitle = originalTitle;
        this.popularity = popularity;
        this.video = video;
        this.voteAverage = voteAverage;
    }

    public Movie() {
    }
}
