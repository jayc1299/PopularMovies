package com.android.test.popularmovies.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.android.test.popularmovies.R;
import com.android.test.popularmovies.fragments.FragmentDetail;

public class ActivityDetail extends AppCompatActivity {

	public static String TAG_MOVIE_OBJECT = "tag_movie_obect";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		//FragmentDetail frag = new FragmentDetail();
		//Bundle args = new Bundle();
		//args.putParcelable(TAG_MOVIE_OBJECT, getIntent().getParcelableExtra(TAG_MOVIE_OBJECT));
		//frag.setArguments(args);
		ft.replace(R.id.activity_main_container, new FragmentDetail(), FragmentDetail.class.getSimpleName());
		ft.commit();
	}
}