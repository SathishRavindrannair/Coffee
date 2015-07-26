package com.example.quotes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomListAdapter extends BaseAdapter {
	private List<HashMap<String, Object>> listData = new ArrayList<HashMap<String, Object>>();
	private LayoutInflater layoutInflater;

	public CustomListAdapter(Context context, List<HashMap<String, Object>> listData) {
		this.listData = listData;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.listview_layout, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.Txt);
			holder.active = (TextView) convertView.findViewById(R.id.active);
			holder.imageView = (ImageView) convertView.findViewById(R.id.thumbnail);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		/*ListData list=(ListData) getItem(position);*/
		@SuppressWarnings("unchecked")
		HashMap<String, Object> hm=(HashMap<String, Object>) getItem(position);
			
			System.out.println("***********"+hm.get("Txt"));
			holder.name.setText((String)hm.get("Txt"));
			holder.active.setText((String) hm.get("Active"));
			if (holder.imageView != null) {
				holder.imageView.setImageBitmap((Bitmap) hm.get("thumbnail"));
			}
		
		
		return convertView;
	}

	static class ViewHolder {
		TextView name;
		TextView active;
		ImageView imageView;
	}
}