package com.example.shriyanshu.clouddatabaseexampleandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    DatabaseReference mDatabaseReference;
    private RecyclerView mRecyclerView;
    private ListItemsAdapter mAdapter;
    FloatingActionButton fab;
    private ArrayList<ListItem> mListItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("listItem");
        mListItems = new ArrayList<>();
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateUI();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewListItem();

            }
        });

        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Added",dataSnapshot.getValue(ListItem.class).toString());
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Changed",dataSnapshot.getValue(ListItem.class).toString());

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG+"Removed",dataSnapshot.getValue(ListItem.class).toString());

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG+"Moved",dataSnapshot.getValue(ListItem.class).toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG+"Cancelled",databaseError.toString());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
            case R.id.action_delete_all:
                deleteAllListItems();
                break;
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    public void createNewListItem() {
        // Create new List Item  at /listItem

        final String key = FirebaseDatabase.getInstance().getReference().child("listItem").push().getKey();
        LayoutInflater li = LayoutInflater.from(this);
        View getListItemView = li.inflate(R.layout.dialog_list_item, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setView(getListItemView);

        final EditText edUserInput = (EditText) getListItemView.findViewById(R.id.ed_DialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // get user input and set it to result
                        // edit text
                        String listItemText = edUserInput.getText().toString();
                        ListItem listItem = new ListItem(listItemText);
                        Map<String, Object> listItemValues = listItem.toMap();
                        Map<String, Object> childUpdates = new HashMap<>();
                        childUpdates.put("/listItem/" + key, listItemValues);
                        FirebaseDatabase.getInstance().getReference().updateChildren(childUpdates);

                    }
                }).create()
                .show();

    }

    public void deleteAllListItems(){
        FirebaseDatabase.getInstance().getReference().child("listItem").removeValue();
        mListItems.clear();
        mAdapter.notifyDataSetChanged();
        Toast.makeText(this,"Items Deleted Successfully",Toast.LENGTH_SHORT).show();

    }


    private void fetchData(DataSnapshot dataSnapshot) {
        ListItem listItem=dataSnapshot.getValue(ListItem.class);
        mListItems.add(listItem);
        updateUI();
    }


   private void updateUI(){
        mAdapter = new ListItemsAdapter(mListItems);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class ListItemsHolder extends RecyclerView.ViewHolder{

        public TextView tvName;
        public ListItemsHolder(View itemView){
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_Name);

        }

        public void bindData(ListItem s){
            tvName.setText(s.getListItemText());

        }
    }

    private class ListItemsAdapter extends RecyclerView.Adapter<ListItemsHolder>{
        private ArrayList<ListItem> mListItems;
        public ListItemsAdapter(ArrayList<ListItem> ListItems){
            mListItems = ListItems;

        }


        @Override
        public ListItemsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            View view = layoutInflater.inflate(R.layout.list_item,parent,false);
            return new ListItemsHolder(view);

        }

        @Override
        public void onBindViewHolder(ListItemsHolder holder, int position) {

            ListItem s = mListItems.get(position);
            holder.bindData(s);

        }

        @Override
        public int getItemCount() {
            return mListItems.size();

        }
    }


}
