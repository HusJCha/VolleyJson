package com.volleyjson.android;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.volleyjson.android.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{
    private static String urlJsonObj = "http://rajeshwersoftsolution.com/nearest_room_finder/public/api/fetchcat";
    private static String TAG = MainActivity.class.getSimpleName();
    private static String img_url ="http://rajeshwersoftsolution.com/nearest_room_finder/public/uploads/category/";
    private static ArrayList<HashMap<String,Object>> list;
    private static RecyclerView rec_json;
    private static JsonAdapter adapter;
    static Context con ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rec_json = findViewById(R.id.rec_json);
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rec_json.setLayoutManager(mLayoutManager);
        list = new ArrayList<>();
        adapter = new JsonAdapter(this,list);
        rec_json.setAdapter(adapter);
        con =this;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlJsonObj, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                Log.e(TAG, response.toString());
                try
                {
                    JSONArray data = response.getJSONArray("data");
                    for (int i = 0 ; i<data.length();i++)
                    {
                        img_url ="http://rajeshwersoftsolution.com/nearest_room_finder/public/uploads/category/";
                        JSONObject record = data.getJSONObject(i);
                        Integer cat_id = record.getInt("Category_id");
                        String cat_nm = record.getString("category_name");
                        String url = record.getString("category_image");
                        img_url = img_url+url;
                        HashMap<String,Object> map = new HashMap<>();
                        map.put("id",cat_id);
                        map.put("nm",cat_nm);
                        map.put("url",img_url);
                        list.add(map);
                    }
                    adapter = new JsonAdapter(getApplicationContext(),list);
                    rec_json.setAdapter(adapter);
                }

                catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG,"Error  : ",error.getMessage());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        try {
            AppController.getmInstance().addToRequestQueue(jsonObjectRequest);
        }
        catch (Exception e)
        {
            Log.e(TAG,e.toString());
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    public static void openProducts(Integer cat_id)
    {
        Intent i = new Intent(con,AllProductsActivity.class);
        i.putExtra("cat_id",cat_id);
        con.startActivity(i);
    }
    }

//    private void makeJsonArrayRequest()
//    {
//        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                Log.e(TAG,response.toString());
//                try
//                {
//                    for (int i = 0; i < response.length(); i++) {
//
//                        JSONObject person = (JSONObject) response.get(i);
//                        String name = person.getString("name");
//                        String email = person.getString("email");
//                        JSONObject phone = person.getJSONObject("phone");
//                        String home = phone.getString("home");
//                        String mobile = phone.getString("mobile");
//                        HashMap<String ,String> map = new HashMap<>();
//                        map.put("nm",name);
//                        map.put("email",email);
//                        map.put("home",home);
//                        map.put("mobile",mobile);
//                        list.add(map);
//                    }
//                    adapter = new JsonAdapter(con,list);
//                    rec_json.setAdapter(adapter);
//                } catch (JSONException e)
//                {
//                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        AppController.getmInstance().addToRequestQueue(req);
//    }

//    private void makeJsonObjectRequest()
//    {
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response)
//            {
//                Log.e(TAG, response.toString());
//                try
//                {
//                    String name = response.getString("name");
//                    String email = response.getString("email");
//                    JSONObject phone = response.getJSONObject("phone");
//                    String home = phone.getString("home");
//                    String mobile = phone.getString("mobile");
//                    HashMap<String ,String> map = new HashMap<>();
//                    map.put("nm",name);
//                    map.put("email",email);
//                    map.put("home",home);
//                    map.put("mobile",mobile);
//                    list.add(map);
//                    adapter = new JsonAdapter(getApplicationContext(),list);
//                    rec_json.setAdapter(adapter);
//                }
//
//                catch (Exception e) {
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG,"Error  : ",error.getMessage());
//                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
//            }
//        });
//        try {
//            AppController.getmInstance().addToRequestQueue(jsonObjectRequest);
//        }
//        catch (Exception e)
//        {
//            Log.e(TAG,e.toString());
//            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
//        }
//    }
//
//}

