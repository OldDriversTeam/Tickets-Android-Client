package com.example.olddrivers.myapplication.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Movie implements Serializable {

    private String id;
    private String name;
    private String releaseDate;
    private String storyLine;
    private String detai;
    private String poster;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStoryLine() {
        return storyLine;
    }

    public void setStoryLine(String storyLine) {
        this.storyLine = storyLine;
    }

    public String getDetai() {
        return detai;
    }

    public void setDetai(String detai) {
        this.detai = detai;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public float getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(float avgScore) {
        this.avgScore = avgScore;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    private float avgScore;
    private boolean isShow;
    private String movieType;

    public Movie(String id, String name, float avgScore, String poster) {
        this.id = id;
        this.name = name;
        this.avgScore = avgScore;
        this.poster = poster;
    }


}
