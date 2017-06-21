package com.vigneshtraining.assignment102;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyRecycleAdapter.MyClickListener,View.OnClickListener {

    private MyRecycleAdapter myRecycleAdapter;
    private FloatingActionButton fab1;
    private RecyclerView recyclerView;
    private Snackbar snackbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle(R.string.app_name);

        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler);

        String itemData[]={"Alpha","Beta","Cupcake","Donut","Eclair","Froyo","Gingerbread","Honeycomb","Ice Cream Sandwich","Jelly Bean","Kitkat","Lollipop","Nougat","O"};

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myRecycleAdapter=new MyRecycleAdapter(itemData,this);

        recyclerView.setAdapter(myRecycleAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        fab1= (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(this);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main; this adds items to the action bar if it is present.
        MenuInflater M = getMenuInflater();
        M.inflate(R.menu.main, menu);

        //  getMenuInflater().inflate(R.main.main, main);
        return true;
    }

    @Override
    public void onItemClick(int position, View v) {
        snackbar= snackbar.make(v,"Clicked on "+myRecycleAdapter.getItemData()[position],Snackbar.LENGTH_INDEFINITE);

        snackbar.setAction("Action",this).show();

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==fab1.getId()){
            Toast.makeText(this,"Clicked on FAB",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Clicked on Action",Toast.LENGTH_LONG).show();
        }

    }
}
