package com.example.cquotes;

import java.util.HashMap;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ContentFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.content_fragment, container,
				false);
		TextView textView = (TextView) view.findViewById(R.id.textView);
		QuotesConstants constants = QuotesConstants.getInstance();
		FragmentMainActivity activity = (FragmentMainActivity) getActivity();
		System.out.println("******************" + activity.getId()
				+ "***********" + activity.getYear());
		
		String content = "";

		if (activity.getYear().toString().equals("1960")) {
			HashMap<String, LeaderHelper> hashMap = constants.hashMap;

			for (String val : hashMap.keySet()) {
				System.out.println("**************" + val);
				LeaderHelper helper = hashMap.get(val);
				if (helper.getId().equals(activity.getId())) {
					content = helper.getContent();
				}

			}
		} else if(activity.getYear().toString().equals("2000")){
			HashMap<String, LeaderHelper> hashMap  = constants.hashMap20;
			for (String val : hashMap.keySet()) {
				LeaderHelper helper = hashMap.get(val);
				if (helper.getId().equals(activity.getId())) {
					content = helper.getContent();
				}

			}
		}else{
			HashMap<String, FilmyHelper> hashMap  = constants.hashMapfilmy;
			for (String val : hashMap.keySet()) {
				FilmyHelper helper = hashMap.get(val);
				if (helper.getId().equals(activity.getId())) {
					content = helper.getContent();
				}

			}
		}
        textView.setText(content);
		return view;

	}
}
