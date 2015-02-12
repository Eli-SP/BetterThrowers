package com.example.eli.loginapp;

/**
 * Created by Eli on 1/18/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class GameEntriesDataBaseAdapter {
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;

    static final String DATABASE_CREATE = "create table "+"GAMES"+"( "+"ID"+" integer primary key autoincrement, "+ "GAMENAME text, PLAYERNAME integer, FOREIGN KEY(PLAYERNAME) REFERENCES LOGIN(ID)); ";
    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;
    public GameEntriesDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public GameEntriesDataBaseAdapter open() throws  SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }
    public SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }
    public void insertEntry(String gameName, int playerID)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("GAMENAME", gameName);
        newValues.put("PLAYERNAME", playerID);
        db.insert("GAMES", null, newValues);
    }
    public int deleteEntry(String gameName)
    {
        String where = "GAMENAME=?";
        return db.delete("GAMES", where, new String[]{gameName});
    }
    public Cursor getAllEntries()
    {
        //Toast.makeText(context, "!@#!@#!@#!@#", Toast.LENGTH_LONG).show();

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM GAMES", null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return null;
        }
        db.close();
        return cursor;
    }
    public void updateEntry(String gameName, int playerID)
    {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("GAMENAME", gameName);
        updatedValues.put("PLAYERNAME", playerID);
        String where = "GAMENAME = ?";
        db.update("GAMES", updatedValues, where, new String[]{gameName});
    }
}