package com.example.android.searchmovie;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>>, AdapterView.OnItemClickListener {

    ListView listView;
    MovieAdapter movieAdapter;

    EditText searchEditText;
    Button searchButton;

    // 2 Loader IDs untuk menangani 2 Loader yang berbeda
    static final int LOADER_ID_NOW_PLAYING = 101;
    static final int LOADER_ID_SEARCH = 102;

    // Key untuk meretrieve search
    static final String EXTRAS_SEARCH = "EXTRAS_SEARCH";

    // Key untuk membawa data ke intent
    static final String MOVIE_ID_DATA = "MOVIE_ID_DATA";
    static final String MOVIE_TITLE_DATA = "MOVIE_TITLE_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        movieAdapter = new MovieAdapter(this);
        movieAdapter.notifyDataSetChanged();
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(movieAdapter);
        // Enable on item click listener untuk dapat call onItemClick dari setiap list item
        listView.setOnItemClickListener(this);

        searchEditText = (EditText) findViewById(R.id.edit_movie_search);
        searchButton = (Button) findViewById(R.id.button_search);

        searchButton.setOnClickListener(myListener);

        getLoaderManager().initLoader(LOADER_ID_NOW_PLAYING, savedInstanceState, this);

    }

    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args) {
        MovieAsyncTaskLoader movieLoader = null;
        if(id == LOADER_ID_NOW_PLAYING)
            movieLoader =  new MovieAsyncTaskLoader(this);

        if(id == LOADER_ID_SEARCH){

            String movieResult = "";
            if(args != null){
                movieResult = args.getString(EXTRAS_SEARCH);
            }

            movieLoader = new MovieAsyncTaskLoader(this, movieResult);
        }
        return movieLoader;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> movieItems) {
        movieAdapter.setData(movieItems);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        movieAdapter.setData(null);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String movie = searchEditText.getText().toString();

            // Cek ketika edit textnya itu kosong atau tidak, ketika iya maka program tsb tidak
            // ngapa-ngapain dengan return nothing
            if(TextUtils.isEmpty(movie))
                return;

            // Jika isi dari edit textnya itu tidak kosong, maka kita akan merestart loader untuk
            // mengaccomodate Search
            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_SEARCH, movie);
            // Restart loader karena kita sudah membuat loader di onCreate
            getLoaderManager().restartLoader(LOADER_ID_SEARCH, bundle, SearchActivity.this);
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        // Dapatkan id dan title bedasarkan ListView item
        int movieIdItem = movieAdapter.getmMovieData().get(position).getId();
        String movieTitleItem = movieAdapter.getmMovieData().get(position).getMovieTitle();
        Intent intentWithMovieIdData = new Intent(SearchActivity.this, DetailActivity.class);
        // Bawa data untuk disampaikan ke {@link DetailActivity}
        intentWithMovieIdData.putExtra(MOVIE_ID_DATA, movieIdItem);
        intentWithMovieIdData.putExtra(MOVIE_TITLE_DATA, movieTitleItem);
        // Start activity tujuan bedasarkan intent object
        startActivity(intentWithMovieIdData);
    }
}
