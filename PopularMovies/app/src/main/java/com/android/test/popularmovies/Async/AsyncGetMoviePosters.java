package com.android.test.popularmovies.Async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.test.popularmovies.MovieApi;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncGetMoviePosters extends AsyncTask<Context, Void, PojoMovies> {

    private static final int mTimeout = 15000;
    private static final String TAG = AsyncGetMoviePosters.class.getSimpleName();
    private IAsyncMovies mListener;

    public interface IAsyncMovies {
        void onMoviesReceived(PojoMovies movies);
    }

    public AsyncGetMoviePosters(IAsyncMovies listener) {
        mListener = listener;
    }

    @Override
    protected PojoMovies doInBackground(Context... params) {
        InputStream is = null;
        Context context = params[0];
        MovieApi api = new MovieApi(context);

        try {
            URL url = api.getMoviesUrl();
            Log.d(TAG, "myUrl:" + url.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(mTimeout);
            conn.setConnectTimeout(mTimeout);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is);

            Gson gson = new Gson();
            return gson.fromJson(contentAsString, PojoMovies.class);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: ", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    Log.e(TAG, "Crash in catch", e);
                }
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(PojoMovies movies) {
        super.onPostExecute(movies);

        if (mListener != null) {
            mListener.onMoviesReceived(movies);
        }
    }

    private String readIt(InputStream stream) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(stream));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }

        return total.toString();
    }
}