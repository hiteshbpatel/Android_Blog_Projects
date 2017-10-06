package com.hiteshfragment.listbuttonclickfragment;

/**
 * Created by Harshada Chavan on 9/27/2017.
 */
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class CountryDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Setting the layout for this activity */
        setContentView(R.layout.country_details_activity_layout);

        /** Getting the fragment manager for fragment related operations */
        FragmentManager fragmentManager = getFragmentManager();

        /** Getting the fragmenttransaction object, which can be used to add, remove or replace a fragment */
        FragmentTransaction fragmentTransacton = fragmentManager.beginTransaction();

        /** Instantiating the fragment CountryDetailsFragment */
        CountryDetailsFragment detailsFragment = new CountryDetailsFragment();

        /** Creating a bundle object to pass the data(the clicked item's position) from the activity to the fragment */
        Bundle b = new Bundle();

        /** Setting the data to the bundle object from the Intent*/
        b.putInt("position", getIntent().getIntExtra("position", 0));

        /** Setting the bundle object to the fragment */
        detailsFragment.setArguments(b);

        /** Adding the fragment to the fragment transaction */
        fragmentTransacton.add(R.id.country_details_fragment_container, detailsFragment);

        /** Making this transaction in effect */
        fragmentTransacton.commit();

    }
}
