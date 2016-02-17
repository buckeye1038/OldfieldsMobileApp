package com.havvyapps.oldfields_new;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by John on 12/31/2015.
 */
public class HoursListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private String item;

    public HoursListAdapter(Context context, int resource, ArrayList<String> itemsList) {
        super(context, resource, itemsList);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        item = getItem(position);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.hours_listview, parent, false);

        // 3. Get the two text view from the rowView
        TextView title = (TextView) rowView.findViewById(R.id.hours);


        // 4. Set the text for textView

        title.setText(item);

        // 5. return rowView
        return rowView;
    }
}




