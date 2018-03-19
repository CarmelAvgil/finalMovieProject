package com.example.android.finalproject;


        import android.content.Intent;
        import android.graphics.Color;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.widget.LinearLayout;
        import android.widget.Toast;

// Note: you can write an onClick event handler in the menu item - in the xml,
// with the event handler implemented here in the MainActivity, but this will cause
// the onOptionsItemSelected not to be called and the event handler to be executed instead.
public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayoutMain;
    private MenuItem menuItemExit;
    private MenuItem menuItemDeleteAll;
    private MenuItem menuItemAddMovieMenually;
    private MenuItem menuItemAddMovieByInternet;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        // Need to find menu items only here because they belong to the menu and not to the main layout.
        // Also, they are not created yet on the onCreate event:
        menuItemExit = menu.findItem(R.id.Exit);
        menuItemDeleteAll = menu.findItem(R.id.DeleteAll);
        menuItemAddMovieMenually = menu.findItem(R.id.AddMovieMenually);
        menuItemAddMovieByInternet  = menu.findItem(R.id.AddMovieByInternet);

        return true;
    }

    // Return true to state that the menu event has been handled.
    // Return false to state that the menu event has not been handled.
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.Exit:
                Toast.makeText(this, "exit", Toast.LENGTH_LONG).show();
                finish();
                return true;
            case R.id.DeleteAll:

                Toast.makeText(this, "delete", Toast.LENGTH_LONG).show();
                return true;
            case R.id.AddMovieByInternet:
                Intent IntentInternet =new Intent( MainActivity.this, AddMovieFromInternet.class);
                startActivity(IntentInternet);
                Toast.makeText(this, "add movie", Toast.LENGTH_LONG).show();
                return true;
            case R.id.AddMovieMenually:
                Intent IntentMenually =new Intent( MainActivity.this, AddMovieMenually.class);
                startActivity(IntentMenually);
                Toast.makeText(this, "add movie", Toast.LENGTH_LONG).show();
                return true;


        }
        return false;
    }
}