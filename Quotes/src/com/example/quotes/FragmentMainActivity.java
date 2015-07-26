
	package com.example.quotes;

	import android.support.v4.app.FragmentActivity;
	import android.support.v7.app.ActionBarActivity;
	import android.content.Intent;
	import android.os.Bundle;
	import android.view.Menu;
	import android.view.MenuItem;

	public class FragmentMainActivity extends FragmentActivity {
		public String id;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			Intent intent=getIntent();
			String id_val = intent.getExtras().getString("id");
			System.out.println("id_val"+id_val);
			this.setId(id_val);
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


