package com.acadgild.barcodereader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import com.acadgild.barcodereader.R;

public class MainActivity extends AppCompatActivity {

    TextView barcodeResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barcodeResult = (TextView) findViewById(R.id.barcode_result);

    }

    public void scanBarCode(View v){
        Intent i = new Intent(this, ScanBarcodeActivity.class);
        startActivityForResult(i,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==0){

            if(requestCode== CommonStatusCodes.SUCCESS){
                if(data !=null){

                    Barcode barcode = data.getParcelableExtra("barcode");
                    barcodeResult.setText("Barcode Value:" + barcode.displayValue);

                } else{
                    barcodeResult.setText("No Barcode Found..!!");
                }
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
