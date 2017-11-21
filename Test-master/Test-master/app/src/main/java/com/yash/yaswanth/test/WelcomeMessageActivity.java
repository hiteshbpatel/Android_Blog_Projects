package com.yash.yaswanth.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by user on 11/21/2017.
 */

public class WelcomeMessageActivity extends Activity {

    private TextView welcomeMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_message_display);

        Intent intentObject = getIntent();
        //String userName = intentObject.getStringExtra("UserName");
        // boolean isRegistered = intentObject.getBooleanExtra("isRegistered", true);

        String userNameBundle=intentObject.getExtras().getString("BundleUserName");
        //Toast.makeText(getApplicationContext(),userNameBundle, Toast.LENGTH_LONG).show();

        welcomeMsg = (TextView) findViewById(R.id.txtShowMsg);


        welcomeMsg.setText("Welcome " + userNameBundle);

    }
}
