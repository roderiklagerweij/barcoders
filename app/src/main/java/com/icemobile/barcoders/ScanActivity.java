package com.icemobile.barcoders;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.Result;
import com.icemobile.barcoders.data.Classwiring;
import com.icemobile.barcoders.data.domain.Sentence;

import java.util.ArrayList;
import java.util.List;

import zxing.library.DecodeCallback;
import zxing.library.ZXingFragment;

public class ScanActivity extends AppCompatActivity {

    private List<String> barcodes = new ArrayList<>();
    private TextView scanStatus;
    private MediaPlayer mp;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_scan);
        scanStatus = (TextView) findViewById(R.id.scan_status);
        ObjectAnimator popupAnimX = ObjectAnimator.ofFloat(scanStatus, View.SCALE_X, 1f, 1.3f, 1f);
        ObjectAnimator popupAnimY = ObjectAnimator.ofFloat(scanStatus, View.SCALE_Y, 1f, 1.3f, 1f);
        final AnimatorSet set = new AnimatorSet();
        set.playTogether(popupAnimX, popupAnimY);

        final ZXingFragment xf = (ZXingFragment) getSupportFragmentManager().findFragmentById(R.id.scanner);
        xf.setDecodeCallback(new DecodeCallback(){

            @Override
            public void handleBarcode(Result result, Bitmap arg1, float arg2) {
                mp = MediaPlayer.create(ScanActivity.this, getResources().getIdentifier("beep", "raw", getPackageName()));
                mp.start();
                barcodes.add(result.getText());
                xf.restartScanningIn(3000);
                if (barcodes.size() == 1) {
                    scanStatus.setText("1 / 3");
                    set.start();
                } else if (barcodes.size() == 2) {
                    scanStatus.setText("2 / 3");
                    set.start();
                } else if (barcodes.size() == 3) {
                    analyze();
                }
            }
        });

    }


    private void analyze() {
        List<Sentence> sentences = Classwiring.getCategoryMapper().getSentences(barcodes);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putParcelableArrayListExtra("sentences", (ArrayList<? extends Parcelable>) sentences);
        startActivity(intent);
    }

}
