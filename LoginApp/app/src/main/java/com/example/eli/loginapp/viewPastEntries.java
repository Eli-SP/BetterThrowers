package com.example.eli.loginapp;

import android.database.Cursor;
import android.os.DropBoxManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class viewPastEntries extends ActionBarActivity {

    Button buttonViewGames, buttonViewEntries;
    GameEntriesDataBaseAdapter gamesDBAdapter;
    EntryDataBaseAdapter entryDBAdapter;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_past_entries);
        textView = new TextView(this);
        textView.findViewById(R.id.TextViewGames);
        gamesDBAdapter = new GameEntriesDataBaseAdapter(this);
        entryDBAdapter = new EntryDataBaseAdapter(this);

        buttonViewEntries = (Button)findViewById(R.id.buttonViewEntries);
        buttonViewGames = (Button)findViewById(R.id.buttonViewGames);
        buttonViewEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        buttonViewGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = gamesDBAdapter.getAllEntries();
                if(cursor != null && cursor.moveToFirst())
                {
                    textView.setText("ID | GameName | PlayerID");
                    /*while(!cursor.isLast())
                    {
                        textView.append(cursor.getString(1));
                        textView.append(cursor.getString(2));
                        textView.append(cursor.getString(3));
                        cursor.moveToNext();
                    }
                    textView.append(cursor.getString(1));
                    textView.append(cursor.getString(2));
                    textView.append(cursor.getString(3));*/
                    cursor.close();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_past_entries, menu);
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
