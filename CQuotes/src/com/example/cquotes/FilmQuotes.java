package com.example.cquotes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
public class FilmQuotes extends Fragment {
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.film_quotes_xml, container, false);
        ImageView img = (ImageView) rootView.findViewById(R.id.filmy);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent(getActivity(),FilmMainActivity.class);
				startActivity(i);
            }
        });
        return rootView;
    }
   
    
}
