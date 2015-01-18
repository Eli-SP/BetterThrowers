package com.example.eli.loginapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class gameScreen extends ActionBarActivity {

    int hits = 0, misses = 0;
    float percentage = 100;
    Button buttonHits, buttonMisses;
    TextView textViewNumHits, textViewNumMisses, textViewAccuracyPercentage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        buttonHits = (Button)findViewById(R.id.buttonHit);
        buttonMisses = (Button)findViewById(R.id.buttonMiss);

        textViewNumHits = (TextView)findViewById(R.id.textViewNumHits);
        textViewNumMisses = (TextView)findViewById(R.id.textViewNumMisses);
        textViewAccuracyPercentage = (TextView)findViewById(R.id.textViewAccuracyPercent);

        textViewNumHits.setText(Integer.toString(hits));
        textViewNumMisses.setText(Integer.toString(misses));
        textViewAccuracyPercentage.setText(Float.toString(percentage));

        buttonHits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hits++;
                textViewNumHits.setText(Integer.toString(hits));
                avg();
            }
        });

        buttonMisses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                misses++;
                textViewNumMisses.setText(Integer.toString(misses));
                avg();
            }
        });
    }
    private void avg() {
        float total = hits + misses;
        textViewAccuracyPercentage.setText(Float.toString((hits/total) * 100));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_screen, menu);
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
