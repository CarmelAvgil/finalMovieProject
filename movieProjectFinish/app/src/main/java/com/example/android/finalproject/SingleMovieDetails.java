package com.example.android.finalproject;

/**
 * Created by Android on 19/03/2018.
 */
// basically i created this class tow times by accident and i used it in different  places
public class SingleMovieDetails {

    private String title;
    private String description;
    private String image;

    public SingleMovieDetails (String title, String description , String image){
        this.title=title;
        this.description=description;
        this.image = image;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
