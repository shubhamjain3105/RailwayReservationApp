package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button signin;
    String user;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu ( menu );
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
         return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case R.id.developId:
                Intent nameAct = new Intent ( getApplicationContext (),NameActivity.class );
                startActivity ( nameAct );
                return true;

            default :
                return false;
        }


    }

    public void Signin(View view)
    {username =  findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        signin = findViewById(R.id.button);
        user = username.getText().toString();
        Log.i("Username", String.valueOf(user));

        if(user.equals("admin")||user.equals ( "ADMIN" ))
        {
            Intent intent = new Intent(getApplicationContext(),AdmiinActivity.class);
            startActivity(intent);
        }
        else if(user.equals ( "customer" )||user.equals ( "CUSTOMER" )||user.equals ( "1" ))
        {
            Intent intent = new Intent(getApplicationContext(),CustomerActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText ( getApplicationContext (),"Invalid Login", Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView label = new TextView(this);
        label.setText("Leaks are bad");

       // setContentView(label);

    }
}
