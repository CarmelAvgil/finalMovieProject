package com.example.android.finalproject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Android on 3/13/2018.
 */
public class BasicMovieInfo {

    private String flag;

    private String name;

    public BasicMovieInfo(){}

    public BasicMovieInfo(JSONObject jsonObj) throws JSONException {
        setName(jsonObj.getString("name"));
        setFlag(jsonObj.getString("flag"));
    }

    public BasicMovieInfo(String flag,String name){
        setName(name);
        setFlag(flag);
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }


    public String getFlag ()
    {
        return flag;
    }

    public void setFlag (String flag)
    {
        this.flag = flag;
    }
}