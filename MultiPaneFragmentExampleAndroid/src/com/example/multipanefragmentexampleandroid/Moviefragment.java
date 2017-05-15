package com.example.multipanefragmentexampleandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Moviefragment extends ListFragment{
	 
	OnMovieSelectedListener mCallback;
	
    public interface OnMovieSelectedListener {
        /** Called by MovieFragment when a list item is selected */
        public void onMovieSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create an array adapter for the list view, using the Movie  array
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, MovieData.Movie));
    }

    @Override
    public void onStart() {
        super.onStart();

       if (getFragmentManager().findFragmentById(R.id.movie_details_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

       
        try {
            mCallback = (OnMovieSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnMovieSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        
        mCallback.onMovieSelected(position);
        getListView().setItemChecked(position, true);
    }
}
