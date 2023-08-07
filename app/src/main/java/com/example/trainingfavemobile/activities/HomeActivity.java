package com.example.trainingfavemobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trainingfavemobile.R;

public class HomeActivity extends AppCompatActivity {
    TextView tv_fullName;
    SharedPreferences sharedPreferences;
    Button btn_showdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tv_fullName = findViewById(R.id.tv_fullName);
        btn_showdb = findViewById(R.id.btn_showDatabase);
        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        tv_fullName.setText(sharedPreferences.getString("full name", "full name"));
        String gender = sharedPreferences.getString("gender", "none");

        if(gender.equals("male")){
            tv_fullName.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_face_24,0,0,0);
        }
        else if(gender.equals("female")){
            tv_fullName.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_face_3_24,0,0,0);
        }
        else{
            tv_fullName.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_android_24,0,0,0);
        }

        btn_showdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DatabaseActivity.class);
                startActivity(intent);
            }
        });
    }
}