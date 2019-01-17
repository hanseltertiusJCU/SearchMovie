package com.example.android.searchmovie;

import android.app.LoaderManager;
import android.content.Loader;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<DetailedMovieItems>> {

    ImageView imageViewDetailedPosterImage;
    TextView textViewDetailedMovieTitle;
    TextView textViewDetailedMovieTagline;
    TextView textViewDetailedMovieRuntime;
    TextView textViewDetailedMovieRating;
    TextView textViewDetailedMovieLanguage;
    TextView textViewDetailedMovieGenres;
    TextView textViewDetailedMovieReleaseDate;
    TextView textViewDetailedMovieOverview;

    int detailedMovieId;
    String detailedMovieTitle;

    LinearLayout contentLayout;
    ProgressBar detailProgress;

    static final int LOADER_ID_SEARCH = 103;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Setup views bedasarkan id yang ada di layout xml
        imageViewDetailedPosterImage = findViewById(R.id.detailed_poster_image);
        textViewDetailedMovieTitle = findViewById(R.id.detailed_movie_title_text);
        textViewDetailedMovieTagline = findViewById(R.id.detailed_movie_tagline_text);
        textViewDetailedMovieRuntime = findViewById(R.id.detailed_movie_runtime_text);
        textViewDetailedMovieRating = findViewById(R.id.detailed_movie_rating_text);
        textViewDetailedMovieLanguage = findViewById(R.id.detailed_movie_languages_text);
        textViewDetailedMovieGenres = findViewById(R.id.detailed_movie_genres_text);
        textViewDetailedMovieReleaseDate = findViewById(R.id.detailed_movie_release_date_text);
        textViewDetailedMovieOverview = findViewById(R.id.detailed_movie_overview_text);

        // Get intent untuk mendapatkan id dan title dari {@link SearchActivity}
        detailedMovieId = getIntent().getIntExtra(SearchActivity.MOVIE_ID_DATA, 0);
        detailedMovieTitle = getIntent().getStringExtra(SearchActivity.MOVIE_TITLE_DATA);

        // Set layout value untuk dapat menjalankan process loading data
        contentLayout = findViewById(R.id.detailed_movie_item);
        detailProgress = findViewById(R.id.detailed_progress_bar);

        // Set title untuk DetailActivity
        setTitle(detailedMovieTitle);

        // Set visiblity of views ketika sedang dalam meretrieve data
        contentLayout.setVisibility(View.INVISIBLE);
        detailProgress.setVisibility(View.VISIBLE);

        // initLoader untuk mentrigger onCreateLoader
        getLoaderManager().initLoader(LOADER_ID_SEARCH, savedInstanceState, this);
    }

    @Override
    public Loader<ArrayList<DetailedMovieItems>> onCreateLoader(int i, Bundle bundle) {
        return new DetailedMovieAsyncTaskLoader(this, detailedMovieId);
    }

    // Kita menggunakan detailedMovieItems sebagai parameter karena parameter tsb itu dicarry dari onCreateLoader sebagai isi data
    @Override
    public void onLoadFinished(Loader<ArrayList<DetailedMovieItems>> loader, ArrayList<DetailedMovieItems> detailedMovieItems) {

        // Ketika data selesai di load, maka kita akan mendapatkan data dan menghilangkan progress bar
        // yang menandakan bahwa loadingnya sudah selesai
        contentLayout.setVisibility(View.VISIBLE);
        detailProgress.setVisibility(View.GONE);

        // Set semua data ke dalam detail activity

        // Pake get(0) karena kita itu ingin mengakses object pertama (dan satu-satunya)
        // dari ArrayList sejak kita ingin show hanya satu object.
        Picasso.get().load(detailedMovieItems.get(0).getDetailedMoviePosterUrl()).into(imageViewDetailedPosterImage);
        textViewDetailedMovieTitle.setText(detailedMovieItems.get(0).getDetailedMovieTitle());
        textViewDetailedMovieTagline.setText("\"" + detailedMovieItems.get(0).getDetailedMovieTagline() + "\"");

        // Set textview content in detailed movie runtime to contain a variety of different colors
        Spannable runtimeWord = new SpannableString("Runtime: ");
        runtimeWord.setSpan(new ForegroundColorSpan(Color.BLACK), 0, runtimeWord.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewDetailedMovieRuntime.setText(runtimeWord);
        Spannable runtimeDetailedMovieHour = new SpannableString(detailedMovieItems.get(0).getDetailedMovieRuntimeHour());
        runtimeDetailedMovieHour.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), 0, runtimeDetailedMovieHour.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewDetailedMovieRuntime.append(runtimeDetailedMovieHour);
        Spannable runtimeHours = new SpannableString(" hour(s) ");
        runtimeHours.setSpan(new ForegroundColorSpan(Color.BLACK), 0, runtimeHours.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewDetailedMovieRuntime.append(runtimeHours);
        Spannable runtimeDetailedMovieMinute = new SpannableString(detailedMovieItems.get(0).getDetailedMovieRuntimeMinute());
        runtimeDetailedMovieMinute.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), 0, runtimeDetailedMovieMinute.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewDetailedMovieRuntime.append(runtimeDetailedMovieMinute);
        Spannable runtimeMinutes = new SpannableString(" minute(s) ");
        runtimeMinutes.setSpan(new ForegroundColorSpan(Color.BLACK), 0, runtimeMinutes.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewDetailedMovieRuntime.append(runtimeMinutes);

        // Set textview content in detailed movie rating to contain a variety of different colors
        Spannable ratingWord = new SpannableString("Rating : ");
        ratingWord.setSpan(new ForegroundColorSpan(Color.BLACK), 0, ratingWord.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewDetailedMovieRating.append(ratingWord);
        Spannable ratingDetailedMovie = new SpannableString(detailedMovieItems.get(0).getDetailedMovieRatings());
        ratingDetailedMovie.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), 0, ratingDetailedMovie.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewDetailedMovieRating.append(ratingDetailedMovie);
        Spannable ratingFromWord = new SpannableString(" from ");
        ratingFromWord.setSpan(new ForegroundColorSpan(Color.BLACK), 0, ratingFromWord.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewDetailedMovieRating.append(ratingFromWord);
        Spannable ratingDetailedMovieVotes = new SpannableString(detailedMovieItems.get(0).getDetailedMovieRatingsVote());
        ratingDetailedMovieVotes.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), 0, ratingDetailedMovieVotes.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewDetailedMovieRating.append(ratingDetailedMovieVotes);
        Spannable ratingVotesWord = new SpannableString(" vote(s)");
        ratingVotesWord.setSpan(new ForegroundColorSpan(Color.BLACK), 0, ratingVotesWord.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewDetailedMovieRating.append(ratingVotesWord);

        textViewDetailedMovieLanguage.setText(detailedMovieItems.get(0).getDetailedMovieLanguages());
        textViewDetailedMovieGenres.setText(detailedMovieItems.get(0).getDetailedMovieGenres());
        textViewDetailedMovieReleaseDate.setText(detailedMovieItems.get(0).getDetailedMovieReleaseDate());
        textViewDetailedMovieOverview.setText(detailedMovieItems.get(0).getDetailtedMovieOverview());


    }

    @Override
    public void onLoaderReset(Loader<ArrayList<DetailedMovieItems>> loader) {
        // Set semua data menjadi null
        Picasso.get().load((Uri) null).into(imageViewDetailedPosterImage);
        textViewDetailedMovieTitle.setText(null);
        textViewDetailedMovieTagline.setText(null);
        textViewDetailedMovieRuntime.setText(null);
        textViewDetailedMovieLanguage.setText(null);
        textViewDetailedMovieGenres.setText(null);
        textViewDetailedMovieReleaseDate.setText(null);
        textViewDetailedMovieOverview.setText(null);
    }
}
