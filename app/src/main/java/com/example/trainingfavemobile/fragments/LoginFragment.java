package com.example.trainingfavemobile.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.trainingfavemobile.R;
import com.example.trainingfavemobile.activities.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    EditText et_username, et_password;
    Button btn_login;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        progressBar = view.findViewById(R.id.progressBar_login);

        sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        et_username = view.findViewById(R.id.et_usernameLogin);
        et_password = view.findViewById(R.id.et_passwordLogin);
        btn_login = view.findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                String filled_username = et_username.getText().toString();
                String filled_password = et_password.getText().toString();
                String username = sharedPreferences.getString("username","");
                String password = sharedPreferences.getString("password", "");

//                et_username.setText(username);
//                et_password.setText(password);

                progressBar.setVisibility(View.GONE);

                if (TextUtils.isEmpty(filled_username)){
                    Toast.makeText(getContext(), "Insert username!", Toast.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(filled_password)){
                    Toast.makeText(getContext(), "Insert password!", Toast.LENGTH_LONG).show();
                }
                else if (!filled_username.equals(username) || !filled_password.equals(password)){
                    Toast.makeText(getContext(), "Username or password doesn't match!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), HomeActivity.class);
                    startActivity(intent);
                }
            }
        });




        return view;
    }
}