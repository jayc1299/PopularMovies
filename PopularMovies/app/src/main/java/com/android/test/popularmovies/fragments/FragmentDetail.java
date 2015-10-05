package com.android.test.popularmovies.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.test.popularmovies.MovieApi;
import com.android.test.popularmovies.R;
import com.android.test.popularmovies.Result;
import com.android.test.popularmovies.activities.ActivityDetail;
import com.squareup.picasso.Picasso;

public class FragmentDetail extends Fragment{

	Result mMovie;
	MovieApi mAPi = new MovieApi();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_detail, container, false);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		//Get movie passed in from activity
		Intent intent = getActivity().getIntent();
		if(intent != null){
			mMovie = intent.getParcelableExtra(ActivityDetail.TAG_MOVIE_OBJECT);
		}

		if(mMovie != null) {
			//Title
			ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
			actionBar.setTitle(mMovie.title);

			//Text
			((TextView) getView().findViewById(R.id.fragment_detail_title)).setText(mMovie.title);
			((TextView) getView().findViewById(R.id.fragment_detail_release_date)).setText(mMovie.releaseDate);
			((TextView) getView().findViewById(R.id.fragment_detail_vote_average)).setText(String.valueOf(mMovie.voteAverage));

			//Set image
			ImageView img = (ImageView) getView().findViewById(R.id.fragment_detail_image);
			String path = mAPi.getImgUrl(getActivity(), mMovie.posterPath);
			Picasso.with(getActivity()).load(path).into(img);
		}
	}
}