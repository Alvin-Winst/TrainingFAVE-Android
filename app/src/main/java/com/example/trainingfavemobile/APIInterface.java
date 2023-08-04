package com.example.trainingfavemobile;

import com.example.trainingfavemobile.models.GamesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("api/games")
    Call<GamesResponse> getGamesResponse(@Query("page") int currentPage);
}
