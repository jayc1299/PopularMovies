package com.android.test.popularmovies.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.test.popularmovies.Async.Result;
import com.android.test.popularmovies.MovieApi;
import com.android.test.popularmovies.R;
import com.android.test.popularmovies.activities.ActivityDetail;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class FragmentDetail extends Fragment {

    private Result mMovie;
    private IFragmentDetailCallback mCallback;
    private ImageView mImage;

    public interface IFragmentDetailCallback {
        void onMoviePosterLoaded(View v);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (IFragmentDetailCallback) context;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MovieApi api = new MovieApi(getActivity());

        //Get movie passed in from activity
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            mMovie = intent.getParcelableExtra(ActivityDetail.TAG_MOVIE_OBJECT);
        }

        if (mMovie != null) {
            //Title
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            actionBar.setTitle(mMovie.title);

            //Text
            ((TextView) getView().findViewById(R.id.fragment_detail_title)).setText(mMovie.title);
            ((TextView) getView().findViewById(R.id.fragment_detail_release_date)).setText(mMovie.releaseDate);
            ((TextView) getView().findViewById(R.id.fragment_detail_vote_average)).setText(String.valueOf(mMovie.voteAverage));
            ((TextView) getView().findViewById(R.id.fragment_detail_synopsis)).setText(String.valueOf(mMovie.overview));

            //Set image
            mImage = (ImageView) getView().findViewById(R.id.fragment_detail_image);
            String path = api.getImgUrl(mMovie.posterPath, true);

            //Callback to activity to give go ahead to load.
            Picasso.with(getActivity()).load(path).into(mImage, new Callback() {
                @Override
                public void onSuccess() {
                    mCallback.onMoviePosterLoaded(mImage);
                }

                @Override
                public void onError() {
                    mCallback.onMoviePosterLoaded(mImage);
                }
            });
        }
    }
}