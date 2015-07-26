package com.example.coffeequotes;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

	private ProgressDialog progressDialog;
	private GridView gridView;
	private GridviewAdapter gadapter;
	private LoadViewTask loadViewTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ConnectivityClass connectivityClass = new ConnectivityClass(
				getApplicationContext());
		System.out.println("checkConnectivity" + connectivityClass);
		if (!connectivityClass.isConnectingToInternet()) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Connect to wifi or quit")
					.setCancelable(false)
					.setPositiveButton("Connect to WIFI",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									startActivity(new Intent(
											Settings.ACTION_WIFI_SETTINGS));
								}
							})
					.setNegativeButton("Quit",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									finish();
								}
							});
			AlertDialog alert = builder.create();
			alert.show();

		} else {
			loadViewTask = new LoadViewTask();
			loadViewTask.execute();
		}
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

	private class LoadViewTask extends AsyncTask<Void, Integer, Void> {
		// Before running code in separate thread
		@Override
		protected void onPreExecute() {
			// Create a new progress dialog
			try {

				RetrievieValues retrievieValues = new RetrievieValues();
				retrievieValues.execute("Test").get();

			} catch (Exception ex) {
				System.out.println("Exception in LoadViewTask" + ex.toString());
			}
			progressDialog = new ProgressDialog(MainActivity.this);
			// Set the progress dialog to display a horizontal progress bar
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			// Set the dialog title to 'Loading...'
			progressDialog.setTitle("Loading...");
			// Set the dialog message to 'Loading application View, please
			// wait...'
			progressDialog
					.setMessage("Loading application View, please wait...");
			// This dialog can't be canceled by pressing the back key
			progressDialog.setCancelable(false);
			// This dialog isn't indeterminate
			progressDialog.setIndeterminate(false);
			// The maximum number of items is 100
			progressDialog.setMax(100);
			// Set the current progress to zero
			progressDialog.setProgress(0);
			// Display the progress dialog
			progressDialog.show();
		}

		// The code to be executed in a background thread.
		@Override
		protected Void doInBackground(Void... params) {
			/*
			 * This is just a code that delays the thread execution 4 times,
			 * during 850 milliseconds and updates the current progress. This is
			 * where the code that is going to be executed on a background
			 * thread must be placed.
			 */
			try {
				// Get the current thread's token
				synchronized (this) {
					// Initialize an integer (that will act as a counter) to
					// zero
					int counter = 0;
					// While the counter is smaller than four
					while (counter <= 4) {
						// Wait 850 milliseconds
						this.wait(850);
						// Increment the counter
						counter++;
						// Set the current progress.
						// This value is going to be passed to the
						// onProgressUpdate() method.
						publishProgress(counter * 25);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		// Update the progress
		@Override
		protected void onProgressUpdate(Integer... values) {
			// set the current progress of the progress dialog
			progressDialog.setProgress(values[0]);
		}

		// after executing the code in the thread
		@Override
		protected void onPostExecute(Void result) {
			// close the progress dialog
			progressDialog.dismiss();
			// initialize the View
			setContentView(R.layout.activity_main);
			gridView = (GridView) findViewById(R.id.gridView1);
			gridView.setAdapter(new GridviewAdapter(getApplicationContext()));
			gridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					System.out.println(position);
					if (position == 0) {
						Intent i = new Intent(MainActivity.this,
								ListViewActivity90.class);
						startActivity(i);
					} else if (position == 1) {
						Intent i = new Intent(MainActivity.this,
								ListViewActivity20.class);
						startActivity(i);
					}
				}
			});

		}
	}

}
