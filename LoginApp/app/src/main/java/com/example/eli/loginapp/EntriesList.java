package com.example.eli.loginapp;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class EntriesList extends ActionBarActivity {
    GameEntriesDataBaseAdapter gamesDBAdapter;
    EntryDataBaseAdapter entryDBAdapter;
    TextView Id, GameID, Misses, Hits;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries_list);


        gamesDBAdapter = new GameEntriesDataBaseAdapter(getApplicationContext());
        entryDBAdapter = new EntryDataBaseAdapter(getApplicationContext());
        Id = (TextView)findViewById(R.id.EntryId);
        GameID = (TextView)findViewById(R.id.EntryGameID);
        Misses = (TextView)findViewById(R.id.EntryMisses);
        Hits = (TextView)findViewById(R.id.EntryHits);

        int i = 1;

        Cursor cursor = entryDBAdapter.getEntries();
        if(cursor != null && cursor.moveToFirst())
        {
            Id.setText("ID:   \n");
            GameID.setText("GameID:   \n");
            Hits.setText("Hits:   \n");
            Misses.setText("Misses:   \n");
            while(!cursor.isLast())
            {
                Id.append(String.valueOf(i++));
                Id.append("\n");
                GameID.append(cursor.getString(1));
                GameID.append("\n");
                Hits.append(cursor.getString(2));
                Hits.append("\n");
                Misses.append(cursor.getString(3));
                Misses.append("\n");
                cursor.moveToNext();
            }
            Id.append(String.valueOf(i));
            Id.append("\n");
            GameID.append(cursor.getString(1));
            GameID.append("\n");
            Hits.append(cursor.getString(2));
            Hits.append("\n");
            Misses.append(cursor.getString(3));
            Misses.append("\n");
            cursor.close();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entries_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
