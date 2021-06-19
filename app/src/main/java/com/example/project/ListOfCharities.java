package com.example.project;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListOfCharities extends AppCompatActivity {

   // private String[] charityNames;
    private List<Charity> charityList;
    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private ListOfCharitiesRecyclerViewAdapter adapter;
    //public Button  DonateButton;

    protected void onCreate(Bundle savedInstanceState) {
        // ...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_charities_recycler_view);
        //charityNames= getResources().getStringArray(R.array.charityName);

        // Lookup the recyclerview in activity layout
        recyclerView = (RecyclerView) findViewById(R.id.ListOfCharitiesRecyclerView);

        // Create adapter passing in the sample user data
        //ListOfCharitiesRecyclerViewAdapter adapter = new ListOfCharitiesRecyclerViewAdapter (this,charityList);
        // Attach the adapter to the recyclerview t0o populate items
        //recyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        charityList = new ArrayList<Charity>();

        fetchCharities();


    }

    private void fetchCharities(){
        String url = "https://awais753.github.io/json-host/ngos.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i< response.length(); i++){
                    try{
                        JSONObject jsonObject = response.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String description = jsonObject.getString("description");

                        Charity charity = new Charity(name,description);
                        charityList.add(charity);
                    } catch(JSONException e){
                        e.printStackTrace();
                    }

                }
                adapter = new ListOfCharitiesRecyclerViewAdapter(ListOfCharities.this, charityList);

                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListOfCharities.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_of_charities_filter_menu,menu);

        MenuItem searchItem = menu.findItem(R.id.search_filter);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
