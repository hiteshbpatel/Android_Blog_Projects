package com.acadgild.physicsanimationsdemo;

import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.animation.FloatPropertyCompat;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //you need to create code for fling animation.
    public void flingIt(View view) {
        ImageView emoji = (ImageView)findViewById(R.id.emoji);

        FlingAnimation flingAnimation
                = new FlingAnimation(emoji, DynamicAnimation.X);
        flingAnimation.setStartVelocity(500);
        flingAnimation.setFriction(0.5f);
        flingAnimation.start();
    }

    //you need to create code for bounce animation.
    public void bounce(View view) {
        final ImageView emoji = (ImageView)findViewById(R.id.emoji);
        //Use SpringAnimation for
        SpringAnimation springAnimation
                = new SpringAnimation(emoji, DynamicAnimation.X);

        SpringForce springForce = new SpringForce();
        springForce.setFinalPosition(emoji.getX());
        springForce.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        springForce.setStiffness(SpringForce.STIFFNESS_LOW);

        springAnimation.setSpring(springForce);
        springAnimation.setStartVelocity(2000);
        springAnimation.start();

        emoji.setImageResource(R.drawable.ic_sentiment_very_satisfied_black_56dp);
        springAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                emoji.setImageResource(R.drawable.ic_sentiment_neutral_black_56dp);
            }
        });
    }

    //This code is for stretch animation
    public void stretch(View view) {
        final ImageView emoji = (ImageView)findViewById(R.id.emoji);
        FloatPropertyCompat<View> scale = new FloatPropertyCompat<View>("scale") {

            @Override
            public float getValue(View view) {
                return view.getScaleX();
            }

            @Override
            public void setValue(View view, float value) {
                view.setScaleX(value);
                view.setScaleY(value);
            }
        };

        //This code is for Spring animation
        SpringAnimation stretchAnimation =
                new SpringAnimation(emoji, scale);
        stretchAnimation.setMinimumVisibleChange(
                DynamicAnimation.MIN_VISIBLE_CHANGE_SCALE);

        SpringForce force = new SpringForce(1);
        force.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY)
             .setStiffness(SpringForce.STIFFNESS_VERY_LOW);

        stretchAnimation.setSpring(force)
                        .setStartVelocity(100);

        stretchAnimation.start();
    }
}
