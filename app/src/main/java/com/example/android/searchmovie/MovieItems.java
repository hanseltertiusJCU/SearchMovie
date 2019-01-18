package com.example.android.searchmovie;

import android.util.Log;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieItems {

    private int id;
    private String movieTitle;
    private String movieRatings;
    private String movieReleaseDate;
    private String movieLanguage;
    private String moviePosterPath;

    public MovieItems(JSONObject object){
        try{
            int id = object.getInt("id");
            String title = object.getString("title");
            String rating = object.getString("vote_average");
            String releaseDate = object.getString("release_date");
            String language = object.getString("original_language");
            // Ubah language menjadi upper case
            String displayed_language = language.toUpperCase();
            // Dapatkan poster path untuk link
            String posterPath = object.getString("poster_path");
            Log.d("poster path movie", posterPath);

            // Set values bedasarkan variable-variable yang merepresentasikan field dari sebuah JSON
            // object
            this.id = id;
            this.movieTitle = title;
            this.movieRatings = rating;
            this.movieReleaseDate = releaseDate;
            this.movieLanguage = displayed_language;
            this.moviePosterPath = posterPath;


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieRatings() {
        return movieRatings;
    }

    public void setMovieRatings(String movieRatings) {
        this.movieRatings = movieRatings;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String getMoviePosterPath() {
        return moviePosterPath;
    }

    public void setMoviePosterPath(String moviePosterPath) {
        this.moviePosterPath = moviePosterPath;
    }
}
