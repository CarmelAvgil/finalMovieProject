package com.example.android.finalproject;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;




public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private LinearLayout linearLayoutMain;
    private MenuItem menuItemExit;
    private MenuItem menuItemDeleteAll;
    private MenuItem menuItemAddMovieMenually;
    private MenuItem menuItemAddMovieByInternet;
    private final int REQUEST_CODE_ADD_PRODUCT = 1;
    private final int REQUEST_CODE_UPDATE_PRODUCT = 2;
    private List<Movie> movies;
    DataBase db;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayoutMain = findViewById(R.id.linearLayoutMain);
      //acssinig my database and get all the info on the movies that i have there and display them on the main activity
        db = new DataBase(this);
        displayMovies();
        listView = findViewById(R.id.MovieTitlesList);

    }

    private void displayMovies() {
        //getting all the movies from the database and going over all of them, creating the way to present each one with the onClickListeners functions
        movies = db.getAllMovieList();
        int i = 0;
        if (movies.equals(null)) {
            TextView empty = (TextView) findViewById(R.id.empty);
            empty.setVisibility(View.VISIBLE);
        } else {
            while (i < movies.size()) {
                String s = movies.get(i).getTitle();
                String u = movies.get(i).getUrl();
                String d = movies.get(i).getBody();
                int id = movies.get(i).get_id();

                makeMovie(s, d, u, id);

                i++;
            }
        }
    }

//getting all the movies from the function above, for each one creating the way to present him with the onClickListeners functions
    public void makeMovie(final String title, final String description, final String url, final int id) {
        ImageView imageView = new ImageView(this);
        TextView titleView = new TextView(this);
        TextView descriptionView = new TextView(this);
        LinearLayout mainLinearLayout = new LinearLayout(this);
        LinearLayout secLinearLayout = new LinearLayout(this);
        imageView.setTag(title);


        final CustomDialogClass cdd = new CustomDialogClass(MainActivity.this, title, id);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPresentMovie(view);

            }
        });
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                cdd.show();
                return false;
            }
        });


//my main layout

        LinearLayout.LayoutParams mainLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mainLinearLayout.setLayoutParams(mainLayoutParams);
        mainLinearLayout.setGravity(Gravity.CENTER);
        mainLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mainLinearLayout.setBackgroundResource(R.drawable.layout_shap);
        mainLayoutParams.setMargins(10, 15, 10, 0);
//my title

        titleView.setLayoutParams(mainLayoutParams);
        titleView.setTextSize(25);
        titleView.setText(title);
        titleView.setGravity(Gravity.CENTER);
        mainLinearLayout.addView(titleView);


//secondery layout

        secLinearLayout.setLayoutParams(mainLayoutParams);
        secLinearLayout.setGravity(Gravity.CENTER);
        secLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        mainLinearLayout.addView(secLinearLayout);
// image view
        LinearLayout.LayoutParams contexLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(contexLayoutParams);
        imageView.getLayoutParams().height = 720;
        imageView.getLayoutParams().width = 400;

        contexLayoutParams.setMargins(15, 15, 25, 25);
        new DownloadImageTask(this, linearLayoutMain, this, imageView, url).execute();
        secLinearLayout.addView(imageView);

// description view

        descriptionView.setLayoutParams(mainLayoutParams);

        descriptionView.setTextSize(20);
        descriptionView.setText("description: " + description);
        secLinearLayout.addView(descriptionView);
        linearLayoutMain.addView(mainLinearLayout);

    }
//the displaying of 1 movie in is own activity
    public void goToPresentMovie(View v) {
        String movieTitle = v.getTag().toString();

        for (int j = 0; j < movies.size(); j++) {
            if (movieTitle.equals(movies.get(j).getTitle())) {
                String title = movies.get(j).getTitle();
                String des = movies.get(j).getBody();
                String url = movies.get(j).getUrl();
                int id = movies.get(j).get_id();

                Intent editActivity = new Intent(this, PresentMovieActivity.class);
                editActivity.putExtra("name", title);
                editActivity.putExtra("des", des);
                editActivity.putExtra("url", url);
                editActivity.putExtra("id", id);
                this.startActivity(editActivity);

            }
        }
    }


// the menu option with all the options inside it and what they will do
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        // Need to find menu items only here because they belong to the menu and not to the main layout.
        // Also, they are not created yet on the onCreate event:
        menuItemExit = menu.findItem(R.id.Exit);
        menuItemDeleteAll = menu.findItem(R.id.DeleteAll);
        menuItemAddMovieMenually = menu.findItem(R.id.AddMovieMenually);
        menuItemAddMovieByInternet = menu.findItem(R.id.AddMovieByInternet);

        return true;
    }


    // the menu option with all the options inside it and what they will do
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.Exit:
                Toast.makeText(this, "exit", Toast.LENGTH_LONG).show();
                finish();
                return true;
            case R.id.DeleteAll:
                db.clear();
                linearLayoutMain.removeAllViews();
                Toast.makeText(this, "delete", Toast.LENGTH_LONG).show();
                return true;
            case R.id.AddMovieByInternet:
                Intent IntentInternet = new Intent(MainActivity.this, AddMovieFromInternet.class);
                startActivity(IntentInternet);
                Toast.makeText(this, "add movie", Toast.LENGTH_LONG).show();
                return true;
            case R.id.AddMovieMenually:
                Intent IntentMenually = new Intent(MainActivity.this, AddMovieMenually.class);
                startActivity(IntentMenually);
                Toast.makeText(this, "add movie", Toast.LENGTH_LONG).show();
                return true;


        }
        return false;
    }
}