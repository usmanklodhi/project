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

import java.util.regex.Pattern;

public class ThirdActivity extends AppCompatActivity {
    private static final String TAG = "ThirdActivity";

    EditText emailAddress;
    EditText name;
    EditText password;
    EditText confirmPassword;
    Button signUpUser;
    private DBHelper mydb;

    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
        Log.d(TAG, "onCreate");
        mydb = new DBHelper(this);
        TextView t1 = findViewById(R.id.LoginTextViewUser);

        signUpUser = findViewById(R.id.SignupUserBtn);

        signUpUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailAddress = findViewById(R.id.EmailAddressUserSignup);
                name = findViewById(R.id.FullNameUserSignup);
                password = findViewById(R.id.PasswordUserSignup);
                confirmPassword = findViewById(R.id.ConfirmPasswordUserSignup);

                String uEmailAddress = emailAddress.getText().toString();
                String uName = name.getText().toString();
                String uPassword = password.getText().toString();
                String uConfirmPassword = confirmPassword.getText().toString();
                //Leave confirm password for now

                if (!uPassword.equals(uConfirmPassword)) {
                    showMessage("Password Not matching");
                } else {
                    User user = new User(uEmailAddress, uName, uPassword, false);
                    mydb.addUser(user);
                    showMessage("User Added with Name: " + user.getFullName());
                    //Open the corresponding activity
                }
            }
        });


        t1.setOnClickListener(v -> {
            startActivity(new Intent(ThirdActivity.this, LoginActivity.class));
        });
    }

    private void showMessage(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
