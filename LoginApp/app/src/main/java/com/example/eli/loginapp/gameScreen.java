package com.example.eli.loginapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class gameScreen extends ActionBarActivity {
    private final static int REQUEST_ENABLE_BT = 1;
    int hits = 0, misses = 0;
    float percentage = 100;
    Button buttonHits, buttonMisses, buttonSaveGame, buttonFire, buttonScan;
    TextView textViewNumHits, textViewNumMisses, textViewAccuracyPercentage;

    GameEntriesDataBaseAdapter gamesDBAdapter;
    EntryDataBaseAdapter entryDBAdapter;

    private BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();

        gamesDBAdapter = new GameEntriesDataBaseAdapter(this);
        gamesDBAdapter = gamesDBAdapter.open();
        gamesDBAdapter.insertEntry("test", 1); // hardcoded
        gamesDBAdapter.close();

        entryDBAdapter = new EntryDataBaseAdapter(this);
        entryDBAdapter = entryDBAdapter.open();

        buttonSaveGame = (Button)findViewById(R.id.buttonSaveGameToDB);
        buttonHits = (Button)findViewById(R.id.buttonHit);
        buttonMisses = (Button)findViewById(R.id.buttonMiss);
        buttonFire = (Button)findViewById(R.id.buttonFireLauncher);
        buttonScan = (Button)findViewById(R.id.buttonScan);

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
        buttonSaveGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entryDBAdapter.insertEntry(1, hits, misses); //1 is hardcoded, need to do a lookup
                entryDBAdapter.close();
                Toast.makeText(gameScreen.this, "Save Successful", Toast.LENGTH_LONG).show();
            }
        });
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bluetoothAdapter == null || !bluetoothAdapter.isEnabled())
                {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                }
                DeviceScanActivity deviceScanActivity = new DeviceScanActivity();
                deviceScanActivity.scanBLE(true);
            }
        });
        buttonFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
