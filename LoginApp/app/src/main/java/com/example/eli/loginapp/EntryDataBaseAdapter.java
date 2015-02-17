package com.example.eli.loginapp;

/**
 * Created by Eli on 1/18/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class EntryDataBaseAdapter {
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;

    static final String DATABASE_CREATE = "create table "+"ENTRY"+"( "+"ID"+" integer primary key autoincrement, "+ "GAMEID integer, HITS integer, MISSES integer, FOREIGN KEY(GAMEID) REFERENCES GAMES(ID)); ";
    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;
    public EntryDataBaseAdapter(Context _context)
    {
        context = _context;

        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public EntryDataBaseAdapter open() throws  SQLException
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
    public void insertEntry(int gameID, int hits, int misses)
    {
        db = dbHelper.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("GAMEID", gameID);
        newValues.put("HITS", hits);
        newValues.put("MISSES", misses);
        db.insert("ENTRY", null, newValues);
        this.close();
    }
    public int deleteEntry(String gameName)
    {
        db = dbHelper.getWritableDatabase();
        String where = "GAMENAME=?";
        int x = db.delete("GAMES", where, new String[]{gameName});
        db.close();
        return x;
    }
    public Cursor getEntry(int EntryNumber)
    {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ENTRY WHERE GAMEID=?", new String[]{String.valueOf(EntryNumber)});
        if(cursor.getCount()<1)
        {
            cursor.close();
            return null;
        }
        db.close();
        return cursor;
    }
    public Cursor getEntries()
    {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ENTRY", null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return null;
        }
        db.close();
        return cursor;
    }
    /*public int getHits()
    {
        Cursor cursor = db.query("ENTRY", null, " HITS=?", new String[]{gameName}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return -1;
        }
        cursor.moveToFirst();
        String playername = cursor.getString(cursor.getColumnIndex("PLAYERNAME"));
        cursor.close();
        return playername;
    }*/
    /*public int getHits()
    {
        Cursor cursor = db.query("ENTRY", null, " GAMENAME=?", new String[]{gameName}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String playername = cursor.getString(cursor.getColumnIndex("PLAYERNAME"));
        cursor.close();
        return playername;
    }*/
    public void updateEntry(String gameName, int playerID)
    {
        db = dbHelper.getWritableDatabase();
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("GAMENAME", gameName);
        updatedValues.put("PLAYERNAME", playerID);
        String where = "GAMENAME = ?";
        db.update("GAMES", updatedValues, where, new String[]{gameName});
        db.close();
    }
}