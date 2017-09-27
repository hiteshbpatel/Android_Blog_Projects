package com.compkerworld.playingwithcontacts;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnUpdate;
    EditText etName,etNumber,etNewNumber;
    ContentResolver contentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialization of componet
        contentResolver = getContentResolver();
        btnUpdate = (Button) findViewById(R.id.btnUpdateContact);
        etName = (EditText) findViewById(R.id.etName);
        etNewNumber = (EditText) findViewById(R.id.etNewNumber);
        etNumber = (EditText) findViewById(R.id.etNumber);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateContact();
            }
        });
    }

    private void UpdateContact() {
        //permissions
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS},100);
        }

        if (!etName.getText().toString().isEmpty()&& !etNumber.getText().toString().isEmpty()&&!etName.getText().toString().isEmpty()){
            update(contentResolver,etNumber.getText().toString(),etNewNumber.getText().toString());
            Toast.makeText(this, "Contact Updated..!!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Please fill all the field", Toast.LENGTH_SHORT).show();
        }
    }

    public static void update(ContentResolver contentResolver,String mOldNumber,String mNewNumber){
        //updating contact
        ArrayList<ContentProviderOperation> list = new ArrayList<>();
        String mContactID = String.valueOf(getContactID(contentResolver,mOldNumber));
        String mPhone = ContactsContract.Data.CONTACT_ID+"=? AND " + ContactsContract.Contacts.Data.MIMETYPE + "='" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "'" + " AND "
                +ContactsContract.CommonDataKinds.Phone.TYPE+ "=?";

        String[] mPhoneArgs = new String[]{mContactID,String.valueOf(ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)};
        list.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                .withSelection(mPhone,mPhoneArgs)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER,mNewNumber).build());

        try {
            contentResolver.applyBatch(ContactsContract.AUTHORITY,list);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }

    private static long getContactID(ContentResolver contentResolver, String mNumber) {
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,Uri.encode(mNumber));
        String[] s = {ContactsContract.PhoneLookup._ID};
        Cursor cursor = contentResolver.query(uri,s,null,null,null);
        if (cursor !=null && cursor.moveToFirst()){
            int id = cursor.getColumnIndex(ContactsContract.PhoneLookup._ID);
            return cursor.getLong(id);
        }
        return -1;
    }


}
