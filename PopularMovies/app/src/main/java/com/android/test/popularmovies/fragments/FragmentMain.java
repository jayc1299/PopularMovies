package com.android.test.popularmovies.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.test.popularmovies.AsyncGetMoviePosters;
import com.android.test.popularmovies.PojoMovies;
import com.android.test.popularmovies.R;
import com.android.test.popularmovies.activities.ActivityDetail;
import com.android.test.popularmovies.adapters.AdapterMovies;

public class FragmentMain extends Fragment implements AsyncGetMoviePosters.IAsyncMovies {

	GridView mGridview;
	AdapterMovies mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, container, false);

		mGridview = (GridView) view.findViewById(R.id.fragment_main_gridview);

		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		AsyncGetMoviePosters async = new AsyncGetMoviePosters(this);
		async.execute(getActivity());

		mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(mAdapter != null && mAdapter.getCount() > 0) {
					Intent intent = new Intent(getActivity(), ActivityDetail.class);
					intent.putExtra(ActivityDetail.TAG_MOVIE_OBJECT, mAdapter.getItem(position));
					startActivity(intent);
				}
			}
		});
	}

	@Override
	public void onMoviesReceived(PojoMovies movies) {
		mAdapter = new AdapterMovies(getActivity(), R.layout.item_movie, movies.results);
		mGridview.setAdapter(mAdapter);
	}
}