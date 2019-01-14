package com.example.android.searchmovie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieItems {

    private int id;
    private String movieTitle;
    private double movieRatings;
    private Date movieReleaseDate;
    private String movieLanguage;
    private String moviePoster;

    public MovieItems(JSONObject object){
        try{
            int id = object.getInt("id");
            String title = object.getString("title");
            double rating = object.getDouble("vote_average");
            String releaseDateString = object.getString("release_date");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // Ubah String menjadi Date object untuk di display
            Date releaseDate = simpleDateFormat.parse(releaseDateString);
            String language = object.getString("original_language");
            // Ubah language menjadi upper case
            String displayed_language = language.toUpperCase();
            String posterPath = object.getString("poster_path");

            // Set values bedasarkan variable-variable yang merepresentasikan field dari sebuah JSON
            // object
            this.id = id;
            this.movieTitle = title;
            this.movieRatings = rating;
            this.movieReleaseDate = releaseDate;
            this.movieLanguage = displayed_language;
            this.moviePoster = posterPath;


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

    public double getMovieRatings() {
        return movieRatings;
    }

    public void setMovieRatings(double movieRatings) {
        this.movieRatings = movieRatings;
    }

    public Date getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(Date movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }
}
