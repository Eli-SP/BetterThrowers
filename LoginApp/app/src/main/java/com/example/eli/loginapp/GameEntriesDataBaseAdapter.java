package com.example.eli.loginapp;

/**
 * Created by Eli on 1/18/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class GameEntriesDataBaseAdapter {
    static final String DATABASE_NAME = "games.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;

    static final String DATABASE_CREATE = "create table "+"GAMES"+"( "+"ID"+" integer primary key autoincrement, "+ "GAMENAME text,FOREIGN KEY(PLAYERNAME) REFERENCES LOGIN(USERNAME)); ";
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
    public void insertEntry(String gameName, String playerName)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("GAMENAME", gameName);
        newValues.put("PLAYERNAME", playerName);
        db.insert("GAMES", null, newValues);
    }
    public int deleteEntry(String gameName)
    {
        String where = "GAMENAME=?";
        return db.delete("GAMES", where, new String[]{gameName});
    }
    public String getSingleEntry(String gameName)
    {
        Cursor cursor = db.query("GAMES", null, " GAMENAME=?", new String[]{gameName}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String playername = cursor.getString(cursor.getColumnIndex("PLAYERNAME"));
        cursor.close();
        return playername;
    }
    public void updateEntry(String gameName, String playerName)
    {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("GAMENAME", gameName);
        updatedValues.put("PLAYERNAME", playerName);
        String where = "GAMENAME = ?";
        db.update("GAMES", updatedValues, where, new String[]{gameName});
    }
}