package com.example.nineoldandroidexample;

import java.util.ArrayList;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

public class AnimationCloningActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation_cloning);
		
		LinearLayout container = (LinearLayout) findViewById(R.id.container);
        final MyAnimationView animView = new MyAnimationView(this);
        container.addView(animView);

        Button btn_Start = (Button) findViewById(R.id.btn_Start);
        btn_Start.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                animView.startAnimation();
            }
        });
    }

    public class MyAnimationView extends View implements ValueAnimator.AnimatorUpdateListener {

        public final ArrayList<ShapeHolder> list_balls = new ArrayList<ShapeHolder>();
        AnimatorSet animation = null;
        private float mDensity;

        public MyAnimationView(Context context) {
            super(context);

            mDensity = getContext().getResources().getDisplayMetrics().density;

            ShapeHolder ball_one = addBall(50f, 25f);
            ShapeHolder ball_two = addBall(150f, 25f);
            ShapeHolder ball_three = addBall(250f, 25f);
            ShapeHolder ball_four = addBall(350f, 25f);
        }

        private void createAnimation() {
            if (animation == null) {
                ObjectAnimator animation_one = ObjectAnimator.ofFloat(list_balls.get(0), "y",
                        0f, getHeight() - list_balls.get(0).getHeight()).setDuration(500);
                ObjectAnimator animation_two = animation_one.clone();
                animation_two.setTarget(list_balls.get(1));
                animation_one.addUpdateListener(this);

                ShapeHolder ball_two = list_balls.get(2);
                ObjectAnimator animator_Down = ObjectAnimator.ofFloat(ball_two, "y",
                        0f, getHeight() - ball_two.getHeight()).setDuration(500);
                animator_Down.setInterpolator(new AccelerateInterpolator());
                ObjectAnimator animator_Up = ObjectAnimator.ofFloat(ball_two, "y",
                        getHeight() - ball_two.getHeight(), 0f).setDuration(500);
                animator_Up.setInterpolator(new DecelerateInterpolator());
                AnimatorSet set_one = new AnimatorSet();
                set_one.playSequentially(animator_Down, animator_Up);
                animator_Down.addUpdateListener(this);
                animator_Up.addUpdateListener(this);
                AnimatorSet set_two = (AnimatorSet) set_one.clone();
                set_two.setTarget(list_balls.get(3));

                animation = new AnimatorSet();
                animation.playTogether(animation_one, animation_two, set_one);
                animation.playSequentially(set_one, set_two);
            }
        }

        private ShapeHolder addBall(float x, float y) {
            OvalShape circle = new OvalShape();
            circle.resize(50f * mDensity, 50f * mDensity);
            ShapeDrawable drawable = new ShapeDrawable(circle);
            ShapeHolder shapeHolder = new ShapeHolder(drawable);
            shapeHolder.setX(x - 25f);
            shapeHolder.setY(y - 25f);
            int red = (int)(100 + Math.random() * 155);
            int green = (int)(100 + Math.random() * 155);
            int blue = (int)(100 + Math.random() * 155);
            int color = 0xff000000 | red << 16 | green << 8 | blue;
            Paint paint = drawable.getPaint(); 
            int darkColor = 0xff000000 | red/4 << 16 | green/4 << 8 | blue/4;
            RadialGradient gradient = new RadialGradient(37.5f, 12.5f,
                    50f, color, darkColor, Shader.TileMode.CLAMP);
            paint.setShader(gradient);
            shapeHolder.setPaint(paint);
            list_balls.add(shapeHolder);
            return shapeHolder;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            for (int i = 0; i < list_balls.size(); ++i) {
                ShapeHolder shapeHolder = list_balls.get(i);
                canvas.save();
                canvas.translate(shapeHolder.getX(), shapeHolder.getY());
                shapeHolder.getShape().draw(canvas);
                canvas.restore();
            }
        }

        public void startAnimation() {
            createAnimation();
            animation.start();
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            invalidate();
        }

    }

	}

	

