package com.icemobile.barcoders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.icemobile.barcoders.data.Classwiring;
import com.icemobile.barcoders.data.domain.PoeticWrapper;
import com.icemobile.barcoders.data.domain.Product;
import com.icemobile.barcoders.data.manager.CategoryMapper;

import java.util.ArrayList;
import java.util.List;

public class ScanActivity extends AppCompatActivity {

    private int index = -1;
    private TextView product1;
    private TextView product2;
    private TextView product3;
    private Button next;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_scan);

        View.OnClickListener scanListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.product1:
                        index = 1;
                        break;
                    case R.id.product2:
                        index = 2;
                        break;
                    case R.id.product3:
                        index = 3;
                        break;
                }
                Log.d("Test", ": " + index);
                new IntentIntegrator(ScanActivity.this).initiateScan();
            }
        };

        product1 = (TextView) findViewById(R.id.product1);
        product2 = (TextView) findViewById(R.id.product2);
        product3 = (TextView) findViewById(R.id.product3);
        next = (Button) findViewById(R.id.next);
        product1.setOnClickListener(scanListener);
        product2.setOnClickListener(scanListener);
        product3.setOnClickListener(scanListener);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSentences();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                // TODO: get correct product
                String product = "Milk";

                switch (index) {
                    case 1:
                        product1.setText(product);
                        break;
                    case 2:
                        product2.setText(product);
                        break;
                    case 3:
                        product3.setText(product);
                        break;
                }

                Toast.makeText(this, "Scanned: " + result.getContents() + " " + index, Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void getSentences() {
        CategoryMapper categoryMapper = Classwiring.getCategoryMapper();
        Product p1 = categoryMapper.getProduct(product1.getText().toString());
        Product p2 = categoryMapper.getProduct(product2.getText().toString());
        Product p3 = categoryMapper.getProduct(product3.getText().toString());

        List<PoeticWrapper> poetics = categoryMapper.getPoetic(p1, p2, p3);

        List<String> sentences = new ArrayList<>();
        for (PoeticWrapper poeticWrapper : poetics) {
            sentences.add(categoryMapper.getSentence(poeticWrapper.getPoetic(), poeticWrapper.getSentenceType()));
        }

        // start final activity with sentences
    }

}
