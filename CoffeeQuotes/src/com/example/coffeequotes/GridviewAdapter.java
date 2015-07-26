package com.example.coffeequotes;






import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridviewAdapter extends BaseAdapter {
	 private Context mcontext;
	 
	public GridviewAdapter(Context context) {
		super();
		this.mcontext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return picId.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewHolder view;
        if(convertView==null)
        {
            view = new ViewHolder();
            convertView = inflater.inflate(R.layout.gridview_row, null);
 
            view.txtView1 = (TextView) convertView.findViewById(R.id.grid_item_label);
            view.imgView1= (ImageView) convertView.findViewById(R.id.grid_item_image);
            convertView.setTag(view);
        }
        else
        {
            view = (ViewHolder) convertView.getTag();
        }
        view.txtView1.setText(textvalue[position]);
        view.imgView1.setImageResource(picId[position]);
  
        return convertView;
	}
	 public static class ViewHolder
	    {
	        public ImageView imgView1;
	        public TextView txtView1;
	    
	        
	    }
	 
	 public Integer[] picId={
				R.drawable.vintage,
				R.drawable.latest
				
		};

	  public String[]  textvalue={"Vintage Quotes" ,
	  		"Latest Quotes"};
}
