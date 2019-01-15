package com.example.android.searchmovie;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {

    ListView listView;
    MovieAdapter movieAdapter;
    // Edit Text bwt search sm Button search akan menyusul, todo: make them after loader ud bs

    // 2 Loader IDs untuk menangani 2 Loader yang berbeda
    private static final int LOADER_ID_NOW_PLAYING = 101;
    private static final int LOADER_ID_SEARCH = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        movieAdapter = new MovieAdapter(this);
        movieAdapter.notifyDataSetChanged();
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(movieAdapter);

        getLoaderManager().initLoader(LOADER_ID_NOW_PLAYING, savedInstanceState, this);

    }

    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args) {
        return new MovieAsyncTaskLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> movieItems) {
        movieAdapter.setData(movieItems);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        movieAdapter.setData(null);
    }
}
