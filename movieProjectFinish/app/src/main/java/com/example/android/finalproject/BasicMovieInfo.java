package com.example.android.finalproject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Android on 3/13/2018.
 */
public class BasicMovieInfo {


// getting the titles for the internet search from jason to normal strings
    private String name;

    public BasicMovieInfo(){}

    public BasicMovieInfo(JSONObject jsonObj) throws JSONException {
        setName(jsonObj.getString("name"));

    }

    public BasicMovieInfo(String name){
        setName(name);

    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }





}