package com.expr.sample.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;

/**
 * Created by Randula.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private final String TAG = "DatabaseHelperClass";
    private static final int databaseVersion = 1;
    private static final String databaseName = "dbTest";
    private static final String TABLE_IMAGE = "ImageTable";

    // Image Table Columns names
    private static final String COL_ID = "col_id";
    private static final String IMAGE_ID = "image_id";
    private static final String IMAGE_BITMAP = "image_bitmap";

    public DatabaseHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_IMAGE_TABLE = "CREATE TABLE " + TABLE_IMAGE + "("
                + COL_ID + " INTEGER PRIMARY KEY ,"
                + IMAGE_ID + " TEXT,"
                + IMAGE_BITMAP + " TEXT )";
        sqLiteDatabase.execSQL(CREATE_IMAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGE);
        onCreate(sqLiteDatabase);
    }

    public void insetImage(Drawable dbDrawable, String imageId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IMAGE_ID, imageId);
        Bitmap bitmap = ((BitmapDrawable)dbDrawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        values.put(IMAGE_BITMAP, stream.toByteArray());
        db.insert(TABLE_IMAGE, null, values);
        db.close();
    }

    public ImageHelper getImage(String imageId) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor2 = db.query(TABLE_IMAGE,
                new String[] {COL_ID, IMAGE_ID, IMAGE_BITMAP},IMAGE_ID
                +" LIKE '"+imageId+"%'", null, null, null, null);
        ImageHelper imageHelper = new ImageHelper();

        if (cursor2.moveToFirst()) {
            do {
                imageHelper.setImageId(cursor2.getString(1));
                imageHelper.setImageByteArray(cursor2.getBlob(2));
            } while (cursor2.moveToNext());
        }

        cursor2.close();
        db.close();
        return imageHelper;
    }

}
