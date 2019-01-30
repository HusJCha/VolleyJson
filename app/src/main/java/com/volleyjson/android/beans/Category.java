package com.volleyjson.android.beans;

public class Category
{
    Integer cat_id;
    String cat_nm,cat_url;

    public Category(Integer cat_id, String cat_nm, String cat_url)
    {
        this.cat_id = cat_id;
        this.cat_nm = cat_nm;
        this.cat_url = cat_url;
    }

    public Integer getCat_id() {
        return cat_id;
    }

    public void setCat_id(Integer cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_nm() {
        return cat_nm;
    }

    public void setCat_nm(String cat_nm) {
        this.cat_nm = cat_nm;
    }

    public String getCat_url() {
        return cat_url;
    }

    public void setCat_url(String cat_url) {
        this.cat_url = cat_url;
    }
}
