package com.example.trainingfavemobile.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingfavemobile.R;
import com.example.trainingfavemobile.adapters.GamesAdapter;
import com.example.trainingfavemobile.models.GamesResponse;
import com.example.trainingfavemobile.models.GamesResponseItem;
import com.example.trainingfavemobile.utils.APIInterface;
import com.example.trainingfavemobile.utils.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatabaseActivity extends AppCompatActivity {
    RecyclerView rvGames;
    ProgressBar progressBar;
    List<GamesResponseItem> gamesResponseItemList = new ArrayList<>();
    GamesAdapter gamesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        rvGames = findViewById(R.id.rv_games);
        progressBar = findViewById(R.id.progressbar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DatabaseActivity.this);
        gamesAdapter = new GamesAdapter(gamesResponseItemList, DatabaseActivity.this);
        rvGames.setLayoutManager(linearLayoutManager);
        rvGames.setAdapter(gamesAdapter);

        APIInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
        Call<GamesResponse> call = apiInterface.getGames();
        call.enqueue(new Callback<GamesResponse>() {
            @Override
            public void onResponse(Call<GamesResponse> call, Response<GamesResponse> response) {
                if (response.isSuccessful()) {
                    GamesResponse gamesResponse = response.body();
                    assert gamesResponse != null;
                    gamesResponseItemList.addAll(gamesResponse.getGamesResponse());
                    gamesAdapter.setGamesResponseItemList(gamesResponseItemList);
                } else {
                    Toast.makeText(DatabaseActivity.this, "Request Error ::" + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GamesResponse> call, Throwable t) {
                Toast.makeText(DatabaseActivity.this, "ERROR ::" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}