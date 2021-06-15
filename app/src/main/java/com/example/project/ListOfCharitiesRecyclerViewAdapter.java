package com.example.project;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListOfCharitiesRecyclerViewAdapter extends RecyclerView.Adapter<ListOfCharitiesRecyclerViewAdapter.ViewHolder> {
    Context context;
    String s[];
    public ListOfCharitiesRecyclerViewAdapter(Context ct, String s1[]) {
        context = ct;
        s= s1;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.charity_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Set item views based on your views and data model
        TextView textView = holder.CharityNameTextView;
        textView.setText(s[position]);

    }

    @Override
    public int getItemCount() {
        return s.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView CharityNameTextView;
        public Button DonateButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            CharityNameTextView = (TextView) itemView.findViewById(R.id.CharityNameOnRecyclerView);
            DonateButton = (Button) itemView.findViewById(R.id.DonateBtnOnRecyclerView);
        }
    }


}
