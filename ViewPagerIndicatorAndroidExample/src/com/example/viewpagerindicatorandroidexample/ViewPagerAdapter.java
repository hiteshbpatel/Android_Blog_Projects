package com.example.viewpagerindicatorandroidexample;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewPagerAdapter extends PagerAdapter {
	// Declare Variables
	Context context;
	String[] baby_number;
	String[] baby_name;
	String[] baby_age;
	int[] baby_image;
	LayoutInflater inflater;

	public ViewPagerAdapter(Context context, String[] baby_number, String[] baby_name,
			String[] baby_age, int[] baby_image) {
		this.context = context;
		this.baby_number = baby_number;
		this.baby_name = baby_name;
		this.baby_age = baby_age;
		this.baby_image = baby_image;
	}

	@Override
	public int getCount() {
		return baby_number.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((RelativeLayout) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		// Declare Variables
		TextView tv_babyNumber;
		TextView tv_babyName;
		TextView tv_babyAge;
		ImageView iv_babyImage;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.viewpager_item, container,
				false);

		// Locate the TextViews in viewpager_item.xml
		tv_babyNumber = (TextView) itemView.findViewById(R.id.tv_Number);
		tv_babyName = (TextView) itemView.findViewById(R.id.tv_Name);
		tv_babyAge = (TextView) itemView.findViewById(R.id.tv_Age);

		// Capture position and set to the TextViews
		tv_babyNumber.setText(baby_number[position]);
		tv_babyName.setText(baby_name[position]);
		tv_babyAge.setText(baby_age[position]);

		// Locate the ImageView in viewpager_item.xml
		iv_babyImage = (ImageView) itemView.findViewById(R.id.iv_Image);
		// Capture position and set to the ImageView
		iv_babyImage.setImageResource(baby_image[position]);

		// Add viewpager_item.xml to ViewPager
		((ViewPager) container).addView(itemView);

		return itemView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// Remove viewpager_item.xml from ViewPager
		((ViewPager) container).removeView((RelativeLayout) object);

	}
}
