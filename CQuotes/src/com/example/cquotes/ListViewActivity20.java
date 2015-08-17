package com.example.cquotes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ListViewActivity20 extends ActionBarActivity implements
		OnQueryTextListener {
	private ActionBar actionBar;
	private ListView listView;
	private CustomListAdapter customListAdapter;
	private List<HashMap<String, Object>> searchList;
	private List<HashMap<String, Object>> aList;
	private SearchView searchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_actvity20);
		actionBar = getSupportActionBar();
		actionBar.show();
		HashMap<String, LeaderHelper> val = null;
		QuotesConstants constants = QuotesConstants.getInstance();
		if (constants.hashMap20 != null) {
			val = constants.hashMap20;
		} else {
			RetrievieValues retrievieValues = new RetrievieValues();
			try {
				retrievieValues.execute("QuotesFrom20").get();
				val = constants.hashMap20;
				System.out.println(val);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		aList = new ArrayList<HashMap<String, Object>>();
		int i = 0;
		for (String key : val.keySet()) {
			LeaderHelper leaderHelper = val.get(key);
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("thumbnail", (Bitmap) leaderHelper.getImage_url());
			hm.put("Txt", leaderHelper.getLeader_name());
			hm.put("Active", leaderHelper.getActive());
			hm.put("id", leaderHelper.getId());
			aList.add(hm);
			i++;
		}
		listView = (ListView) findViewById(R.id.listView20);
		System.out.println("List Adapter" + aList.toString());
		customListAdapter = new CustomListAdapter(this, aList, "2000");
		listView.setAdapter(customListAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(ListViewActivity20.this,
						FragmentMainActivity.class);
				System.out.println("*****" + arg2 + "******"
						+ aList.get(arg2).get("id").toString());
				i.putExtra("id", aList.get(arg2).get("id").toString());
				i.putExtra("year", "2000");
				startActivity(i);
				finish();
			}
		}

		);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view_actvity20, menu);
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		searchView = (SearchView) menu.findItem(R.id.menu_item_search_20)
				.getActionView();

		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		searchView.setSubmitButtonEnabled(true);
		searchView.setOnQueryTextListener(this);

		return super.onCreateOptionsMenu(menu);

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

	@Override
	public boolean onQueryTextChange(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		searchList = new ArrayList<HashMap<String, Object>>();
		if (TextUtils.isEmpty(query)) {
			listView.clearTextFilter();
			listView = (ListView) findViewById(R.id.listView20);
			System.out.println("List Adapter onquery" + aList.toString());
			customListAdapter = new CustomListAdapter(this, aList, "2000");
			listView.setAdapter(customListAdapter);
			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Intent i = new Intent(ListViewActivity20.this,
							FragmentMainActivity.class);
					System.out.println("*****" + arg2 + "******"
							+ aList.get(arg2).get("id").toString());
					i.putExtra("id", aList.get(arg2).get("id").toString());
					i.putExtra("year", "2000");
					startActivity(i);
					finish();
				}
			}

			);
			return false;
		} else {
			listView.setFilterText(query.toString());
			QuotesConstants constants = QuotesConstants.getInstance();
			HashMap<String, LeaderHelper> val = constants.hashMap20;
			for (String key : val.keySet()) {
				LeaderHelper leaderHelper = val.get(key);
				if (leaderHelper.getLeader_name().contains(query)) {
					HashMap<String, Object> hm = new HashMap<String, Object>();
					hm.put("thumbnail", (Bitmap) leaderHelper.getImage_url());
					hm.put("Txt", leaderHelper.getLeader_name());
					hm.put("Active", leaderHelper.getActive());
					hm.put("id", leaderHelper.getId());
					searchList.add(hm);
				}

			}
			if (searchList.size() == 0) {
				searchList = aList;
			}
			listView = (ListView) findViewById(R.id.listView20);
			System.out.println("List Adapter search" + searchList.toString());
			customListAdapter = new CustomListAdapter(this, searchList, "2000");
			listView.setAdapter(customListAdapter);
			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Intent i = new Intent(ListViewActivity20.this,
							FragmentMainActivity.class);
					System.out.println("*****" + arg2 + "******"
							+ searchList.get(arg2).get("id").toString());
					i.putExtra("id", searchList.get(arg2).get("id").toString());
					i.putExtra("year", "2000");
					startActivity(i);
					finish();
				}
			}

			);
			listView.clearTextFilter();
			return true;
		}

	}
}
