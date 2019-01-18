package com.example.android.searchmovie;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailedMovieItems {

    private int id;
    private String detailedMovieTitle;
    private String detailedMovieTagline;
    private String detailedMovieRuntimeHour;
    private String detailedMovieRuntimeMinute;
    private String detailedMovieRatings;
    private String detailedMovieRatingsVote;
    private String detailedMovieLanguages;
    private String detailedMovieGenres;
    private String detailedMovieReleaseDate;
    private String detailtedMovieOverview;
    private String detailedMoviePosterPath;

    public DetailedMovieItems(JSONObject object){
        try{
            int id = object.getInt("id");
            String title = object.getString("title");
            String tagline = object.getString("tagline");
            String runtime = object.getString("runtime");
            Log.d("Runtime detailed movie:", runtime);
            String hourText;
            String minuteText;

            if(runtime != null){
                // Cari runtime hour tanpa ada decimal places
                Integer hour = ((int) Integer.parseInt(runtime) / 60);
                hourText = String.valueOf(hour);
                // Cari runtime minute dengan mendapatkan sisa dari pembagian (% operator)
                Integer minute = Integer.parseInt(runtime) % 60;
                minuteText = String.valueOf(minute);
            } else {
                hourText = String.valueOf(0);
                minuteText = String.valueOf(0);
            }

            String rating = object.getString("vote_average");
            String ratingText;
            if(rating != null)
                ratingText = rating;
            else
                ratingText = String.valueOf(0);

            String ratingVotes = object.getString("vote_count");
            String ratingVotesText;
            if(ratingVotes != null)
                ratingVotesText = ratingVotes;
            else
                ratingVotesText = String.valueOf(0);


            JSONArray languageArray = object.getJSONArray("spoken_languages");
            String languages = null;
            if(languageArray.length() > 0){
                // Iterate language array untuk mendapatkan language yang akan ditambahkan ke languages
                // fyi: languages itu adalah koleksi dari language field
                for(int i = 0; i < languageArray.length(); i++){
                    JSONObject languageObject = languageArray.getJSONObject(i);
                    String language = languageObject.getString("name");
                    if(i == 0)
                        languages = language + " ";
                    else
                        languages += language + " ";
                }
            } else {
                languages = " ";
            }

            JSONArray genreArray = object.getJSONArray("genres");
            String genres = null;

            if(genreArray.length() > 0){
                // Iterate genre array untuk mendapatkan genre yang akan ditambahkan ke genres
                // fyi: genres itu adalah koleksi dari genre field
                for(int i = 0; i< genreArray.length(); i++){
                    JSONObject genreObject = genreArray.getJSONObject(i);
                    String genre = genreObject.getString("name");
                    if(i == 0)
                        genres = genre + " ";
                    else
                        genres += genre + " ";
                }
            } else {
                genres = " ";
            }

            String releaseDate = object.getString("release_date");
            String overview = object.getString("overview");
            // Dapatkan detailed movie poster path untuk link
            String detailedPosterPath = object.getString("poster_path");
            Log.d("Detailed path: ", detailedPosterPath);

            // Set values bedasarkan variable-variable yang merepresentasikan field dari sebuah JSON
            // object
            this.id = id;
            this.detailedMovieTitle = title;
            this.detailedMovieTagline = tagline;
            this.detailedMovieRuntimeHour = hourText;
            this.detailedMovieRuntimeMinute = minuteText;
            this.detailedMovieRatings = ratingText;
            this.detailedMovieRatingsVote = ratingVotesText;
            this.detailedMovieLanguages = languages;
            this.detailedMovieGenres = genres;
            this.detailedMovieReleaseDate = releaseDate;
            this.detailtedMovieOverview = overview;
            this.detailedMoviePosterPath = detailedPosterPath;

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

    public String getDetailedMovieTitle() {
        return detailedMovieTitle;
    }

    public void setDetailedMovieTitle(String detailedMovieTitle) {
        this.detailedMovieTitle = detailedMovieTitle;
    }

    public String getDetailedMovieTagline() {
        return detailedMovieTagline;
    }

    public void setDetailedMovieTagline(String detailedMovieTagline) {
        this.detailedMovieTagline = detailedMovieTagline;
    }

    public String getDetailedMovieRuntimeHour() {
        return detailedMovieRuntimeHour;
    }

    public void setDetailedMovieRuntimeHour(String detailedMovieRuntimeHour) {
        this.detailedMovieRuntimeHour = detailedMovieRuntimeHour;
    }

    public String getDetailedMovieRuntimeMinute() {
        return detailedMovieRuntimeMinute;
    }

    public void setDetailedMovieRuntimeMinute(String detailedMovieRuntimeMinute) {
        this.detailedMovieRuntimeMinute = detailedMovieRuntimeMinute;
    }

    public String getDetailedMovieRatings() {
        return detailedMovieRatings;
    }

    public void setDetailedMovieRatings(String detailedMovieRatings) {
        this.detailedMovieRatings = detailedMovieRatings;
    }

    public String getDetailedMovieRatingsVote() {
        return detailedMovieRatingsVote;
    }

    public void setDetailedMovieRatingsVote(String detailedMovieRatingsVote) {
        this.detailedMovieRatingsVote = detailedMovieRatingsVote;
    }

    public String getDetailedMovieLanguages() {
        return detailedMovieLanguages;
    }

    public void setDetailedMovieLanguages(String detailedMovieLanguages) {
        this.detailedMovieLanguages = detailedMovieLanguages;
    }

    public String getDetailedMovieGenres() {
        return detailedMovieGenres;
    }

    public void setDetailedMovieGenres(String detailedMovieGenres) {
        this.detailedMovieGenres = detailedMovieGenres;
    }

    public String getDetailedMovieReleaseDate() {
        return detailedMovieReleaseDate;
    }

    public void setDetailedMovieReleaseDate(String detailedMovieReleaseDate) {
        this.detailedMovieReleaseDate = detailedMovieReleaseDate;
    }

    public String getDetailtedMovieOverview() {
        return detailtedMovieOverview;
    }

    public void setDetailtedMovieOverview(String detailtedMovieOverview) {
        this.detailtedMovieOverview = detailtedMovieOverview;
    }

    public String getDetailedMoviePosterPath() {
        return detailedMoviePosterPath;
    }

    public void setDetailedMoviePosterPath(String detailedMoviePosterPath) {
        this.detailedMoviePosterPath = detailedMoviePosterPath;
    }
}
