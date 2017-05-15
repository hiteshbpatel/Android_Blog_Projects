package com.example.shriyanshu.swiperefreshlayoutfragmentexampleandroid;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Handler;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_refresh_layout_fragment);

        getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SwipeRefreshLayoutFragment())
                    .commit();
    }

    public static class SwipeRefreshLayoutFragment extends Fragment {
        List<String> arrayList;
        SwipeRefreshLayout mSwipeRefreshLayout;
        ListView mListView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.swipe_refresh_layout_fragment, container, false);
            mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
            mListView = (ListView) view.findViewById(R.id.listView);


            mSwipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.blue, R.color.green);
            arrayList = Arrays.asList(getResources().getStringArray(R.array.list_names));

            ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, arrayList);
            mListView.setAdapter(adapter);

            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            shuffle();
                        }
                    }, 2500);
                }
            });
            return view;
        }

        public void shuffle() {

            Collections.shuffle(arrayList);
            ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, arrayList);
            mListView.setAdapter(adapter);
            mSwipeRefreshLayout.setRefreshing(false);

        }

    }
}