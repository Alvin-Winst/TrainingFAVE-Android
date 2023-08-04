package com.example.trainingfavemobile.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GamesResponse{

	@SerializedName("GamesResponse")
	private List<GamesResponseItem> gamesResponse;

	public List<GamesResponseItem> getGamesResponse(){
		return gamesResponse;
	}
}