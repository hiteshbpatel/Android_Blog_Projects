package com.venketesh.test;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   EditText edt1,edt2;
   Button bt1,bt2;
   TextView tx1,tx2;
    int counter = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = (EditText)findViewById(R.id.editText);
        edt2 = (EditText)findViewById(R.id.editText3);
        bt1 = (Button)findViewById(R.id.button3);
        bt2 = (Button)findViewById(R.id.button4);
        tx1 = (TextView)findViewById(R.id.textView2);
        tx1.setVisibility(View.GONE);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if (edt1.getText().toString().equals("yaswanth") && edt2.getText().toString().equals("12345"))
                {
                    Toast.makeText(getApplicationContext(),"Signing in",Toast.LENGTH_SHORT).show();
                }
                else {
                 Toast.makeText(getApplicationContext(),"Wrong credentials",Toast.LENGTH_SHORT).show();
                 tx1.setVisibility(View.VISIBLE);
                 tx1.setBackgroundColor(Color.RED);
                 counter--;
                 tx1.setText(Integer.toString(counter));
                 if(counter == 0)
                 {
                     bt1.setEnabled(false);
                 }
             }

            }
        });
    }
}
