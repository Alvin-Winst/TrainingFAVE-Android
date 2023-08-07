package com.example.trainingfavemobile.utils;

import com.example.trainingfavemobile.models.GamesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("games")
    Call<GamesResponse> getGames();
}
