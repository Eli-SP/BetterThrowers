package com.example.eli.loginapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class signup extends ActionBarActivity {

    EditText editTextUserName, editTextPassword, editTextConfirmPassword;
    Button buttonCreateAccount;

    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        editTextUserName = (EditText)findViewById(R.id.editTextUserName);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText)findViewById(R.id.editTextConfirmPassword);

        buttonCreateAccount = (Button)findViewById(R.id.buttonCreateAccount);
        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();

                if (userName.equals("")||password.equals("")||confirmPassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match" , Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.equals(confirmPassword))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    loginDataBaseAdapter.insertEntry(userName, password);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }

}
