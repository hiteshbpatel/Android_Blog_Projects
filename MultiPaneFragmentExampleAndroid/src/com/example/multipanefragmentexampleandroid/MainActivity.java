package com.example.multipanefragmentexampleandroid;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends FragmentActivity implements Moviefragment.OnMovieSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       
        if (findViewById(R.id.fragment_container) != null) {

            
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of Moviefragment
            Moviefragment movie_fragment= new Moviefragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            movie_fragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, movie_fragment).commit();
        }
    }

	@Override
	public void onMovieSelected(int position) {
		// TODO Auto-generated method stub
		
        MovieDetailsFragment details_fragment = (MovieDetailsFragment)
                getSupportFragmentManager().findFragmentById(R.id.movie_details_fragment);

        if (details_fragment != null) {
           
        	details_fragment.updateMovieDetailsView(position);

        } else {
           
        	MovieDetailsFragment new_fragment = new MovieDetailsFragment();
            Bundle args = new Bundle();
            args.putInt(MovieDetailsFragment.ARG_POSITION, position);
            new_fragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, new_fragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
	}


   
}
