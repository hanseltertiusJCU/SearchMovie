package com.example.android.searchmovie;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class DetailedMovieItems {

    private int id;
    private String detailedMovieTitle;
    private String detailedMovieTagline;
    private String detailedMovieStatus;
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
            String status = object.getString("status");
            String rating = object.getString("vote_average");
            String ratingVotes = object.getString("vote_count");
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
                languages = "Language Unknown";
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
                genres = "Genre Unknown";
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
            this.detailedMovieStatus = status;
            this.detailedMovieRatings = rating;
            this.detailedMovieRatingsVote = ratingVotes;
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
        if(detailedMovieTagline != null && !detailedMovieTagline.isEmpty()){
            return detailedMovieTagline;
        } else {
            return "Tagline Unknown";
        }

    }

    public void setDetailedMovieTagline(String detailedMovieTagline) {
        this.detailedMovieTagline = detailedMovieTagline;
    }

    public String getDetailedMovieStatus() {
        return detailedMovieStatus;
    }

    public void setDetailedMovieStatus(String detailedMovieStatus) {
        this.detailedMovieStatus = detailedMovieStatus;
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
        if(detailedMovieReleaseDate != null && !detailedMovieReleaseDate.isEmpty()) {
            return detailedMovieReleaseDate;
        } else {
            return "yyyy-MM-dd";
        }
    }

    public void setDetailedMovieReleaseDate(String detailedMovieReleaseDate) {
        this.detailedMovieReleaseDate = detailedMovieReleaseDate;
    }

    public String getDetailtedMovieOverview() {
        if(detailtedMovieOverview != null && !detailtedMovieOverview.isEmpty()){
            return detailtedMovieOverview;
        } else {
            return "Overview Unknown";
        }
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
