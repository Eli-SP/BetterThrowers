package com.example.eli.loginapp;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ListViewActivity extends ActionBarActivity {
    GameEntriesDataBaseAdapter gamesDBAdapter;
    EntryDataBaseAdapter entryDBAdapter;
    TextView Id, GameName, PlayerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        gamesDBAdapter = new GameEntriesDataBaseAdapter(getApplicationContext());
        entryDBAdapter = new EntryDataBaseAdapter(getApplicationContext());
        Id = (TextView)findViewById(R.id.view1);
        GameName = (TextView)findViewById(R.id.view2);
        PlayerID = (TextView)findViewById(R.id.view3);

        Cursor cursor = gamesDBAdapter.getAllEntries();
        if(cursor != null && cursor.moveToFirst())
        {
            //textView.setText("ID | GameName | PlayerID");
            while(!cursor.isLast())
            {
                //Id.append(cursor.getString(1));
                GameName.append(cursor.getString(1));
                GameName.append("\n");
                PlayerID.append(cursor.getString(2));
                PlayerID.append("\n");
                cursor.moveToNext();
            }
            //Id.append(cursor.getString(1));
            GameName.append(cursor.getString(1));
            PlayerID.append(cursor.getString(2));
            cursor.close();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view, menu);
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
