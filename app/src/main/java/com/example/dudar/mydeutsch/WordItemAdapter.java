package com.example.dudar.mydeutsch;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dudar on 26/11/2016.
 */

public class WordItemAdapter extends ArrayAdapter<WordItem> {

    private int mBackgroundColor;
    public WordItemAdapter(Activity context, ArrayList<WordItem> items, int bckgrColorId){
        super(context,0,items);
        mBackgroundColor = bckgrColorId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the WordItem object located at this position in the list
        WordItem currentWordItem = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_list_item);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        defaultTextView.setText(currentWordItem.getDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView deutschTextView = (TextView) listItemView.findViewById(R.id.deutsch_list_item);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        deutschTextView.setText(currentWordItem.getDeutschTranslation());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image_src);
        // Check if an image is provided for this word or not
        if (currentWordItem.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            iconView.setImageResource(currentWordItem.getImageSrc());
            // Make sure the view is visible
            iconView.setVisibility(View.VISIBLE);
        }
        else
            // Otherwise hide the ImageView (set visibility to GONE)
            iconView.setVisibility(View.GONE);

        View textContainer =listItemView.findViewById(R.id.text_layout);
        int color = ContextCompat.getColor(getContext(),mBackgroundColor);
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
