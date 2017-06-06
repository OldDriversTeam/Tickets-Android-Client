package com.example.olddrivers.myapplication.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

/**
 * Created by FrankLin on 2017/6/6.
 */

public class FilmItem implements Serializable {

    private String movie_name;
    private Calendar  release_time;
    private Vector<String> actors;

    public FilmItem(String movie_name, Calendar release_time, Vector<String> actors) {
        this.movie_name = movie_name;
        this.release_time = release_time;
        this.actors = actors;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public void setRelease_time(Calendar release_time) {
        this.release_time = release_time;
    }

    public void setActors(Vector<String> actors) {
        this.actors = actors;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public Calendar getRelease_time() {
        return release_time;
    }

    public Vector<String> getActors() {
        return actors;
    }

}
