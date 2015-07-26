package com.example.quotes;

import java.util.HashMap;

import com.example.quotes.*;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageFragment extends Fragment{
	
	@Override
	 
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
 
        View view = inflater.inflate(R.layout.image_fragment, container, false);
        FragmentMainActivity activity=(FragmentMainActivity) getActivity();
	    System.out.println("******************"+activity.getId());
        QuotesConstants constants=QuotesConstants.getInstance();
        HashMap<String,LeaderHelper> hashMap=constants.hashMap;
        System.out.println("*******Hashmap*********"+hashMap);
		ImageAdapter imageAdapter=new ImageAdapter(view.getContext(),hashMap);
		ImageView imageView=(ImageView)view.findViewById(R.id.SimpleView);
		imageView.setImageBitmap(imageAdapter.picId[Integer.parseInt(activity.getId())]);
        return view;
 
    }
}
