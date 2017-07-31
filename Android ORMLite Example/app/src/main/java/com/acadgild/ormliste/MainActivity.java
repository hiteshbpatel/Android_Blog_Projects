package com.acadgild.ormliste;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {
	EditText etEntry;
	ListView listView;
	NameAdapter adapter = null;
	DatabaseHelper helper;
	List<Person> list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		listView = (ListView) findViewById(R.id.listView1);
		listView.setOnItemClickListener(this);

		etEntry = (EditText) findViewById(R.id.etentry);

		helper = new DatabaseHelper(getApplicationContext());

		setDataToAdapter();

	}

	public void adddata(View v) {
		String strName = etEntry.getText().toString().trim();
		if (TextUtils.isEmpty(strName)) {
			showToast("Please Add your Name!!!");
			return;
		}

		Person person = new Person();
		person.setName(strName);

		helper.addData(person);

		showToast("Data Successfully Added");

		etEntry.setText("");

		setDataToAdapter();

	}

	private void setDataToAdapter() {

		list = helper.GetData();

		adapter = new NameAdapter(this, R.layout.row, list);
		listView.setAdapter(adapter);

	}

	private void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	public void deletedata(View v) {

		list = helper.GetData();
		if (null != list && list.size() > 0) {
			AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
					.create();
			alert.setTitle("Delete ?");
			alert.setMessage("Are you sure want to delete All data from Database");
			alert.setButton("No", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			alert.setButton2("Yes", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();

					helper.deleteAll();
                    etEntry.setText(""); 
					showToast("Removed All Data!!!");
					setDataToAdapter();
				}
			});
			alert.show();
		} else {
			showToast("No data found from the Database");
		}
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		showToast(list.get(position).getName());
	}
}
