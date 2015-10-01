package com.android.test.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

public class ActivityMain extends AppCompatActivity implements AsyncGetMoviePosters.IAsyncMovies {

	private String CLASS_TAG = "ActivityMain";
	GridView mGridview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mGridview = (GridView) findViewById(R.id.activity_main_gridview);

		findViewById(R.id.activity_main_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AsyncGetMoviePosters async = new AsyncGetMoviePosters(ActivityMain.this);
				async.execute(ActivityMain.this);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onMoviesReceived(PojoMovies movies) {
		AdapterMovies adapter = new AdapterMovies(ActivityMain.this, R.layout.item_movie, movies.results);
		mGridview.setAdapter(adapter);

	}
}
