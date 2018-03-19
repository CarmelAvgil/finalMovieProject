package com.example.android.finalproject;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

// Class for reading server data:
public class MovieReaderController extends MovieController {

    // ctor:
    public MovieReaderController(Activity activity) {
        super(activity);
    }

    // Read all countries from the server:
    public void readAllCountries() {
        HttpRequest httpRequest = new HttpRequest(this);
        httpRequest.execute("https://restcountries.eu/rest/v2/all?fields=name");
    }

    // Got all countries from the server - update all in the ListView:
    public void onSuccess(String downloadedText) {

        try {

            // Translate all to a JSON array:
            JSONArray jsonArray = new JSONArray(downloadedText);

            // Create a new array list to hold all countries:
            countries = new ArrayList<>();

            // Run on all JSON objects:
            for (int i = 0; i < jsonArray.length(); i++) {

                // Convert each country from a JSON object into an array:
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String country =name;

                // Add the country object into the countries array:
                countries.add(country);
            }

            // Set adapter for the ListView:
            ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, countries);

            // Display all:
            MovieList.setAdapter(adapter);

            //adding event listener to the ListView
            MovieList.setOnItemClickListener(
                    //On item click we will show to the screen a popup with the item's content
                    new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String selectedCountry = String.valueOf(adapterView.getItemAtPosition(i));
                            Intent intent=new Intent(activity,SingleMovieDetails.class);
                            intent.putExtra("countryName",selectedCountry);
                            activity.startActivity(intent);
                        }
                    }
            );
        }
        catch (JSONException ex) {
            Toast.makeText(activity, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        // Dismiss dialog:
        progressDialog.dismiss();
    }
}