package com.example.cquotes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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
	private String year = null;

	public CustomListAdapter(Context context,
			List<HashMap<String, Object>> listData, String year) {
		this.listData = listData;
		layoutInflater = LayoutInflater.from(context);
		this.year = year;
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

			if (year.equals("2000")) {
				convertView = layoutInflater.inflate(R.layout.listview_row_20,
						null);
				holder = new ViewHolder();
				holder.name = (TextView) convertView.findViewById(R.id.Txt);
				holder.active = (TextView) convertView
						.findViewById(R.id.active);
				holder.imageView = (ImageView) convertView
						.findViewById(R.id.thumbnail);
			} else if (year.equals("1960")) {
				convertView = layoutInflater.inflate(R.layout.listview_row,
						null);
				holder = new ViewHolder();
				holder.name = (TextView) convertView.findViewById(R.id.Txt);
				holder.active = (TextView) convertView
						.findViewById(R.id.active);
				holder.imageView = (ImageView) convertView
						.findViewById(R.id.thumbnail);
			} else {
				convertView = layoutInflater.inflate(
						R.layout.listview_row_film, null);
				holder = new ViewHolder();
				holder.name = (TextView) convertView.findViewById(R.id.Txt);
				holder.starring = (TextView) convertView
						.findViewById(R.id.starring);
				holder.imageView = (ImageView) convertView
						.findViewById(R.id.thumbnail);
			}

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		/* ListData list=(ListData) getItem(position); */
		@SuppressWarnings("unchecked")
		HashMap<String, Object> hm = (HashMap<String, Object>) getItem(position);
		if (year.equals("2000")) {
			System.out.println("***********" + hm.get("Txt"));
			holder.name.setText((String) hm.get("Txt"));
			holder.active.setText((String) hm.get("Active"));
			if (holder.imageView != null) {
				holder.imageView.setImageBitmap((Bitmap) hm.get("thumbnail"));
			}
		} else if (year.equals("1960")) {
			System.out.println("***********" + hm.get("Txt"));
			holder.name.setText((String) hm.get("Txt"));
			holder.active.setText((String) hm.get("Active"));
			if (holder.imageView != null) {
				holder.imageView.setImageBitmap((Bitmap) hm.get("thumbnail"));
			}
		} else {
			System.out.println("***********" + hm.get("Txt"));
			holder.name.setText((String) hm.get("Txt"));
			holder.starring.setText((String) hm.get("starring"));
			if (holder.imageView != null) {
				holder.imageView.setImageBitmap((Bitmap) hm.get("thumbnail"));
			}
		}

		return convertView;
	}

	static class ViewHolder {
		TextView name;
		TextView active;
		ImageView imageView;
		TextView starring;
	}

}