package com.example.nineoldandroidexample;

import com.nineoldandroids.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class TogglesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toggles);
		
		 final View target = findViewById(R.id.btn_target);
	        final int duration = 2 * 1000;

	        findViewById(R.id.btn_tx).setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                ObjectAnimator.ofFloat(target, "translationX", 0, 50, -50, 0).setDuration(duration).start();
	            }
	        });
	        findViewById(R.id.btn_ty).setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                ObjectAnimator.ofFloat(target, "translationY", 0, 50, -50, 0).setDuration(duration).start();
	            }
	        });
	        findViewById(R.id.btn_sx).setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                ObjectAnimator.ofFloat(target, "scaleX", 1, 2, 1).setDuration(duration).start();
	            }
	        });
	        findViewById(R.id.btn_sy).setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                ObjectAnimator.ofFloat(target, "scaleY", 1, 2, 1).setDuration(duration).start();
	            }
	        });
	        findViewById(R.id.btn_alpha).setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                ObjectAnimator.ofFloat(target, "alpha", 1, 0, 1).setDuration(duration).start();
	            }
	        });
	        findViewById(R.id.btn_rx).setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                ObjectAnimator.ofFloat(target, "rotationX", 0, 180, 0).setDuration(duration).start();
	            }
	        });
	        findViewById(R.id.btn_ry).setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                ObjectAnimator.ofFloat(target, "rotationY", 0, 180, 0).setDuration(duration).start();
	            }
	        });
	        findViewById(R.id.btn_rz).setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                ObjectAnimator.ofFloat(target, "rotation", 0, 180, 0).setDuration(duration).start();
	            }
	        });
	       
	    	
		
	}

	
}
