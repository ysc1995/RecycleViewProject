package com.example.shaochengyang.recycleviewproject;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {   //volley included

    RecyclerView recyclerView;
    MyRecycleViewAdaptor adaptor;
    ArrayList<Movie> movies = new ArrayList<>();
    ProgressDialog progressDialog;
    Movie myMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        adaptor = new MyRecycleViewAdaptor(movies);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(manager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(adaptor);

        //setMovieData();

        readDataFromServer();
    }

    private void readDataFromServer() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("My Dialog");
        progressDialog.setMessage("Volley Loading the data..");
        progressDialog.show();


        String url = "https://api.androidhive.info/contacts/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss(); //from previous project

                if(response != null){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray contacts = jsonObject.getJSONArray("contacts");

                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);

                            String id = c.getString("id");
                            String name = c.getString("name");
                            String email = c.getString("email");
                            myMovieList = new Movie(id,name,email);
                            movies.add(myMovieList);

                            recyclerView.setAdapter(adaptor);
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);

    }

    private void setMovieData() {
        Movie movieItem = new Movie("movie1","drama","1990");
        movies.add(movieItem);
        Movie movieItem2 = new Movie("movie2","drama","1980");
        movies.add(movieItem2);
        Movie movieItem3 = new Movie("movie3","comic","1970");
        movies.add(movieItem3);
        adaptor.notifyDataSetChanged();//refresh the data when data is changed
        Movie movieItem4 = new Movie("movie4","drama","1990");
        movies.add(movieItem4);
        Movie movieItem5 = new Movie("movie5","drama","1990");
        movies.add(movieItem5);
        Movie movieItem6 = new Movie("movie6","drama","1990");
        movies.add(movieItem6);
    }
}
