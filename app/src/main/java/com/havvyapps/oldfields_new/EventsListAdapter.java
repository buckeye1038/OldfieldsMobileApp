package com.havvyapps.oldfields_new;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by John on 12/31/2015.
 */
public class EventsListAdapter extends ArrayAdapter<Events> {
    private final Context context;
    private Events item;

    public EventsListAdapter(Context context, int resource, List<Events> itemsList) {
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
        View rowView = inflater.inflate(R.layout.events_listview, parent, false);

        // 3. Get the two text view from the rowView
        TextView title = (TextView) rowView.findViewById(R.id.title);
        TextView description = (TextView) rowView.findViewById(R.id.description);
        TextView dateField = (TextView) rowView.findViewById(R.id.date);


        // 4. Set the text for textView


        Date date = item.getDate();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        dateField.setText(dateFormat.format(date));

        title.setText(item.getName());
        description.setText(item.getDescription());


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder summaryBuilder = new AlertDialog.Builder(v.getContext());
                summaryBuilder.setMessage(item.getDescription());
                summaryBuilder.setTitle(item.getName());

                summaryBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // doesn't need to do anything!
                    }
                });

                AlertDialog summaryDialog = summaryBuilder.create();
                summaryDialog.show();
            }
        });

        // 5. return rowView
        return rowView;
    }
}




