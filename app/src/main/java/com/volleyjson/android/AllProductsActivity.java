package com.volleyjson.android;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.volleyjson.android.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllProductsActivity extends AppCompatActivity
{
    private static String TAG = AllProductsActivity.class.getSimpleName();
    static Context con;
    static String img_url = "";
    static RecyclerView rec_pro;
    static ArrayList<HashMap<String,Object>> list;
    static ProductAdapter adapter;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        con =this;
        rec_pro=findViewById(R.id.rec_pro);
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rec_pro.setLayoutManager(mLayoutManager);
        list=new ArrayList<>();
        pDialog = new ProgressDialog(AllProductsActivity.this);
        pDialog.setMessage("Please Wait..");
        pDialog.setCancelable(false);
        pDialog.show();
        final Integer cat_id = getIntent().getIntExtra("cat_id",0);
        String product_url = "http://rajeshwersoftsolution.com/nearest_room_finder/public/api/fetchproperty";
        final StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, product_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Log.e(TAG, "Login with email" + response);
                    try {
                        JSONObject product = new JSONObject(response);
                        JSONArray data = product.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            img_url = "http://rajeshwersoftsolution.com/nearest_room_finder/public/uploads/property/";
                            JSONObject record = data.getJSONObject(i);
                            Integer pro_id = record.getInt("Property_id");
                            String pro_nm = record.getString("property_name");
                            String url = record.getString("property_image_1");
                            img_url = img_url + url;
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("id", pro_id);
                            map.put("pro_nm", pro_nm);
                            map.put("img", img_url);
                            list.add(map);
                        }
                        adapter = new ProductAdapter(con, list);
                        rec_pro.setAdapter(adapter);
                        pDialog.hide();
                    } catch (JSONException e) {
                        Toast.makeText(con,"No data Found",Toast.LENGTH_SHORT).show();
                        pDialog.hide();
                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v(TAG, "Request for getUserByEmail Error: " + error.toString());
            }
        }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String > params = new HashMap<String, String >();
                    params.put("category_id", cat_id.toString());
                    return params;
                }
        };
        try {
            AppController.getmInstance().addToRequestQueue(jsonObjectRequest);
        }
        catch (Exception e)
        {
            Log.e(TAG,e.toString());
            Toast.makeText(con,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
