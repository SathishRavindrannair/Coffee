package com.example.cquotes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FilmMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_film_main);
		HashMap<String, FilmyHelper> val=null;
		QuotesConstants constants=QuotesConstants.getInstance();
		if(constants.hashMapfilmy !=null){
		 val=constants.hashMapfilmy;
		}else{
			RetrievieValues retrievieValues=new RetrievieValues();
		try {
			retrievieValues.execute("FilmQuotes").get();
			 val=constants.hashMapfilmy;
			System.out.println(val);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		final List<HashMap<String,Object>> aList = new ArrayList<HashMap<String, Object>>();
		for (String key:val.keySet()) {
			FilmyHelper filmyHelper=val.get(key);
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("thumbnail",(Bitmap)filmyHelper.getImage_url());
			hm.put("Txt", filmyHelper.getMovie_name());
			hm.put("starring", filmyHelper.getStarring());
			hm.put("id", filmyHelper.getId());
			aList.add(hm);
		}
		ListView listView = (ListView) findViewById(R.id.listViewfilm);
		System.out.println("List Adapter"+aList.toString());
		listView.setAdapter(new CustomListAdapter(this, aList,"0000"));
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(FilmMainActivity.this,
						FragmentMainActivity.class);
				System.out.println("*****"+arg2+"******"+aList.get(arg2).get("id").toString());
				i.putExtra("id", aList.get(arg2).get("id").toString());
				i.putExtra("year","0000");
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
