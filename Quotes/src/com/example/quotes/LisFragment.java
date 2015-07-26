package com.example.quotes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ListActivity;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

public class LisFragment extends ListFragment {

	String[] quotes = { "Customer is King" };

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.listfragment_layout, container,
				false);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, quotes);
		setListAdapter(adapter);

		return view;

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(quotes[position])
				.setCancelable(false)
				.setPositiveButton("Close",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// if this button is clicked, close
								// current activity
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = builder.create();

		// show it
		alertDialog.show();
	}

}
