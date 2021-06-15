package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    EditText emailId;
    EditText password;
    Button login;
    private DBHelper mydb;

    protected void onCreate(Bundle SaveInstanceState) {
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.login_activity);
        Log.d(TAG, "onCreate");
        mydb = new DBHelper(this);
        emailId = findViewById(R.id.EmailAddressLogin);
        password = findViewById(R.id.PasswordLoginUser);
        login = findViewById(R.id.LoginBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uEmailId = emailId.getText().toString();
                String uPassword = password.getText().toString();
                if (mydb.doesUserExist(uEmailId, uPassword)) {
                    showMessage("Sign in successful");
                } else {
                    showMessage("Sign in failed");
                }

            }
        });

        TextView t1 = findViewById(R.id.CreateAccountTextView);
        t1.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, ThirdActivity.class));
        });
    }

    private void showMessage(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
