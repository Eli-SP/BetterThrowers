package com.example.eli.loginapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.database.sqlite.SQLiteDatabase;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    Button buttonSignIn, buttonSignUp, buttonScan, buttonDelete;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create an instance of sqlite db
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        buttonSignIn = (Button)findViewById(R.id.buttonSignIn);
        buttonSignUp = (Button)findViewById(R.id.buttonSignUp);
        buttonScan = (Button)findViewById(R.id.buttonScan);
        buttonDelete = (Button)findViewById(R.id.buttonDeleteData);
        //set the onclick listener to the signup button
        buttonSignUp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //create intent for signup activity and start it
                Intent intentSignUp = new Intent(getApplicationContext(), signup.class);
                startActivity(intentSignUp);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDataBaseAdapter.Upgrade();
                Toast.makeText(getApplicationContext(), "Delete Successful", Toast.LENGTH_LONG);
            }
        });
    }

    //method to handle click event of sign in button
    public void signIn(View v){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.activity_sign_in); // ?
        dialog.setTitle("Login");

        final EditText editTextUserName = (EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
        final EditText editTextPassword = (EditText)dialog.findViewById(R.id.editTextPasswordToLogin);

        Button buttonSignIn = (Button)dialog.findViewById(R.id.buttonSignIn);

        //set on click listener
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                //get user name and password
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

                //fetch password from database
                String storedPassword = loginDataBaseAdapter.getSingleEntry(userName);
                //check if password matches
                if(userName == "Admin") {
                    Intent intent = new Intent(getApplicationContext(), admin_home_screen.class);
                    startActivity(intent);
                    loginDataBaseAdapter.close();
                }
                else if (password.equals(storedPassword))
                {
                    Toast.makeText(MainActivity.this, "Congrats: Login was Successful", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    Intent intentHomeScreen = new Intent(getApplicationContext(), user_home_screen.class);
                    loginDataBaseAdapter.close();
                    startActivity(intentHomeScreen);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //close db
        loginDataBaseAdapter.close();
    }
}
