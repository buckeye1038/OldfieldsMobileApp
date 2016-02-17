package com.havvyapps.oldfields_new;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
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
import java.util.List;


/**
 * Created by John on 12/31/2015.
 */
public class AlcoholListAdapter extends ArrayAdapter<Alcohol> {
    private final Context context;
    private Alcohol item;

    public AlcoholListAdapter(Context context, int resource, List<Alcohol> itemsList) {
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
        View rowView = inflater.inflate(R.layout.alcohol_listview, parent, false);

        // 3. Get the two text view from the rowView
        TextView title = (TextView) rowView.findViewById(R.id.title);
        TextView description = (TextView) rowView.findViewById(R.id.description);
        TextView brewery = (TextView) rowView.findViewById(R.id.brewery);
        TextView abv = (TextView) rowView.findViewById(R.id.abv);


        // 4. Set the text for textView
        title.setText(item.getName());
        description.setText(item.getDescription());
        brewery.setText(item.getBrewery());
        abv.setText(item.getABV() + "% abv");

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder summaryBuilder = new AlertDialog.Builder(v.getContext());
                summaryBuilder.setMessage(item.getDescription() +
                        "\n\n" + item.getBrewery());

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




