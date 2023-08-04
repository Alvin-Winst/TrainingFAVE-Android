package com.example.trainingfavemobile.models;

import com.google.gson.annotations.SerializedName;

public class GamesResponseItem{

	@SerializedName("short_description")
	private String shortDescription;

	@SerializedName("thumbnail")
	private String thumbnail;

	@SerializedName("game_url")
	private String gameUrl;

	@SerializedName("release_date")
	private String releaseDate;

	@SerializedName("freetogame_profile_url")
	private String freetogameProfileUrl;

	@SerializedName("genre")
	private String genre;

	@SerializedName("publisher")
	private String publisher;

	@SerializedName("developer")
	private String developer;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("platform")
	private String platform;

	public String getShortDescription(){
		return shortDescription;
	}

	public String getThumbnail(){
		return thumbnail;
	}

	public String getGameUrl(){
		return gameUrl;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public String getFreetogameProfileUrl(){
		return freetogameProfileUrl;
	}

	public String getGenre(){
		return genre;
	}

	public String getPublisher(){
		return publisher;
	}

	public String getDeveloper(){
		return developer;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getPlatform(){
		return platform;
	}
}