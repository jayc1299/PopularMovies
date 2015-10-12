package com.android.test.popularmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MovieApi {

	public MovieApi(){}

	private String getApiKey(Context context){
		return context.getString(R.string.apikey);
	}

	public String getApiUrl(Context context){
		return context.getString(R.string.apiurl, getApiKey(context), getSortBy(context));
	}

	public String getImgUrl(Context context, String imgPath, boolean large){
		if(large) {
			return context.getString(R.string.imgurl, "w500", imgPath);
		}else{
			return context.getString(R.string.imgurl, "w342", imgPath);
		}
	}

	private String getSortBy(Context context){
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		return prefs.getString(context.getString(R.string.pref_sort_key), context.getString(R.string.pref_sort_default));
	}
}