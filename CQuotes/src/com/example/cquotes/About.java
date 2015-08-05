package com.example.cquotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
public class About extends Fragment {
 
	private GridView gridView;
	private GridviewAdapter gadapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 

        View rootView = inflater.inflate(R.layout.about_layout_xml, container, false);
        gridView = (GridView)rootView.findViewById(R.id.gridView1);
		gridView.setAdapter(new GridviewAdapter(rootView.getContext()));
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				System.out.println(position);
				if (position == 0) {
					Intent i = new Intent(getActivity(),ListViewActivity90.class);
					startActivity(i);
				} else if (position == 1) {
					Intent i = new Intent(getActivity(),ListViewActivity20.class);
					startActivity(i);
				}
			}
		});

         
        return rootView;
    }
}
