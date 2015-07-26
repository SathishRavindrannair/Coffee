package com.example.quotes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import android.support.v7.app.ActionBarActivity;
import android.text.Layout;
import android.util.Log;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView.Validator;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Integer[] id = { R.drawable.mahatma, R.drawable.che, R.drawable.abe };
		String[] displayString = { "Mahatma Gandhi", "Che Guevara",
				"Abraham Lincoln" };
		String[] active = { "October 2,1869 - Janaury 30,1948",
				"June 14,1928 - October 9,1967",
				"February 12,1809 - April 15,1865" };
		RetrievieValues retrievieValues=new RetrievieValues();
		ArrayList<Bitmap> val=null;
	try {
		val=retrievieValues.execute("Test").get();
		System.out.println(val);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
		final List<HashMap<String,Object>> aList = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < displayString.length; i++) {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("thumbnail",(Bitmap)val.get(i));
			hm.put("Txt", displayString[i]);
			hm.put("Active", active[i]);
			hm.put("id", new Integer(i).toString());
			aList.add(hm);
		}
		int[] to = new int[] { R.id.thumbnail, R.id.Txt, R.id.active };
		String[] from = { "thumbnail", "Txt", "Active" };
		ListView listView = (ListView) findViewById(R.id.listView);
		/*SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
				aList, R.layout.listview_layout, from, to);
		listView.setAdapter(adapter);*/
		System.out.println("List Adapter"+aList.toString());
		listView.setAdapter(new CustomListAdapter(this, aList));
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(MainActivity.this,
						FragmentMainActivity.class);
				System.out.println("*****"+arg2+"******"+aList.get(arg2).get("id").toString());
				i.putExtra("id", aList.get(arg2).get("id").toString());
				startActivity(i);
			}
		}

		);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
