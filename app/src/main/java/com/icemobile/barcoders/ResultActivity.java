package com.icemobile.barcoders;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView line1;
    private TextView line2;
    private TextView line3;
    private TextView line4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        line1 = (TextView) findViewById(R.id.line1);
        line2 = (TextView) findViewById(R.id.line2);
        line3 = (TextView) findViewById(R.id.line3);
        line4 = (TextView) findViewById(R.id.line4);

        line1.setText("Some text 1");
        line2.setText("Some text 2");
        line3.setText("Some text 3");
        line4.setText("Some text 4");

        animate(line1, 1);
    }

    private void animate(final View view, final int position){
        view.setVisibility(View.VISIBLE);
        AnimatorSet set = new AnimatorSet();

        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "alpha", .3f, 1f);
        fadeIn.setDuration(2000);

        ValueAnimator scaleUp = ValueAnimator.ofFloat(1,(float)2);
        scaleUp.setDuration(800);
        scaleUp.setInterpolator(new BounceInterpolator());
        scaleUp.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float newValue = (Float) valueAnimator.getAnimatedValue();
                view.setScaleY(newValue);
                view.setScaleX(newValue);
            }
        });


        set.play(scaleUp).with(fadeIn);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                switch (position) {
                    case 1:
                        animate(line2, 2);
                        break;
                    case 2:
                        animate(line3, 3);
                        break;
                    case 3:
                        animate(line4, 4);
                        break;
                    default:
                        break;
                }
            }
        });
        set.start();
    }
}
