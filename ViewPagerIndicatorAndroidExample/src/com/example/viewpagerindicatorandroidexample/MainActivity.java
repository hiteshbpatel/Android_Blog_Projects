package com.example.viewpagerindicatorandroidexample;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import com.viewpagerindicator.UnderlinePageIndicator;

public class MainActivity extends Activity {

	// Declare Variables
	ViewPager viewPager;
	PagerAdapter adapter;
	String[] baby_number;
	String[] baby_name;
	String[] baby_age;
	int[] baby_image;
	UnderlinePageIndicator mIndicator;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from activity_main.xml
		setContentView(R.layout.activity_main);

		// Generate sample data
		baby_number = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

		baby_name = new String[] { "Andrew", "Benjamin", "Christopher",
				"Daniel", "Ethan", "Isabella", "Joseph", "Lily",
				"Mathew", "Michael" };

		baby_age = new String[] { "1.2 yr", "2.3 yr",
				"1.7 yr", "2 yr", "2.5 yr", "1.6 yr",
				"7 months", "2.6 yr", "1.9 yr", "2.6 yr" };

		baby_image = new int[] { R.drawable.andrew, R.drawable.benjamin,
				R.drawable.christopher, R.drawable.daniel,
				R.drawable.ethan, R.drawable.isabella, R.drawable.joseph,
				R.drawable.lily, R.drawable.matthew, R.drawable.michael };

		// Locate the ViewPager in activity_main.xml
		viewPager = (ViewPager) findViewById(R.id.view_pager);
		// Pass results to ViewPagerAdapter Class
		adapter = new ViewPagerAdapter(MainActivity.this, baby_number, baby_name,
				baby_age, baby_image);
		// Binds the Adapter to the ViewPager
		viewPager.setAdapter(adapter);

		// ViewPager Indicator
		mIndicator = (UnderlinePageIndicator) findViewById(R.id.page_indicator);
		mIndicator.setFades(false);
		mIndicator.setViewPager(viewPager);

	}
}