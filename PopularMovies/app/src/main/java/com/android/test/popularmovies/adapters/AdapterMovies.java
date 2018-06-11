package com.android.test.popularmovies.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.test.popularmovies.Async.Result;
import com.android.test.popularmovies.MovieApi;
import com.android.test.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.MovieViewHolder> {

    private List<Result> mMovies;
    private Context mContext;
    private MovieApi mApi;
    private IMovieClickedListener listener;

    public AdapterMovies(Context context, IMovieClickedListener listener, List<Result> movies) {
        this.listener = listener;
        mMovies = movies;
        mContext = context;
        mApi = new MovieApi(mContext);
    }

    public interface IMovieClickedListener {
        void onMovieClickedListener(Result movie, View view);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        String path = mApi.getImgUrl(mMovies.get(position).posterPath, false);
        Picasso.with(mContext).load(path).into(holder.movieImageView);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView movieImageView;

        MovieViewHolder(View itemView) {
            super(itemView);
            movieImageView = itemView.findViewById(R.id.item_movie_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onMovieClickedListener(mMovies.get(getAdapterPosition()), view);
            }
        }
    }
}