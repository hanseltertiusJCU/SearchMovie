package com.example.android.searchmovie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieItems {

    private int id;
    private String movieTitle;
    private String movieRatings;
    private String movieReleaseDate;
    private String movieLanguage;
    private String moviePosterUrl;

    public MovieItems(JSONObject object){
        try{
            int id = object.getInt("id");
            String title = object.getString("title");
            double rating = object.getDouble("vote_average");
            // Ubah double menjadi String untuk di display ke View
            String ratingText = String.valueOf(rating);
            String releaseDateString = object.getString("release_date");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // Ubah String menjadi Date object
            Date releaseDate = simpleDateFormat.parse(releaseDateString);
            // Ubah Date menjadi String untuk di display ke View
            String releaseDateText = simpleDateFormat.format(releaseDate);
            String language = object.getString("original_language");
            // Ubah language menjadi upper case
            String displayed_language = language.toUpperCase();
            // Dapatkan poster path untuk link
            String posterPath = object.getString("poster_path");
            String posterUrl = null;
            if(posterPath != null){
                // Link untuk poster bedasarkan poster path di atas
                posterUrl = "https://image.tmdb.org/t/p/w185" + posterPath;
            }

            // Set values bedasarkan variable-variable yang merepresentasikan field dari sebuah JSON
            // object
            this.id = id;
            this.movieTitle = title;
            this.movieRatings = ratingText;
            this.movieReleaseDate = releaseDateText;
            this.movieLanguage = displayed_language;
            this.moviePosterUrl = posterUrl;


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

    public String getMoviePosterUrl() {
        return moviePosterUrl;
    }

    public void setMoviePosterUrl(String moviePosterUrl) {
        this.moviePosterUrl = moviePosterUrl;
    }
}
