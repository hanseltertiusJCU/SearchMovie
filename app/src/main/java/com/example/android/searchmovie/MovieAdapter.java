package com.example.android.searchmovie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private ArrayList<MovieItems> mMovieData = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private Context context;

    public ArrayList<MovieItems> getmMovieData() {
        return mMovieData;
    }

    public MovieAdapter(Context context){
        this.context = context;
        mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItems> mData) {
        this.mMovieData = mData;
        // Method tersebut berguna untuk memanggil adapter bahwa ada data yg bru, sehingga data tsb
        // dpt ditampilkan pada ListView yg berisi adapter yg berkaitan dengan ListView
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mMovieData == null)
            return 0;
        // Kalo ada item(s) di ArrayList, return brp item(s) yang ada
        return mMovieData.size();
    }

    @Override
    public MovieItems getItem(int position) {
        // Return sebuah item dari ArrayList bedasarkan index posisi dari sebuah ArrayList<>
        return mMovieData.get(position);
    }


    @Override
    public long getItemId(int position) {
        // Return position dari sebuah item di ListView
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            // Kita set root menjadi null karena kita ingin mengatur list item, bukan Activity
            convertView = mLayoutInflater.inflate(R.layout.movie_items, null);
            viewHolder.imageViewMoviePoster = (ImageView) convertView.findViewById(R.id.poster_image);
            viewHolder.textViewMovieTitle = (TextView) convertView.findViewById(R.id.movie_title_text);
            viewHolder.textViewMovieRatings = (TextView) convertView.findViewById(R.id.movie_ratings_text);
            viewHolder.textViewMovieReleaseDate = (TextView) convertView.findViewById(R.id.movie_release_date_text);
            viewHolder.textViewMovieOriginalLanguage = (TextView) convertView.findViewById(R.id.movie_language_text);
            // panggil method setTag untuk membuat memori dari sebuah view
            convertView.setTag(viewHolder);
        } else {
            // panggil method getTag untuk mendapat memori dari sebuah view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.get().load(mMovieData.get(position).getMoviePosterUrl()).into(viewHolder.imageViewMoviePoster);
        viewHolder.textViewMovieTitle.setText(mMovieData.get(position).getMovieTitle());
        viewHolder.textViewMovieRatings.setText(mMovieData.get(position).getMovieRatings());
        viewHolder.textViewMovieReleaseDate.setText(mMovieData.get(position).getMovieReleaseDate());
        viewHolder.textViewMovieOriginalLanguage.setText(mMovieData.get(position).getMovieLanguage());
        return convertView;
    }

    // Kelas ini berguna untuk menampung view yang ada tanpa mendeclare view di sebuah Adapter
    private static class ViewHolder{
        ImageView imageViewMoviePoster;
        TextView textViewMovieTitle;
        TextView textViewMovieRatings;
        TextView textViewMovieReleaseDate;
        TextView textViewMovieOriginalLanguage;
    }

}
