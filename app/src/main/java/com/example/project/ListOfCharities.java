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
    private ImageView theFilter;
    private EditText getText;


    //public Button  DonateButton;

    protected void onCreate(Bundle savedInstanceState) {

        // ...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_charities_recycler_view);
        recyclerView = (RecyclerView) findViewById(R.id.ListOfCharitiesRecyclerView);
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
                theFilter=(ImageView)findViewById(R.id.search_imageview);
                getText=(EditText) findViewById(R.id.searchTextView);
                getText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        filter(s.toString());
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListOfCharities.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void filter(String text) {
        ArrayList<Charity> filteredList = new ArrayList<>();

        for (Charity item : charityList) {
            if (item.getCharityName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    /*public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.charity_menu,menu);

        MenuItem searchItem = menu.findItem(R.id.charity_search);
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
    }*/
}
