package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListOfPastCharitiesRecyclerViewAdapter extends RecyclerView.Adapter<ListOfPastCharitiesRecyclerViewAdapter.ViewHolder>{
    Context context;
    String charityName[];
    String charityDescription[];
    public ListOfPastCharitiesRecyclerViewAdapter(Context ct, String s1[], String s2[]) {
        context = ct;
        charityName= s1;
        charityDescription=s2;
    }
    @NonNull
    @Override
    public ListOfPastCharitiesRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.past_charities_list_item, parent, false);
        return new ListOfPastCharitiesRecyclerViewAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ListOfPastCharitiesRecyclerViewAdapter.ViewHolder holder, int position) {
        // Set item views based on your views and data model
        TextView textView = holder.CharityNameTextView;
        textView.setText(charityName[position]);
        TextView textView1 = holder.DescriptionTextView;
        textView1.setText(charityDescription[position]);
    }

    @Override
    public int getItemCount() {
        return charityName.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView CharityNameTextView;
        public TextView DescriptionTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            CharityNameTextView = (TextView) itemView.findViewById(R.id.PastCharityNameOnRecyclerView);
            DescriptionTextView = (TextView) itemView.findViewById(R.id.PastCharityDescriptionOnRecyclerView);
        }
    }
}
