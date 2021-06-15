package com.example.project;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListOfPastCharities extends AppCompatActivity {

    private String[] charityNames;
    private String[] charityDescription;


    protected void onCreate(Bundle savedInstanceState) {
        // ...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_past_charities_recycler_view);
        charityNames= getResources().getStringArray(R.array.pastCharityName);
        charityDescription= getResources().getStringArray(R.array.pastCharityDescription);

        // Lookup the recyclerview in activity layout
        RecyclerView rv = (RecyclerView) findViewById(R.id.ListOfPastCharitiesRecyclerView);

        // Create adapter passing in the sample user data
        ListOfPastCharitiesRecyclerViewAdapter adapter = new ListOfPastCharitiesRecyclerViewAdapter(this,charityNames,charityDescription);
        // Attach the adapter to the recyclerview to populate items
        rv.setAdapter(adapter);
        // Set layout manager to position the items
        rv.setLayoutManager(new LinearLayoutManager(this));
        // That's all!



    }
}
