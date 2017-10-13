package com.developers.acadgild.datastorageassn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOperations extends SQLiteOpenHelper {


    // Variables initialized along with the query to create database
    public static final int database_version = 1;
    public String CREATE_QUERY = "CREATE TABLE "+ TableData.TableInfo.TABLE_NAME+"("+ TableData.TableInfo.id+" TEXT,"+TableData.TableInfo.firstname+" TEXT,"+ TableData.TableInfo.lastname+" TEXT);";

    public DatabaseOperations(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database Operations","Database Created Successfully");
    }


    // query to create data is run and if the inputs are valid then the database will be created
    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(CREATE_QUERY);
        Log.d("Database Operations","Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /** Put information into the database*/

    public void putInformation(DatabaseOperations dop,String id,String fname,String lname)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.id,id);
        cv.put(TableData.TableInfo.firstname,fname);
        cv.put(TableData.TableInfo.lastname,lname);
        long k = SQ.insert(TableData.TableInfo.TABLE_NAME,null,cv);
        Log.d("Database Operations","One raw data inserted");
    }

    /** Retreiving data from the database*/
    public Cursor getInformation(DatabaseOperations dop)
    {
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] coloums = {TableData.TableInfo.id, TableData.TableInfo.firstname,TableData.TableInfo.lastname};
        Cursor CR = SQ.query(TableData.TableInfo.TABLE_NAME,coloums,null,null,null,null,null);
        return CR;
    }

}
