package com.example.coffeequotes;

import java.util.HashMap;
import java.util.Set;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context context;
	public Bitmap[] picId = null;

	public ImageAdapter(Context context, HashMap<String, LeaderHelper> hashMap) {
		this.context = context;
		int i = 0;
		picId = new Bitmap[hashMap.size()];
		for (String val : hashMap.keySet()) {
			LeaderHelper helper = hashMap.get(val);
			picId[i] = helper.getImage_url();
			i++;
		}

	}

	public ImageAdapter(Context context2, HashMap<String, FilmyHelper> hashMap,
			String year) {
		// TODO Auto-generated constructor stub
		this.context = context2;
		int i = 0;
		picId = new Bitmap[hashMap.size()];
		for (String val : hashMap.keySet()) {
			FilmyHelper helper = hashMap.get(val);
			picId[i] = helper.getImage_url();
			i++;
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return picId.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return picId[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
		}
		imageView.setImageBitmap((Bitmap) getItem(position));

		// TODO Auto-generated method stub
		return imageView;
	}

}
