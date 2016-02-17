package com.havvyapps.oldfields_new;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;

import java.text.NumberFormat;
import java.util.ArrayList;


/**
 * Created by John on 12/31/2015.
 */
public class SpecialsListAdapter extends ArrayAdapter<Specials> {
    private final Context context;
    private Specials item;
    private ArrayList<Specials> itemsList;

    public SpecialsListAdapter(Context context, int resource, ArrayList<Specials> itemsArrayList) {
        super(context, resource, itemsArrayList);
        this.context = context;
        itemsList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        item = getItem(position);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.specials_listview, parent, false);

        // 3. Get the two text view from the rowView
        TextView description = (TextView) rowView.findViewById(R.id.description);


        // 4. Set the text for textView

        description.setText(item.getDescription());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder summaryBuilder = new AlertDialog.Builder(v.getContext());
                summaryBuilder.setMessage(item.getDescription());

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




