package com.example.androidtablayout;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ParsingActivityAdapter extends ArrayAdapter<JSONitem> {

	public static List<JSONitem> mList = new ArrayList<JSONitem>();
	private ImageLoader imageLoader;
	Activity mContext;
	LayoutInflater inflater;
	JSONitem item;
	Drawable drawable;
	String image;
	
	public ParsingActivityAdapter(Activity context, List<JSONitem> items) {
		super(context, 0, items);
		mList = items;
		imageLoader = new ImageLoader(context);
		mContext = context;
		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.parsing_activity_list_row, null);

			holder = new ViewHolder();
			holder.mLayout = (RelativeLayout) convertView
					.findViewById(R.id.linearLay_main);
			holder.imgPhotoThumb = (ImageView) convertView
					.findViewById(R.id.imgView);
			holder.txt_aname = (TextView) convertView.findViewById(R.id.title);
			holder.txt_cname = (TextView) convertView
					.findViewById(R.id.subtitle);
			holder.cprice = (TextView) convertView.findViewById(R.id.desi);
			holder.progressBar = (ProgressBar) convertView
					.findViewById(R.id.progressBar1);
			holder.progressBar.setVisibility(View.GONE);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		item = mList.get(position);
		holder.txt_aname.setText(item.getId().toString());
		holder.txt_cname.setText(item.getTitle());
		holder.cprice.setText(item.getDesignation());
		holder.imgPhotoThumb.setTag(item.getLargeimage().toString());
		imageLoader.DisplayImage(item.getLargeimage().toString().trim(),
				mContext, holder.imgPhotoThumb);

		if (position % 2 == 0) {

			holder.mLayout.setBackgroundColor(Color.LTGRAY);

		} else {
			holder.mLayout.setBackgroundColor(Color.WHITE);
		}
//		convertView.setOnClickListener(new OnClickListener() {

//			@Override
//			public void onClick(View v) {
				// TODO Auto-generated method stub
				
//				Intent intent = new Intent(mContext,PhotoGridActivity.class);
//				item = mList.get(position);
//				intent.putExtra("IMAGE",item.getLargeimage());
//				image = item.getLargeimage();
//				intent.putExtra("SIZE", ""+mList.size());
//				intent.putExtra("IMAGE", myList.);
//				mContext.startActivity(intent);

//			}
//		});

		return convertView;
	}

	static class ViewHolder {
		private TextView txt_aname, txt_cname, cprice;
		private ImageView imgPhotoThumb;
		RelativeLayout mLayout;
		private ProgressBar progressBar;

	}
}