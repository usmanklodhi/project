package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DonationPageActivity extends AppCompatActivity {
    public Button DonateToASpecificCharityBtn;
    public Button ViewAllCharitiesBtn;
    protected void onCreate(Bundle SaveInstanceState) {
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.donationpage_activity);
        DonateToASpecificCharityBtn= findViewById(R.id.DonateToASpecificCharity);
        DonateToASpecificCharityBtn.setOnClickListener(v -> {
            startActivity(new Intent(DonationPageActivity.this, ListOfCharities.class));
        });
        ViewAllCharitiesBtn= findViewById(R.id.ViewAllCharities);
        ViewAllCharitiesBtn.setOnClickListener(v -> {
            startActivity(new Intent(DonationPageActivity.this, ListOfPastCharities.class));
        });
    }
}
