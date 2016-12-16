package com.icemobile.barcoders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;
import com.icemobile.barcoders.data.Classwiring;
import com.icemobile.barcoders.data.domain.Sentence;

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

//    private void scanCode() {
////        new IntentIntegrator(ScanActivity.this).initiateScan();
//        overridePendingTransition(0, 0);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if(result != null) {
//            if(result.getContents() == null) {
//                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
//            } else {
//                barcodes.add(result.getContents());
//                if (barcodes.size() == 1) {
//                    Toast.makeText(this, "Please scan your second product", Toast.LENGTH_LONG).show();
//                    scanCode();
//                } else if (barcodes.size() == 2) {
//                    Toast.makeText(this, "Please scan your third and final product", Toast.LENGTH_LONG).show();
//                    scanCode();
//                } else if (barcodes.size() == 3){
//                    analyze();
//                }
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
    }

    private void analyze() {
        List<Sentece> sentences = Classwiring.getCategoryMapper().getSentences(barcodes);
        for (Sentence s : sentences) {
            Log.d("Test", s.getText());
        }
    }

}
