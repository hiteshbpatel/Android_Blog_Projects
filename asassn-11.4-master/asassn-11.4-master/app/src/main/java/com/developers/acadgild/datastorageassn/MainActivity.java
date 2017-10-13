package com.developers.acadgild.datastorageassn;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText id,firname,lasname;
    Button save,show;
    Context ctx = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // EditTexts in the first page of the app
        id = (EditText) findViewById(R.id.id);
        firname = (EditText) findViewById(R.id.firstname);
        lasname = (EditText) findViewById(R.id.lastname);
        save = (Button) findViewById(R.id.save);
        show = (Button) findViewById(R.id.show);

        // data will be stored inside the databade after clicking on this button

        // user can enter any amount of data
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseOperations DB = new DatabaseOperations(ctx);
                String idd = id.getText().toString();
                String first = firname.getText().toString();
                String last = lasname.getText().toString();
                DB.putInformation(DB,idd,first,last);
                Log.d("PutInfo","Data Input Done");
                Toast.makeText(MainActivity.this, "Data Saved Succesfully" , Toast.LENGTH_LONG ).show();
            }
        });

        // click on this button to open a new activity to see all the data stored in the database
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ShowData.class));
            }
        });
    }
}
