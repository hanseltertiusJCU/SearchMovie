package com.example.android.searchmovie;

import android.app.LoaderManager;
import android.content.Loader;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
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

        // Set title untuk DetailActivity
        setTitle(detailedMovieTitle);

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

        // Set semua data ke dalam detail activity

        // Pake get(0) karena kita itu ingin mengakses object pertama (dan satu-satunya)
        // dari ArrayList sejak kita ingin show hanya satu object.
        Picasso.get().load(detailedMovieItems.get(0).getDetailedMoviePosterUrl()).into(imageViewDetailedPosterImage);
        textViewDetailedMovieTitle.setText(detailedMovieItems.get(0).getDetailedMovieTitle());
        textViewDetailedMovieTagline.setText("\"" + detailedMovieItems.get(0).getDetailedMovieTagline() + "\"");
        textViewDetailedMovieRuntime.setText("Runtime: " + detailedMovieItems.get(0).getDetailedMovieRuntimeHour() +
                " hour(s) " + detailedMovieItems.get(0).getDetailedMovieRuntimeMinute() + " minute(s)");
        textViewDetailedMovieRating.setText("Rating : " + detailedMovieItems.get(0).getDetailedMovieRatings() +
                " from " + detailedMovieItems.get(0).getDetailedMovieRatingsVote() + " vote(s)");
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
