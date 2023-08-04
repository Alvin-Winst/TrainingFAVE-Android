package com.example.trainingfavemobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.trainingfavemobile.models.GamesResponse;
import com.example.trainingfavemobile.models.GamesResponseItem;

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
    private int CURRENT_PAGE = 1;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        rvGames = findViewById(R.id.rv_games);
        progressBar = findViewById(R.id.progressbar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        gamesAdapter = new GamesAdapter(gamesResponseItemList, getApplicationContext());
        rvGames.setLayoutManager(linearLayoutManager);
        rvGames.setAdapter(gamesAdapter);

        loadGamesData();
    }
    private void initScrollListener(){
        rvGames.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if(!isLoading){
                    if(linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == gamesResponseItemList.size() - 1){
                        CURRENT_PAGE++;
                        isLoading = true;
                        progressBar.setVisibility(View.VISIBLE);
                        loadGamesData();
                    }
                }
            }
        });
    }

    private void loadGamesData(){
        APIInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
        Call<GamesResponse> call = apiInterface.getGamesResponse(CURRENT_PAGE);
        call.enqueue(new Callback<GamesResponse>() {
            @Override
            public void onResponse(Call<GamesResponse> call, Response<GamesResponse> response) {
                if(response.isSuccessful()){
                    GamesResponse gamesResponse = response.body();
                    assert gamesResponse != null;
                    gamesResponseItemList.addAll(gamesResponse.getGamesResponse());
                    gamesAdapter.setGamesResponseItemList(gamesResponseItemList);
                }
                isLoading = false;
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GamesResponse> call, Throwable t) {
                Toast.makeText(DatabaseActivity.this, "ERROR ::" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}