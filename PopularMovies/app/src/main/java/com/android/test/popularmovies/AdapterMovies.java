package com.android.test.popularmovies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterMovies extends ArrayAdapter<PojoMovies.Result>{

	LayoutInflater mInflater;
	List<PojoMovies.Result> mMovies;
	Context mContext;
	MovieApi mApi;

	public AdapterMovies(Context context, int resource, List<PojoMovies.Result> movies) {
		super(context, resource, movies);
		mInflater = LayoutInflater.from(context);
		mMovies = movies;
		mContext = context;
		mApi = new MovieApi();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = mInflater.inflate(R.layout.item_movie, null, false);

		String path = mApi.getImgUrl(mContext, mMovies.get(position).backdropPath);

		Log.d("AdapterMovies", path);

		Picasso.with(mContext).load(path).into((ImageView) convertView.findViewById(R.id.item_movie_image));

		return convertView;
	}
}
