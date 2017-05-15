package com.example.androidpulltorefreshexample;

import java.util.Arrays;
import java.util.LinkedList;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

public class PullToRefreshGridViewActivity extends Activity {
	
	private LinkedList<String> mListItems;
	private PullToRefreshGridView mPullRefreshGridView;
	private GridView mGridView;
	private ArrayAdapter<String> mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pull_to_refresh_grid_view);
		
		mPullRefreshGridView = (PullToRefreshGridView) findViewById(R.id.pull_to_refresh_gridview);
		mGridView = mPullRefreshGridView.getRefreshableView();

		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshGridView.setOnRefreshListener(new OnRefreshListener2<GridView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
				Toast.makeText(PullToRefreshGridViewActivity.this, "Pull Down!", Toast.LENGTH_SHORT).show();
				new GetDataTask().execute();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
				Toast.makeText(PullToRefreshGridViewActivity.this, "Pull Up!", Toast.LENGTH_SHORT).show();
				new GetDataTask().execute();
			}

		});

		mListItems = new LinkedList<String>();

		TextView tv_emptyview = new TextView(this);
		tv_emptyview.setGravity(Gravity.CENTER);
		tv_emptyview.setText("Empty View, Pull Down/Up to Add Items");
		mPullRefreshGridView.setEmptyView(tv_emptyview);

		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);
		mGridView.setAdapter(mAdapter);
	}

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			return mStrings;
		}

		@Override
		protected void onPostExecute(String[] result) {
			mListItems.addFirst("Added after refresh...");
			mListItems.addAll(Arrays.asList(result));
			mAdapter.notifyDataSetChanged();

			// Call onRefreshComplete when the list has been refreshed.
			mPullRefreshGridView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}
	
	private String[] mStrings = { "India", "Pakistan", "Nepal", "Bhutan", "Bangladesh",
			"Sri Lanka", "Afghanistan", "Maldives"};
}
