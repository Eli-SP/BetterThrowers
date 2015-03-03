package com.example.eli.loginapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class admin_home_screen extends ActionBarActivity {
private Button buttonAdminDeleteUser, buttonAdminDeleteEntry, buttonAdminDeleteGame;
private EditText editTextDeleteUser, editTextDeleteGame, editTextDeleteEntry;
private GameEntriesDataBaseAdapter gameDb;
private EntryDataBaseAdapter entryDb;
private LoginDataBaseAdapter loginDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_screen);

        buttonAdminDeleteEntry = (Button)findViewById(R.id.buttonAdminDeleteEntry);
        buttonAdminDeleteGame = (Button)findViewById(R.id.buttonAdminDeleteGame);
        buttonAdminDeleteUser = (Button)findViewById(R.id.buttonAdminDeleteUser);
        editTextDeleteEntry = (EditText)findViewById(R.id.editTextDeleteEntry);
        editTextDeleteGame = (EditText)findViewById(R.id.editTextDeleteGame);
        editTextDeleteUser = (EditText)findViewById(R.id.editTextDeleteUser);

        buttonAdminDeleteEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entryDb.open().deleteEntry(editTextDeleteEntry.getText().toString());
                entryDb.close();
            }
        });
        buttonAdminDeleteGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gametodelete = editTextDeleteGame.getText().toString();

                gameDb.open().deleteEntry(gametodelete);
                gameDb.close();
            }
        });
        buttonAdminDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDb.open().deleteEntry(editTextDeleteUser.getText().toString());
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin_home_screen, menu);
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
