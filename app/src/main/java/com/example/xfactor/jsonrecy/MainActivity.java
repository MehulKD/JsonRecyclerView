package com.example.xfactor.jsonrecy;


import android.app.SearchManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ArrayList<item> end = new ArrayList<item>();
    RecyclerView recyclerView;
    Adapter adapter;
    List<item> mModels;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("KEY2", end);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View view = LayoutInflater.from(this).inflate(R.layout.activity_main, null, false);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.RecylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this);
        recyclerView.setAdapter(adapter);


        if (isAvailable() && savedInstanceState == null)
        //Check if network is available
        {
            jsonreq();
        } else if (savedInstanceState != null)

        {
            end = savedInstanceState.getParcelableArrayList("KEY2");
            adapter.list(end);


        } else {
            Toast.makeText(this, "Network Unavailable Please Try Again", Toast.LENGTH_LONG).show();

        }


    }


    private boolean isAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        Boolean isAvail = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvail = true;
        }
        return isAvail;

    }

    public void jsonreq() {
        String url = "http://ish1995.5gbfree.com/ishaan1995/image.json";
        RequestQueue requestQueue = VolleySingelton.getrequestQueue();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                end = getdata(response);
                adapter.list(end);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);

    }

    private ArrayList<item> getdata(JSONObject response) {
        ArrayList<item> listy = new ArrayList<item>();

        try {

            JSONArray array = response.getJSONArray("items");

            for (int i = 0; i < array.length(); i++) {
                JSONObject object1 = array.getJSONObject(i);
                String content = object1.getString("caption");
                String url = object1.getString("url");
                // ImageView image = process(url,imageView);
                item I = new item(content, url);
                listy.add(I);

            }


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error" + e, Toast.LENGTH_LONG).show();
        }
        Log.d("mohak", "" + response.length());

        return listy;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true; // handled
            }

            @Override
            public boolean onQueryTextChange(String newText) {

               adapter.getFilter().filter(newText);

                return true;
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}

