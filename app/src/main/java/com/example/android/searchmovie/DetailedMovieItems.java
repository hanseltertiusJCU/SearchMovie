package com.example.android.searchmovie;

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
    private String detailedMovieLanguages;
    private String detailedMovieGenres;
    private String detailedMovieReleaseDate;
    private String detailtedMovieOverview;
    private String detailedMoviePosterUrl;

    public DetailedMovieItems(JSONObject object){
        try{
            int id = object.getInt("id");
            String title = object.getString("title");
            String tagline = object.getString("tagline");
            int runtime = object.getInt("runtime");
            // Cari runtime hour tanpa ada decimal places
            int hour = ((int) runtime / 60);
            String hourText = String.valueOf(hour);
            // Cari runtime minute dengan mendapatkan sisa dari pembagian (% operator)
            int minute = runtime % 60;
            String minuteText = String.valueOf(minute);
            double rating = object.getDouble("vote_average");
            String ratingText = String.valueOf(rating);
            JSONArray languageArray = object.getJSONArray("spoken_languages");
            String languages = null;
            // Iterate language array untuk mendapatkan language yang akan ditambahkan ke languages
            // fyi: languages itu adalah koleksi dari language field
            for(int i = 0; i < languageArray.length(); i++){
                JSONObject languageObject = languageArray.getJSONObject(i);
                String language = languageObject.getString("name");
                languages += language + " ";
            }
            JSONArray genreArray = object.getJSONArray("genres");
            String genres = null;
            // Iterate genre array untuk mendapatkan genre yang akan ditambahkan ke genres
            // fyi: genres itu adalah koleksi dari genre field
            for(int i = 0; i< genreArray.length(); i++){
                JSONObject genreObject = genreArray.getJSONObject(i);
                String genre = genreObject.getString("name");
                genres += genre + " ";
            }
            String releaseDateString = object.getString("release_date");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date releaseDate = simpleDateFormat.parse(releaseDateString);
            String releaseDateText = simpleDateFormat.format(releaseDate);
            String overview = object.getString("overview");

            // Set values bedasarkan variable-variable yang merepresentasikan field dari sebuah JSON
            // object
            this.id = id;
            this.detailedMovieTitle = title;
            this.detailedMovieTagline = tagline;
            this.detailedMovieRuntimeHour = hourText;
            this.detailedMovieRuntimeMinute = minuteText;
            this.detailedMovieRatings = ratingText;
            this.detailedMovieLanguages = languages;
            this.detailedMovieGenres = genres;
            this.detailedMovieReleaseDate = releaseDateText;
            this.detailtedMovieOverview = overview;

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

    public String getDetailedMoviePosterUrl() {
        return detailedMoviePosterUrl;
    }

    public void setDetailedMoviePosterUrl(String detailedMoviePosterUrl) {
        this.detailedMoviePosterUrl = detailedMoviePosterUrl;
    }
}
