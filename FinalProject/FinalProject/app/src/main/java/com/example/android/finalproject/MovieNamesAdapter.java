package com.example.android.finalproject;

        import android.content.Context;
                import android.view.LayoutInflater;
                import android.view.View;
                import android.view.ViewGroup;
                import android.widget.ArrayAdapter;
                import android.widget.RelativeLayout;
                import android.widget.TextView;
                import java.util.ArrayList;

public class MovieNamesAdapter extends ArrayAdapter<BasicMovieInfo> {

    private LayoutInflater layoutInflater;

    public MovieNamesAdapter(Context context,ArrayList<BasicMovieInfo> countries) {
        super(context, 0, countries);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Inflate layout and get controls:
        RelativeLayout relativeLayout =
                (RelativeLayout)layoutInflater.inflate(R.layout.layout_movie_item, null);

        TextView imageViewFlag =
                (TextView)relativeLayout.findViewById(R.id.imageViewFlag);


        TextView textViewType =
                (TextView)relativeLayout.findViewById(R.id.textViewType);

        // Get current item:
        BasicMovieInfo movieInfo = getItem(position);


        textViewType.setText( movieInfo.getName());
        imageViewFlag.setText(movieInfo.getFlag());
        // Return created layout:
        return relativeLayout;
    }
}