package com.volleyjson.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonAdapter extends RecyclerView.Adapter<JsonAdapter.MyViewHolder>
{
    Context con;
    ArrayList<HashMap<String,Object>> list;
    ArrayList<Integer> ids = new ArrayList<>();
    ArrayList<Integer> pro_ids = new ArrayList<>();

    public JsonAdapter(Context con, ArrayList<HashMap<String, Object>> list)
    {
        this.con = con;
        this.list = list;
    }

    @Override
    public JsonAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_json,null);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(JsonAdapter.MyViewHolder holder, final int position)
    {
        final HashMap<String,Object> map =list.get(position);
        String nm,img_url;
        Integer cat_id = (Integer)map.get("id");
        ids.add(cat_id);
        nm = map.get("nm").toString();
        img_url = map.get("url").toString();
        holder.tv_nm.setText(nm);
        Glide.with(con).load(img_url).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.img_cat);
        holder.tv_nm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer cat_id = ids.get(position);
                MainActivity.openProducts(cat_id);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_nm;
        ImageView img_cat;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            tv_nm = itemView.findViewById(R.id.tv_cat_nm);
            img_cat = itemView.findViewById(R.id.img_cat);
        }
    }
}
