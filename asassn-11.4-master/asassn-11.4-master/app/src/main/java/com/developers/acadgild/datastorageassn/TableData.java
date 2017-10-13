package com.developers.acadgild.datastorageassn;

import android.provider.BaseColumns;


public class TableData {

    public TableData()
    {

    }

    public static abstract class TableInfo implements BaseColumns
    {
        /** KEYSSSS  */
        public static final String id = "idd";
        public static final String firstname = "firstname";
        public static final String lastname = "lastname";
        public static final String DATABASE_NAME = "user_info1";
        public static final String TABLE_NAME = "reg_info1";
    }
}
