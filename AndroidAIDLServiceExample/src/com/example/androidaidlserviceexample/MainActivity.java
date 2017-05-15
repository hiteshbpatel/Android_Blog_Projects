package com.example.androidaidlserviceexample;


import com.example.androidaidlserviceexample.IAddService;
import android.support.v7.app.ActionBarActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	private static final String TAG = "AndroidAIDLServiceExample";
	  IAddService service;
	  AddServiceConnection connection;

	  /**
	   * This is the class which represents the actual service-connection.
	   * It type casts the bound-stub implementation of the service class to our AIDL interface.
	   */
	  class AddServiceConnection implements ServiceConnection {

	    public void onServiceConnected(ComponentName name, IBinder boundService) {
	      service = IAddService.Stub.asInterface((IBinder) boundService);
	      Log.i(TAG, "onServiceConnected(): Connected");
	      Toast.makeText(MainActivity.this, "AIDLExample Service connected", Toast.LENGTH_LONG).show();
	    }

	    public void onServiceDisconnected(ComponentName name) {
	      service = null;
	      Log.i(TAG, "onServiceDisconnected(): Disconnected");
	      Toast.makeText(MainActivity.this, "AIDLExample Service Connected", Toast.LENGTH_LONG).show();
	    }
	  }
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initService();
	}
	
	public void onClickButtonResult(View v)
	{
		  TextView 	value1	= (TextView) findViewById(R.id.ed_FirstNumber);
	      EditText	value2  = (EditText) findViewById(R.id.ed_SecondNumber);
	      EditText 	result = (EditText) findViewById(R.id.ed_Result);
	      int n1 =0, n2 =0, res = -1;
	      n1 = Integer.parseInt(value1.getText().toString());
	      n2 = Integer.parseInt(value2.getText().toString());
		   try {
		     res = service.add(n1, n2);
		   } catch (RemoteException e) {
		     Log.i(TAG, "Data fetch failed with: " + e);
		     e.printStackTrace();
		   }
		   result.setText(new Integer(res).toString());
	}
	  /** This is our function which binds our activity(MainActivity) to our service(AddService). */
	  private void initService() {
		Log.i(TAG, "initService()" );
		connection = new AddServiceConnection();
	    Intent i = new Intent();
	    i.setClassName("com.example.androidaidlserviceexample", com.example.androidaidlserviceexample.AddService.class.getName());
	    boolean ret = bindService(i, connection, Context.BIND_AUTO_CREATE);
	    Log.i(TAG, "initService() bound value: " + ret);
	  }

	  /** This is our function to un-binds this activity from our service. */
	  private void releaseService() {
	    unbindService(connection);
	    connection = null;
	    Log.d(TAG, "releaseService(): unbound.");
	  }
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		releaseService();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
