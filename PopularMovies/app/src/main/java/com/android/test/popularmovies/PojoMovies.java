package com.android.test.popularmovies;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoMovies {

	@SerializedName("page")
	@Expose
	public int page;
	@SerializedName("results")
	@Expose
	public List<Result> results = new ArrayList<Result>();
	@SerializedName("total_pages")
	@Expose
	public int totalPages;
	@SerializedName("total_results")
	@Expose
	public int totalResults;


	public class Result {

		@SerializedName("adult")
		@Expose
		public boolean adult;
		@SerializedName("backdrop_path")
		@Expose
		public String backdropPath;
		@SerializedName("genre_ids")
		@Expose
		public List<Integer> genreIds = new ArrayList<Integer>();
		@SerializedName("id")
		@Expose
		public int id;
		@SerializedName("original_language")
		@Expose
		public String originalLanguage;
		@SerializedName("original_title")
		@Expose
		public String originalTitle;
		@SerializedName("overview")
		@Expose
		public String overview;
		@SerializedName("release_date")
		@Expose
		public String releaseDate;
		@SerializedName("poster_path")
		@Expose
		public String posterPath;
		@SerializedName("popularity")
		@Expose
		public float popularity;
		@SerializedName("title")
		@Expose
		public String title;
		@SerializedName("video")
		@Expose
		public boolean video;
		@SerializedName("vote_average")
		@Expose
		public float voteAverage;
		@SerializedName("vote_count")
		@Expose
		public int voteCount;

	}
}