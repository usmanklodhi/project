package com.example.project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    public Button btnSignUpUser;
    public Button btnLoginUser;
    private DBHelper mydb;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        mydb = new DBHelper(this);
        // mydb.deleteDatabase();
        btnSignUpUser = findViewById(R.id.CreateAnAccountBtn);
        btnSignUpUser.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ThirdActivity.class));
        });
        btnLoginUser = findViewById(R.id.LoginBtnOnMainPage);
        btnLoginUser.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });
    }
}