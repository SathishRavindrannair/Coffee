package com.example.cquotes;

import java.util.HashMap;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.image_fragment, container, false);
		QuotesConstants constants = QuotesConstants.getInstance();
		FragmentMainActivity activity = (FragmentMainActivity) getActivity();
		System.out.println("******************" + activity.getId()+ "***********" + activity.getYear());
		ImageAdapter imageAdapter = null;
		if (activity.getYear().toString().equals("1960")) {
			HashMap<String, LeaderHelper> hashMap = constants.hashMap;
			System.out.println("*******Hashmap*********" + hashMap);
			imageAdapter = new ImageAdapter(view.getContext(), hashMap);
		} else if (activity.getYear().toString().equals("2000")) {
			HashMap<String, LeaderHelper> hashMap = constants.hashMap20;
			System.out.println("*******Hashmap*********" + hashMap);
			imageAdapter = new ImageAdapter(view.getContext(), hashMap);
		} else {
			HashMap<String, FilmyHelper> hashMap = constants.hashMapfilmy;
			System.out.println("*******Hashmap*********" + hashMap);
			imageAdapter = new ImageAdapter(view.getContext(), hashMap,activity.getYear());
		}

		ImageView imageView = (ImageView) view.findViewById(R.id.SimpleView);
		imageView.setImageBitmap(imageAdapter.picId[Integer.parseInt(activity.getId())]);
		return view;

	}
}
