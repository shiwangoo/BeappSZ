package com.example.beappsz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<String> {
  private final Context context;
  private final String[] values;

  public CustomArrayAdapter(Context context, String[] values) {
    super(context, R.layout.activity_main, values);
    this.context = context;
    this.values = values;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.onerow, parent, false);
    TextView textView = (TextView) rowView.findViewById(R.id.secondLine);
    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
    textView.setText(values[position]);
    // Change the icon for Windows and iPhone
    String s = values[position];
    if (s.startsWith("iPhone")) {
      imageView.setImageResource(R.drawable.download);
    } else {
      imageView.setImageResource(R.drawable.ic_launcher);
    }

    return rowView;
  }
} 