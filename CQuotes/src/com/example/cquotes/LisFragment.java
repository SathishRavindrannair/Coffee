package com.example.cquotes;

import java.util.HashMap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ListActivity;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

public class LisFragment extends ListFragment {

	String[] quotes;
	PopupWindow pwindo;
	PopupWindow psharewindow;
	String contents;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.listfragment_layout, container,
				false);
		QuotesConstants constants = QuotesConstants.getInstance();
		FragmentMainActivity activity = (FragmentMainActivity) getActivity();
		System.out.println("******************" + activity.getId()
				+ "***********" + activity.getYear());

		if (activity.getYear().toString().equals("1960")) {
			HashMap<String, LeaderHelper> hashMap = constants.hashMap;
			for (String val : hashMap.keySet()) {
				System.out.println("**************" + val);
				LeaderHelper helper = hashMap.get(val);
				if (helper.getId().equals(activity.getId())) {
					quotes = new String[helper.getQuotes().length];
					quotes = helper.getQuotes();
				}

			}
		} else if (activity.getYear().toString().equals("2000")) {
			HashMap<String, LeaderHelper> hashMap = constants.hashMap20;
			for (String val : hashMap.keySet()) {
				LeaderHelper helper = hashMap.get(val);
				if (helper.getId().equals(activity.getId())) {
					quotes = new String[helper.getQuotes().length];
					quotes = helper.getQuotes();
				}

			}
		} else {
			HashMap<String, FilmyHelper> hashMap = constants.hashMapfilmy;
			for (String val : hashMap.keySet()) {
				FilmyHelper helper = hashMap.get(val);
				if (helper.getId().equals(activity.getId())) {
					quotes = new String[helper.getQuotes().length];
					quotes = helper.getQuotes();
				}

			}
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, quotes);
		setListAdapter(adapter);

		return view;

	}

	/*
	 * @Override public void onListItemClick(ListView l, View v, int position,
	 * long id) { AlertDialog.Builder builder = new
	 * AlertDialog.Builder(getActivity()); builder.setMessage(quotes[position])
	 * .setCancelable(false) .setPositiveButton("Close", new
	 * DialogInterface.OnClickListener() { public void onClick(DialogInterface
	 * dialog, int id) { // if this button is clicked, close // current activity
	 * dialog.cancel(); } });
	 * 
	 * // create alert dialog AlertDialog alertDialog = builder.create();
	 * 
	 * // show it alertDialog.show(); }
	 */
	@Override
	public void onListItemClick(ListView l, View view, int position, long id) {

		LayoutInflater inflater = (LayoutInflater) view.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.popupwindow_xml,
				(ViewGroup) view.findViewById(R.id.popup));
		pwindo = new PopupWindow(layout, 400, 300, true);
	    contents=quotes[position];
		pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
		TextView textView = (TextView) layout.findViewById(R.id.dialog_info);
		textView.setText(contents);
		Button btnClosePopup = (Button) layout.findViewById(R.id.dialog_cancel);
		btnClosePopup.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pwindo.dismiss();
			}
		});
		Button shareContent = (Button) layout.findViewById(R.id.share_dialog);
		shareContent.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				LayoutInflater inflater = (LayoutInflater) view.getContext()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View layout = inflater.inflate(R.layout.sharepopup,
						(ViewGroup) view.findViewById(R.id.sharepopup));
				psharewindow = new PopupWindow(layout, 400, 300, true);
				psharewindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
				ImageView facebook = (ImageView) layout
						.findViewById(R.id.facebook);
				ImageView whatsapp = (ImageView) layout
						.findViewById(R.id.whatsapp);
				facebook.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						psharewindow.dismiss();

					}
				});
				whatsapp.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View view) {
						PackageManager pm = getActivity().getPackageManager();
						try {

							Intent waIntent = new Intent(Intent.ACTION_SEND);
							waIntent.setType("text/plain");
							String text =contents;

							PackageInfo info = pm.getPackageInfo(
									"com.whatsapp",
									PackageManager.GET_META_DATA);
							// Check if package exists or not. If not then code
							// in catch block will be called
							waIntent.setPackage("com.whatsapp");

							waIntent.putExtra(Intent.EXTRA_TEXT, text);
							startActivity(Intent.createChooser(waIntent,
									"Share with"));

						} catch (NameNotFoundException e) {
							Toast.makeText(getActivity(),
									"WhatsApp not Installed",
									Toast.LENGTH_SHORT).show();
						}

					}
				});
			}
		});

	}
}
