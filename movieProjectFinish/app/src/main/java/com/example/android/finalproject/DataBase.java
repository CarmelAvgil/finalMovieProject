package com.example.android.finalproject;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;


//this class handles the all database tasks and extends the SQLiteOpenHelper (a build class)

public class DataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "movieDatabase";

    public static final String TABLE_MOVIES = "MoviesDetails";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "movieName";
    public static final String KEY_DESCRIPTION = "movieDescription";
    public static final String KEY_URL = "movieURL";




    public DataBase(Context contex) {
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating the table in the oncreate
    @Override
    public void onCreate(SQLiteDatabase db) {


        String query = "CREATE TABLE " + TABLE_MOVIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_URL + " TEXT); ";

        db.execSQL(query);
    }
//just an onupgrade method not really in use in this project right now
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);

        // Create tables again
        onCreate(db);
    }


    public void clear() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIES, null, null);
        db.execSQL("delete from " + TABLE_MOVIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);

        onCreate(db);
    }

// adding movie method simple chosing a movie with all it componnents and puting them inside the table
    public void addMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        String title = movie.getTitle();
        String body = movie.getBody();
        String url = movie.getUrl();
        values.put(KEY_NAME, title);
        values.put(KEY_DESCRIPTION, body);
        values.put(KEY_URL, url);


        db.insert(TABLE_MOVIES, null, values);
        db.close();
    }

//deleting movie from the database by his id
    public boolean deleteMovie(int delID) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_MOVIES, KEY_ID + "=" + delID, null) > 0;

    }

//getting all the movies as a list of movies
    public List<Movie> getAllMovieList() {

        List<Movie> MovieList = new ArrayList<Movie>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MOVIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Movie movie = new Movie();
                movie.set_id(Integer.parseInt(cursor.getString(0)));
                movie.setTitle(cursor.getString(1));
                movie.setBody(cursor.getString(2));
                movie.setUrl(cursor.getString(3));


                // Adding contact to list
                MovieList.add(movie);

            } while (cursor.moveToNext());
        }

        // return contact list
        return MovieList;
    }

}