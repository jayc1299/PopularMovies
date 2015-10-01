package com.android.test.popularmovies;

import android.content.Context;

public class MovieApi {

	public MovieApi(){}

	private String getApiKey(Context context){
		return context.getString(R.string.apikey);
	}

	public String getApiUrl(Context context){
		return context.getString(R.string.apiurl, getApiKey(context));
	}

	public String getImgUrl(Context context, String imgPath){
		return context.getString(R.string.imgurl, imgPath, getApiKey(context));
	}
}