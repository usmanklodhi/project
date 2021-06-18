package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListOfCharitiesRecyclerViewAdapter extends RecyclerView.Adapter<ListOfCharitiesRecyclerViewAdapter.ViewHolder> {
    Context context;
    List<Charity> charityList;
    private DBHelper db;
    public ListOfCharitiesRecyclerViewAdapter(Context ct, List<Charity> charities) {
        context = ct;
        charityList= charities;

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
        //TextView textView = holder.CharityNameTextView;
        //textView.setText(charityList.get(position).getCharityName());

        Charity charity = charityList.get(position);
        holder.CharityNameTextView.setText(charity.getCharityName());
        //holder.title.setText(charity.getTitle());
    }

    @Override
    public int getItemCount() {
        return charityList.size();
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
            DonateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, DonationActivity.class));

                }
            });
        }
    }


}
