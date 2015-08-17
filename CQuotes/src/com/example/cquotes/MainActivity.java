package com.example.cquotes;

import java.util.concurrent.ExecutionException;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.Tab;
import android.support.v4.view.ViewPager;

@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity {
	private ActionBar actionBar;
	private static int SPLASH_TIME_OUT = 3000;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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

			try {
				RetrievieValues retrievieValues = new RetrievieValues();
				retrievieValues.execute("Test").get();
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						Intent i = new Intent(MainActivity.this,
								TabMainActivity.class);
						startActivity(i);
						finish();

					}
				}, SPLASH_TIME_OUT);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
