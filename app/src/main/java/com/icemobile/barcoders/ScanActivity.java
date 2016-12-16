package com.icemobile.barcoders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.icemobile.barcoders.data.Classwiring;
import com.icemobile.barcoders.data.domain.Product;
import com.icemobile.barcoders.data.manager.CategoryMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScanActivity extends AppCompatActivity {

//    private int index = -1;
//    private TextView product1TextView;
//    private TextView product2TextView;
//    private TextView product3TextView;
//    private Button next;
//    private Product product1;
//    private Product product2;
//    private Product product3;

    private List<String> barcodes = new ArrayList<>();

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_scan);

        Toast.makeText(this, "Please scan your first product", Toast.LENGTH_LONG).show();
        scanCode();
//        startActivity(new Intent(this, AnalyzeActivity.class));

//        View.OnClickListener scanListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (view.getId()) {
//                    case R.id.product1:
//                        index = 1;
//                        break;
//                    case R.id.product2:
//                        index = 2;
//                        break;
//                    case R.id.product3:
//                        index = 3;
//                        break;
//                }
//                new IntentIntegrator(ScanActivity.this).initiateScan();
//            }
//        };
//
//        product1TextView = (TextView) findViewById(R.id.product1);
//        product2TextView = (TextView) findViewById(R.id.product2);
//        product3TextView = (TextView) findViewById(R.id.product3);
//        next = (Button) findViewById(R.id.next);
//        product1TextView.setOnClickListener(scanListener);
//        product2TextView.setOnClickListener(scanListener);
//        product3TextView.setOnClickListener(scanListener);
//
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getSentences();
//            }
//        });
//        setNextButton();
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
                } else {
                    analyze();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void analyze() {
//        List<String> sentences = Classwiring.getCategoryMapper().getSentences();
    }

}
