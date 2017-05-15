package com.example.multipanefragmentexampleandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MovieDetailsFragment extends Fragment {

	final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {

        
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.movie_details_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

       
        Bundle args = getArguments();
        if (args != null) {
            // Set movie details based on argument passed in
            updateMovieDetailsView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set movie details based on saved instance state defined during onCreateView
        	updateMovieDetailsView(mCurrentPosition);
        }
    }

    public void updateMovieDetailsView(int position) {
        TextView tv_movie_details = (TextView) getActivity().findViewById(R.id.tv_movie_details);
        tv_movie_details.setText(MovieData.MovieDetails[position]);
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current movie selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}
