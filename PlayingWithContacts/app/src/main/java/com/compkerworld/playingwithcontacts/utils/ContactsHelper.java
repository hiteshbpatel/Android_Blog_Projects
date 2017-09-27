package com.compkerworld.playingwithcontacts.utils;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;
import java.util.ArrayList;

/**
 * Created by kumar on 9/13/2017.
 */

public class ContactsHelper {

    public static boolean insertContact(ContentResolver contactAdder, String firstName, String mobileNumber) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null).withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());

        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValue(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE).withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,firstName).build());

        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValue(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE).withValue(ContactsContract.CommonDataKinds.Phone.NUMBER,mobileNumber).withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE).build());
        try {
            contactAdder.applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
            Log.d("Errors", e.getMessage());
            return false;
        }
        return true;
    }

    public static int updateContact(ContentResolver contactUpdate, String name, String mobileNumber) throws RemoteException, OperationApplicationException {
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();

        String rawContactId = getRawContactID(contactUpdate, name);

        Log.d("Raw Contact ID: ", rawContactId);

        //String where = ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME + " = ? ";
        String where = ContactsContract.Data.RAW_CONTACT_ID + " = ? AND mimetype_id=5 AND  " + ContactsContract.Data.MIMETYPE + " = ?" ;

        String[] params = new String[] {rawContactId, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE};

        //ops.add(ContentProviderOperation.newUpdate(ContactsContract.RawContacts.CONTENT_URI).withSelection(where, params).withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null).withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());
//        ops.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
//        .withSelection(where, params)
//        .withValue(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE).withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,name)
//        .build());
        ops.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                .withSelection(where, params)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER,mobileNumber)
                .build());


        ContentProviderResult [] contentProviderResult = contactUpdate.applyBatch(ContactsContract.AUTHORITY, ops);

        return contentProviderResult[0].count;
    }


    private static String getRawContactID(ContentResolver contactHelper,String name) {
        Uri uri = ContactsContract.Data.CONTENT_URI;
        String[] projection = new String[] { ContactsContract.Data.RAW_CONTACT_ID };
        String selection = ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME + " = ?";
        String[] selectionArguments = { name };
        Cursor cursor = contactHelper.query(uri, projection, selection, selectionArguments, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                return cursor.getString(0);
            }
        }
        return "-1";
    }
}
