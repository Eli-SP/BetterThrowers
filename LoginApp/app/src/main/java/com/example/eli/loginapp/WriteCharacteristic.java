package com.example.eli.loginapp;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.AsyncTask;

/**
 * Created by Eli on 2/17/2015.
 */
public class WriteCharacteristic extends AsyncTask<String, Void, String> {

    public BluetoothGatt mGatt;
    public BluetoothGattCharacteristic mCharacteristic;

    public WriteCharacteristic(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic){
        mGatt = gatt;
        mCharacteristic = characteristic;
    }

    @Override
    protected String doInBackground(String... urls) {
        mGatt.writeCharacteristic(mCharacteristic);
        return null;
    }
}