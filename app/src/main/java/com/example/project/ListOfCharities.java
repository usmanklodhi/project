package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListOfCharities extends AppCompatActivity {

    private String[] charityNames;
    public Button  DonateButton;

    protected void onCreate(Bundle savedInstanceState) {
        // ...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_charities_recycler_view);
        charityNames= getResources().getStringArray(R.array.charityName);

        // Lookup the recyclerview in activity layout
        RecyclerView rv = (RecyclerView) findViewById(R.id.ListOfCharitiesRecyclerView);

        // Create adapter passing in the sample user data
        ListOfCharitiesRecyclerViewAdapter adapter = new ListOfCharitiesRecyclerViewAdapter (this,charityNames);
        // Attach the adapter to the recyclerview to populate items
        rv.setAdapter(adapter);
        // Set layout manager to position the items
        rv.setLayoutManager(new LinearLayoutManager(this));
        // That's all!



    }
}
