package com.example.nineoldandroidexample;

import java.util.ArrayList;
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
import android.widget.Button;
import android.widget.LinearLayout;

public class ReverseAnimationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reverse_action);
		
		LinearLayout container = (LinearLayout) findViewById(R.id.container);
        final MyAnimationView animView = new MyAnimationView(this);
        container.addView(animView);

        Button btn_Start = (Button) findViewById(R.id.btn_Start);
        btn_Start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                animView.startAnimation();
            }
        });

        Button btn_Reverse = (Button) findViewById(R.id.btn_Reverse);
        btn_Reverse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                animView.reverseAnimation();
            }
        });

    }

    public class MyAnimationView extends View implements ValueAnimator.AnimatorUpdateListener {

        public final ArrayList<ShapeHolder> list_balls = new ArrayList<ShapeHolder>();
        ValueAnimator bounce_Animator = null;
        ShapeHolder ball_shape = null;

        public MyAnimationView(Context context) {
            super(context);
            ball_shape = createBall(25, 25);
        }

        private void createAnimation() {
            if (bounce_Animator == null) {
            	bounce_Animator = ObjectAnimator.ofFloat(ball_shape, "y", ball_shape.getY(), getHeight() - 50f).
                        setDuration(1500);
            	bounce_Animator.setInterpolator(new AccelerateInterpolator(2f));
            	bounce_Animator.addUpdateListener(this);
            }
        }

        public void startAnimation() {
            createAnimation();
            bounce_Animator.start();
        }

        public void reverseAnimation() {
            createAnimation();
            bounce_Animator.reverse();
        }

        public void seek(long seekTime) {
            createAnimation();
            bounce_Animator.setCurrentPlayTime(seekTime);
        }

        private ShapeHolder createBall(float x, float y) {
            OvalShape circle = new OvalShape();
            circle.resize(50f, 50f);
            ShapeDrawable drawable = new ShapeDrawable(circle);
            ShapeHolder shapeHolder = new ShapeHolder(drawable);
            shapeHolder.setX(x - 25f);
            shapeHolder.setY(y - 25f);
            int red = (int)(Math.random() * 255);
            int green = (int)(Math.random() * 255);
            int blue = (int)(Math.random() * 255);
            int color = 0xff000000 | red << 16 | green << 8 | blue;
            Paint paint = drawable.getPaint(); //new Paint(Paint.ANTI_ALIAS_FLAG);
            int darkColor = 0xff000000 | red/4 << 16 | green/4 << 8 | blue/4;
            RadialGradient gradient = new RadialGradient(37.5f, 12.5f,
                    50f, color, darkColor, Shader.TileMode.CLAMP);
            paint.setShader(gradient);
            shapeHolder.setPaint(paint);
            return shapeHolder;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.save();
            canvas.translate(ball_shape.getX(), ball_shape.getY());
            ball_shape.getShape().draw(canvas);
            canvas.restore();
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            invalidate();
        }

    }

	}

	

