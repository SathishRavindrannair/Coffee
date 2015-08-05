package com.example.cquotes;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FragmentMainActivity extends FragmentActivity {
	private String id;
	private String year;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent=getIntent();
		String id_val = intent.getExtras().getString("id");
		String year = intent.getExtras().getString("year");
		System.out.println("id_val"+id_val);
		this.setId(id_val);
		this.setYear(year);
		setContentView(R.layout.activity_fragment);
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, 
               menu);
		return true;
	}
	
}
