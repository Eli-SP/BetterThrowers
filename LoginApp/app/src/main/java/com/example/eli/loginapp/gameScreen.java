package com.example.eli.loginapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.ParcelUuid;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public class gameScreen extends ActionBarActivity {
    private final static int REQUEST_ENABLE_BT = 1;
    int hits = 0, misses = 0;
    float percentage = 100;
    Button buttonHits, buttonMisses, buttonSaveGame, buttonFire;
    TextView textViewNumHits, textViewNumMisses, textViewAccuracyPercentage;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static String address = "00:00:00:00:00:00";

    EntryDataBaseAdapter entryDBAdapter;

    private BluetoothAdapter bluetoothAdapter;
    private OutputStream outputStream;
    //private InputStream inStream;
    private Set<BluetoothDevice> btdevices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
        btdevices = bluetoothAdapter.getBondedDevices();

        entryDBAdapter = new EntryDataBaseAdapter(this);
        entryDBAdapter = entryDBAdapter.open();

        buttonSaveGame = (Button)findViewById(R.id.buttonSaveGameToDB);
        buttonHits = (Button)findViewById(R.id.buttonHit);
        buttonMisses = (Button)findViewById(R.id.buttonMiss);
        buttonFire = (Button)findViewById(R.id.buttonFireLauncher);


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

        try{
            func();
        }
        catch (Exception e)
        {
            //broke, maybe no devices available
        }

        buttonFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                //check if bluetooth is enabled on the phone
                if(bluetoothAdapter == null || !bluetoothAdapter.isEnabled())
                {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                }

                try {
                    //func();
                    String s = "1";
                    byte[] msgBuffer = s.getBytes();

                    outputStream.write(msgBuffer);
                }
                catch (Exception e)
                {
                    //broke
                }

            }
        });
    }
    private void avg() {
        float total = hits + misses;
        textViewAccuracyPercentage.setText(Float.toString((hits/total) * 100));
    }
    private void func() throws IOException {
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        if(bondedDevices.size() > 0){
            //BluetoothDevice[] devices = (BluetoothDevice[]) bondedDevices.toArray();//
            BluetoothDevice device = bondedDevices.iterator().next(); //one of these lines breaks
            ParcelUuid [] uuids = device.getUuids();//device.getUuids();
            BluetoothSocket socket = device.createRfcommSocketToServiceRecord(MY_UUID/*uuids[0].getUuid()*/);
            socket.connect();
            outputStream = socket.getOutputStream();


        }
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
