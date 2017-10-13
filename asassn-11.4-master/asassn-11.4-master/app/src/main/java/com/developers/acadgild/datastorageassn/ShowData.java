package com.developers.acadgild.datastorageassn;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ShowData extends Activity{
    Context CTX=this;
    Button getbtn;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);

        // onclick on this button the user will be able to view all the data which is being stored in the database

        getbtn = (Button) findViewById(R.id.getdatabtn);
        getbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseOperations DOP = new DatabaseOperations(CTX);
                Cursor CR = DOP.getInformation(DOP);
                CR.moveToFirst();
                String NAME = "";
                Log.d("ShowDAta", "Before fetch");
                /**  do {
                 if (CR.getString(0) != null && CR.getString(1) != null && CR.getString(2) != null) {
                 tvid.setText(CR.getString(0));
                 tvfirst.setText(CR.getString(1));
                 tvsecond.setText(CR.getString(2));

                 Log.d("ShowDAta", "Before fetch");
                 }
                 } while (CR.moveToNext());

                 */


                int i = 0;     // i refers to the row
                while (CR.moveToNext()) {

                    if (CR.getString(i) != null && CR.getString(i+1)!=null && CR.getString(i+2)!=null) {




                        /**   DEAD CODE    IGNORE THIS   JUST FOR CHECKING PURPOSE  FOR DEVELOPER ONLY    REFER THE UNCOMMENTED CODE
                        Toast.makeText(getApplicationContext(), "Id: "+CR.getString(i)+" FirstName: "+CR.getString(i+1)+" LastName: "+CR.getString(i+2), Toast.LENGTH_SHORT).show();
                        Snackbar snackbar = Snackbar.make(view,"Id: "+CR.getString(i)+" FirstName: "+CR.getString(i+1)+" LastName: "+CR.getString(i+2),Snackbar.LENGTH_LONG);
                        snackbar.show();
                        */




                        // Main code functionality
                        // Textview created dynamically as user can input any number of data in the database
                        // so functioning has to be dynamic in nature to meet the needs


                        View linearLayout =  findViewById(R.id.info);
                        LinearLayout linearLayoutt = (LinearLayout)findViewById(R.id.info);


                        TextView id = new TextView(ShowData.this);
                        TextView fname = new TextView(ShowData.this);

                        TextView lname = new TextView(ShowData.this);
                      //  linearLayoutt.setOrientation(LinearLayout.HORIZONTAL);
                        id.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        fname.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        lname.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        id.setTextSize(20);
                        fname.setTextSize(20);
                        lname.setTextSize(20);
                        id.setTextColor(Color.BLACK);
                        fname.setTextColor(Color.BLACK);
                        lname.setTextColor(Color.BLACK);
                        linearLayoutt.addView(id);
                        linearLayoutt.addView(fname);
                        linearLayoutt.addView(lname);
                        id.setText("ID: "+CR.getString(i)+"");
                        fname.setText("Firstname: "+CR.getString(i+1)+"");
                        lname.setText("Lastname: "+CR.getString(i+2)+"\n\n");
                    }

                }
                i++;
            }
        });
    }
}
