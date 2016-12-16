package com.icemobile.barcoders;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import com.icemobile.barcoders.data.domain.Sentence;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private TextView line1;
    private TextView line2;
    private TextView line3;
    private TextView line4;

    private MediaPlayer mp;
    private List<Sentence> sentences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        sentences = getIntent().getParcelableArrayListExtra("sentences");

        line1 = (TextView) findViewById(R.id.line1);
        line2 = (TextView) findViewById(R.id.line2);
        line3 = (TextView) findViewById(R.id.line3);
        line4 = (TextView) findViewById(R.id.line4);

        line1.setText(sentences.get(0).getText());
        line2.setText(sentences.get(1).getText());
        line3.setText(sentences.get(2).getText());
        line4.setText(sentences.get(3).getText());


        animate(line1, 0);
    }

    private void animate(final View view, final int position){
        mp = MediaPlayer.create(this, getResources().getIdentifier(sentences.get(position).getAudioFilename(), "raw", getPackageName()));
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                switch (position) {
                    case 0:
                        animate(line2, 1);
                        break;
                    case 1:
                        animate(line3, 2);
                        break;
                    case 2:
                        animate(line4, 3);
                        break;
                    default:
                        break;
                }
            }
        });
        mp.start();


        view.setVisibility(View.VISIBLE);
        AnimatorSet set = new AnimatorSet();

        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "alpha", .3f, 1f);
        fadeIn.setDuration(2000);

        ValueAnimator scaleUp = ValueAnimator.ofFloat((float)0.5,(float)1);
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
        set.start();
    }
}
