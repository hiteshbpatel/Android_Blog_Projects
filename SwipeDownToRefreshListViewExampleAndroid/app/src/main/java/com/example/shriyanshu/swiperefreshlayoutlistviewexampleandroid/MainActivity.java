package com.example.shriyanshu.swiperefreshlayoutlistviewexampleandroid;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView mListView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ArrayList<String> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mListView = (ListView) findViewById(R.id.listview);
        listItems = new ArrayList<String>();
        listItems.add("List Item 1");
        listItems.add("List Item 2");
        listItems.add("List Item 3");
        listItems.add("List Item 4");
        listItems.add("List Item 5");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //handling swipe refresh
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        listItems.add(0,"New Item Added");
                        mListView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        mListView.smoothScrollToPosition(0);
                    }
                }, 2000);
            }
        });
    }
}
