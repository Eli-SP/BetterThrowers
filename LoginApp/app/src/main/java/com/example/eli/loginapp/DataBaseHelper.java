package com.example.eli.loginapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

/**
 * Created by Eli on 1/11/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper
{
    public DataBaseHelper(Context context, String databaseName, CursorFactory factory, int databaseVersion)
    {
        super(context, databaseName, factory, databaseVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase _db)
    {
        _db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldversion, int _newversion)
    {
        Log.w("TaskDBAdapter", "Upgrading from version " +_oldversion + " to " +_newversion + " which will destroy all old data");
        _db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        onCreate(_db);
    }
}
