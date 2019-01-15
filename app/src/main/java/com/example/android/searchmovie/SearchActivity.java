package com.example.android.searchmovie;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

    static final String EXTRAS_SEARCH = "EXTRAS_SEARCH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        movieAdapter = new MovieAdapter(this);
        movieAdapter.notifyDataSetChanged();
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(movieAdapter);

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

            // Cek ketika edit textnya itu kosong atau tidak,
            if(TextUtils.isEmpty(movie))
                return;

            // Jika isi dari edit textnya itu tidak kosong, maka kita akan merestart loader untuk
            // mengaccomodate Search
            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_SEARCH, movie);
            getLoaderManager().restartLoader(LOADER_ID_SEARCH, bundle, SearchActivity.this);
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

    }
}
