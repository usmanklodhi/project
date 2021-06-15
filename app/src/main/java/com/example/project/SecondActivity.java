package com.example.project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Log.d(TAG, "onCreate");
        TextView t1 = findViewById(R.id.loginTextView);
        t1.setOnClickListener(v -> {
            startActivity(new Intent(SecondActivity.this, LoginActivity.class));
        });
    }
}
