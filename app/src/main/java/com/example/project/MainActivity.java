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

    public Button btnSignUpAdmin;
    public Button btnSignUpUser;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        /*
        btn = (Button) findViewById(R.id.signupAsAdminBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        */
        btnSignUpUser = findViewById(R.id.signupAsDonorBtn);
        btnSignUpUser.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ThirdActivity.class));
        });
    }
}