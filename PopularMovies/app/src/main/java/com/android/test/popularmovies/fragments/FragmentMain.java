package com.android.test.popularmovies.fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.test.popularmovies.Async.AsyncGetMoviePosters;
import com.android.test.popularmovies.Async.PojoMovies;
import com.android.test.popularmovies.Async.Result;
import com.android.test.popularmovies.R;
import com.android.test.popularmovies.activities.ActivityDetail;
import com.android.test.popularmovies.adapters.AdapterMovies;

public class FragmentMain extends Fragment implements AsyncGetMoviePosters.IAsyncMovies {

    private RecyclerView mRecycler;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mRecycler = view.findViewById(R.id.fragment_main_gridview);
        mRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMovies();
    }

    /**
     * Get movies from web. This method is public as it may be refreshed from activity.
     */
    public void getMovies() {
        AsyncGetMoviePosters async = new AsyncGetMoviePosters(this);
        async.execute(getActivity());
    }

    @Override
    public void onMoviesReceived(PojoMovies movies) {
        if(getActivity() != null && !getActivity().isFinishing()) {
            if (movies != null && movies.results.size() > 0) {
                AdapterMovies adapter = new AdapterMovies(getActivity(), movieClickedListener, movies.results);
                mRecycler.setAdapter(adapter);
                getView().findViewById(R.id.fragment_main_no_results).setVisibility(View.GONE);
                mRecycler.setVisibility(View.VISIBLE);
            } else {
                getView().findViewById(R.id.fragment_main_no_results).setVisibility(View.VISIBLE);
                mRecycler.setVisibility(View.GONE);
            }
        }
    }

    private AdapterMovies.IMovieClickedListener movieClickedListener = new AdapterMovies.IMovieClickedListener() {
        @Override
        public void onMovieClickedListener(Result movie, View view) {
            Intent intent = new Intent(getActivity(), ActivityDetail.class);
            intent.putExtra(ActivityDetail.TAG_MOVIE_OBJECT, movie);

            ActivityOptions options = null;
            // create the transition animation - the images in the layouts of both activities are defined with android:transitionName="MyTransition"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), view, getString(R.string.movieTransitionName));
            }
            // start the new activity
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && options != null) {
                getActivity().startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }

        }
    };
}