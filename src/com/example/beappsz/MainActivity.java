package com.example.beappsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final ListView listview = (ListView) findViewById(R.id.listviewmain);
		String[][] textvalues = new String[][] { {"Beer1", "Beer2", "Beer3",
				"MoreBeer", "Etc" }, { "pretty good", "hoppy", "not my thing",
					"yay", "are you still reading this?" }};

		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < textvalues.length; ++i) {
			list.add(textvalues[i]);
		}
//		final StableArrayAdapter adapter = new StableArrayAdapter(this,
//				android.R.layout.simple_list_item_1, list);
		final CustomArrayAdapter adapter = new CustomArrayAdapter(this, textvalues);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				final String item = (String) parent.getItemAtPosition(position);
				view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
					@Override
					public void run() {
						//warning, this is not doing anything because I'm using the strings directly and not the array list
						list.remove(item);
						adapter.notifyDataSetChanged();
						view.setAlpha(1);
					}
				});
			}
		});

		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						"Click ListItem Number " + position, Toast.LENGTH_LONG)
						.show();
			}
		}); 
	}

	private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}
	}
	
	public class CustomArrayAdapter extends ArrayAdapter<String> {
		  private final Context context;
		  private final String[] names;
		  private final String[] descriptions;

		  public CustomArrayAdapter(Context context, String[][] textvalues) {
		    super(context, R.layout.activity_main);
		    this.context = context;
		    this.names = textvalues[1];
		    this.descriptions = textvalues[2];
		  }

		  @Override
		  public View getView(int position, View convertView, ViewGroup parent) {
		    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    View rowView = inflater.inflate(R.layout.onerow, parent, false);
		    TextView textNames = (TextView) rowView.findViewById(R.id.textNames);
		    TextView textDescr = (TextView) rowView.findViewById(R.id.textDescriptions);
		    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		    textNames.setText(names[position]);
		    textDescr.setText(descriptions[position]);
		    // Change the icon for Windows and iPhone
		    String s = names[position];
		    if (s.startsWith("iPhone")) {
		      imageView.setImageResource(R.drawable.download);
		    } else {
		      imageView.setImageResource(R.drawable.ic_launcher);
		    }

		    return rowView;
		  }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
