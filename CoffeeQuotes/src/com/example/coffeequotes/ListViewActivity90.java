package com.example.coffeequotes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class ListViewActivity90 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_activity90);
		HashMap<String, LeaderHelper> val=null;
		QuotesConstants constants=QuotesConstants.getInstance();
		if(constants.hashMap!=null){
		 val=constants.hashMap;
		}else{
			RetrievieValues retrievieValues=new RetrievieValues();
		try {
			retrievieValues.execute("QuotesFrom90").get();
			 val=constants.hashMap;
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
		int i=0;
		for (String key:val.keySet()) {
			LeaderHelper leaderHelper=val.get(key);
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("thumbnail",(Bitmap)leaderHelper.getImage_url());
			hm.put("Txt", leaderHelper.getLeader_name());
			hm.put("Active", leaderHelper.getActive());
			hm.put("id", leaderHelper.getId());
			aList.add(hm);
			i++;
		}
		ListView listView = (ListView) findViewById(R.id.listView);
		System.out.println("List Adapter"+aList.toString());
		listView.setAdapter(new CustomListAdapter(this, aList,"1960"));
		/*********************For Header *******************************************/
		LayoutInflater inflater = getLayoutInflater();
		ViewGroup headerview=(ViewGroup) inflater.inflate(R.layout.header, listView,false);
		LinearLayout layout=(LinearLayout) headerview.findViewById(R.id.linearlayout);
		TextView headerText=(TextView) headerview.findViewById(R.id.header);
		headerText.setText("Welcome To Vintage Era");
		listView.addHeaderView(headerview,null,false);
		
		/********************* Footer*******************************************/
		ViewGroup footerView=(ViewGroup) inflater.inflate(R.layout.footer, listView,false);
		TextView footerText=(TextView) footerView.findViewById(R.id.footer);
		footerText.setText("End");
		listView.addFooterView(footerView,null,false);
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(ListViewActivity90.this,
						FragmentMainActivity.class);
				System.out.println("*****"+arg2+"******"+aList.get(arg2).get("id").toString());
				i.putExtra("id", aList.get(arg2).get("id").toString());
				i.putExtra("year","1960");
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
