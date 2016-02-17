package com.havvyapps.oldfields_new;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;


/**
 * Created by John on 12/31/2015.
 */
public class FoodListAdapter extends ArrayAdapter<Food> {
    private final Context context;
    private final ArrayList<Food> itemsList;

    public FoodListAdapter(Context context, int resource, ArrayList<Food> itemsArrayList) {
        super(context, resource, itemsArrayList);
        this.context = context;
        itemsList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final Food item = getItem(position);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.food_listview, parent, false);

        // 3. Get the two text view from the rowView
        TextView title = (TextView) rowView.findViewById(R.id.title);
        TextView description = (TextView) rowView.findViewById(R.id.description);
        TextView priceText = (TextView) rowView.findViewById(R.id.price);


        // 4. Set the text for textView
        title.setText(item.getName());
        description.setText(item.getDescription());
        Number price = item.getPrice();
        final String priceString = NumberFormat.getCurrencyInstance().format(price);
        priceText.setText(priceString);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder summaryBuilder = new AlertDialog.Builder(v.getContext());


                summaryBuilder.setMessage(item.getDescription() +
                        "\n\n" + priceString);


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




