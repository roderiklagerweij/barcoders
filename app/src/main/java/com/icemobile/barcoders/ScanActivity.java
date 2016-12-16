package com.icemobile.barcoders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;
import com.icemobile.barcoders.data.Classwiring;

import java.util.ArrayList;
import java.util.List;

import zxing.library.DecodeCallback;
import zxing.library.ZXingFragment;

public class ScanActivity extends AppCompatActivity {

    private List<String> barcodes = new ArrayList<>();

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_scan);

        Toast.makeText(this, "Please scan your first product", Toast.LENGTH_LONG).show();
        final ZXingFragment xf = (ZXingFragment) getSupportFragmentManager().findFragmentById(R.id.scanner);
        xf.setDecodeCallback(new DecodeCallback(){

            @Override
            public void handleBarcode(Result result, Bitmap arg1, float arg2) {
                Log.d("Test", "result: "  + result.getText());
                barcodes.add(result.getText());
                xf.restartScanningIn(3000);
                if (barcodes.size() == 3) {
                    analyze();
                }
            }
        });

    }


    private void analyze() {
        List<String> sentences = Classwiring.getCategoryMapper().getSentences(barcodes);
        for (String s : sentences) {
            Log.d("Test", s);
        }
    }

}
