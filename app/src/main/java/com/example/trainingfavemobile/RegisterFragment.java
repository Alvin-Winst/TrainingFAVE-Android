package com.example.trainingfavemobile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
    EditText et_username, et_firstName, et_lastName, et_password;
    Button btn_register;
    ProgressBar progressBar;
    RadioGroup rg_gender;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        progressBar = view.findViewById(R.id.progressBar_register);

        sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        et_username = view.findViewById(R.id.et_username);
        et_firstName = view.findViewById(R.id.et_firstname);
        et_lastName = view.findViewById(R.id.et_lastname);
        rg_gender = view.findViewById(R.id.rg_gender);
        et_password = view.findViewById(R.id.et_password);
        btn_register = view.findViewById(R.id.btn_register);

        final String[] gender = new String[1];

        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_male:
                        gender[0] = "male";
                        break;
                    case R.id.rb_female:
                        gender[0] = "female";
                        break;
                    case R.id.rb_none:
                        gender[0] = "none";
                        break;
                    default:
                        gender[0] = "";
                        break;
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                String username = et_username.getText().toString();
                String firstname = et_firstName.getText().toString();
                String lastname = et_lastName.getText().toString();
                String fullname = firstname + " " + lastname;
                String password = et_password.getText().toString();

                progressBar.setVisibility(View.GONE);

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(firstname) || TextUtils.isEmpty(lastname) || TextUtils.isEmpty(gender[0]) || TextUtils.isEmpty(password)){
                    Toast.makeText(getContext(), "Fill in all the required fields!", Toast.LENGTH_LONG).show();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder
                            .setTitle("Confirm Registration")
                            .setMessage("Submit?")
                            .setPositiveButton("Submit", (dialog,which) -> {
                                editor.putString("username", username).apply();
                                editor.putString("full name", fullname).apply();
                                editor.putString("gender", gender[0]).apply();
                                editor.putString("password", password).apply();
                                Toast.makeText(getContext(), "Register Successful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getContext(), HomeActivity.class);
                                startActivity(intent);
                            })
                            .setNegativeButton("Cancel",(dialog, which) -> {
                                Toast.makeText(getContext(), "Register Cancelled!", Toast.LENGTH_SHORT).show();
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });


        return view;
    }
}