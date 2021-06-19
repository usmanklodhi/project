package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListOfCharitiesRecyclerViewAdapter extends RecyclerView.Adapter<ListOfCharitiesRecyclerViewAdapter.ViewHolder> {
    Context context;
    List<Charity> charityList;
    List<Charity> charityListFull;
    private Filter filter;

    public ListOfCharitiesRecyclerViewAdapter(Context ct, List<Charity> charities) {
        context = ct;
        charityList= charities;
        charityListFull = new ArrayList<>(charityList);
    }

    public void filterList(ArrayList<Charity> filteredList) {
        charityList = filteredList;
        notifyDataSetChanged();
    }

    ListOfCharitiesRecyclerViewAdapter(List <Charity> c){
        this.charityList=c;
        charityListFull = new ArrayList <>(c);
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

   /* @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    charityListFiltered = charityListFull;
                } else {
                    List<Charity> filteredList = new ArrayList<>();
                    for (Charity row : charityList) {
                        if (row.getCharityName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    charityListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = charityListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                charityListFiltered = (ArrayList<Charity>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }*/

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
