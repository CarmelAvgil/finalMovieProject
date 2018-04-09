package com.example.android.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PresentMovieActivity extends AppCompatActivity {
    TextView title;
    TextView description;

    LinearLayout l;
    ImageView imageView;
// just an activity to present a single movie without anything special
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_movie);

        imageView = (ImageView) findViewById(R.id.imageView);
        title = (TextView) findViewById(R.id.titleView);
        description = (TextView) findViewById(R.id.descriptionView);

        l = (LinearLayout) findViewById(R.id.editLayout);

        Intent A = getIntent();
        Bundle b = A.getExtras();
        if (b != null) {

            String NAME = (String) b.get("name");
            String d = (String) b.get("des");
            String u = (String) b.get("url");

            title.setText(NAME);
            description.setText(d);
            new DownloadImageTask(this, l, this, imageView, u).execute();
        }

    }
}