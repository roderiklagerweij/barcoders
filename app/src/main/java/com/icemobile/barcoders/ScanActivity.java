package com.icemobile.barcoders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.icemobile.barcoders.data.Classwiring;

import java.util.ArrayList;
import java.util.List;

public class ScanActivity extends AppCompatActivity {

    private List<String> barcodes = new ArrayList<>();

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_scan);

        Toast.makeText(this, "Please scan your first product", Toast.LENGTH_LONG).show();
        scanCode();
    }

    private void scanCode() {
        new IntentIntegrator(ScanActivity.this).initiateScan();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                barcodes.add(result.getContents());
                if (barcodes.size() == 1) {
                    Toast.makeText(this, "Please scan your second product", Toast.LENGTH_LONG).show();
                    scanCode();
                } else if (barcodes.size() == 2) {
                    Toast.makeText(this, "Please scan your third and final product", Toast.LENGTH_LONG).show();
                    scanCode();
                } else if (barcodes.size() == 3){
                    analyze();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void analyze() {
        List<String> sentences = Classwiring.getCategoryMapper().getSentences(barcodes);
    }

}
