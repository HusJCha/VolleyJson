package com.volleyjson.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder>
{
    Context con;
    ArrayList<HashMap<String,Object>> list;
    ArrayList<Integer> pro_ids = new ArrayList<>();

    public ProductAdapter(Context con, ArrayList<HashMap<String, Object>> list)
    {
        this.con = con;
        this.list = list;
    }

    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_product,null);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.MyViewHolder holder, int position)
    {
        HashMap<String ,Object> map =new HashMap<>();
        map=list.get(position);
        Integer pro_id = (Integer)map.get("id");
        pro_ids.add(pro_id);
        String tv_pro_nm = map.get("pro_nm").toString();
        String img_url = map.get("img").toString();
        holder.tv_pro_nm.setText(tv_pro_nm);
        Glide.with(con).load(img_url).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.img_pro_img);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img_pro_img;
        TextView tv_pro_nm;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_pro_nm = itemView.findViewById(R.id.tv_pro_nm);
            img_pro_img = itemView.findViewById(R.id.img_pro);
        }
    }
}
