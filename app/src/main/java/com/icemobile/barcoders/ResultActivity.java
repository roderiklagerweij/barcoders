package com.icemobile.barcoders;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView line1 = (TextView) findViewById(R.id.line1);
        TextView line2 = (TextView) findViewById(R.id.line2);
        TextView line3 = (TextView) findViewById(R.id.line3);

        line1.setText("Some text 1");
        line2.setText("Some text 2");
        line3.setText("Some text 3");

        animate(line1);
        animate(line2);
        animate(line3);
    }

    private void animate(final View view){
        view.setVisibility(View.VISIBLE);
        AnimatorSet set = new AnimatorSet();

        ValueAnimator scaleUp = ValueAnimator.ofFloat(1,(float)1.2);
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

        ValueAnimator scaleDown = ValueAnimator.ofFloat((float)1.2,1);
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


        set.play(scaleUp);
        set.play(scaleDown).after(scaleUp);
        set.start();
    }
}
