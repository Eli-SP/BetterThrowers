package com.example.eli.loginapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class createGame extends ActionBarActivity {
    GameEntriesDataBaseAdapter gamesDBAdapter;
    Button buttonCreateGame;
    EditText gamename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        gamename = (EditText)findViewById(R.id.editTextGameName);
        buttonCreateGame = (Button)findViewById(R.id.buttonCreateGame);
        buttonCreateGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamesDBAdapter = new GameEntriesDataBaseAdapter(getApplicationContext());
                gamesDBAdapter = gamesDBAdapter.open();
                gamesDBAdapter.insertEntry(gamename.getText().toString(), 1); // hardcoded
                gamesDBAdapter.close();


                Intent intentCreateGame = new Intent(getApplicationContext(), gameScreen.class);
                startActivity(intentCreateGame);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_game, menu);
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
