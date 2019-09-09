package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddDetailsActivity extends AppCompatActivity {
    EditText editText3,editText5,editText8;

    public void  addD(View view)
    { SQLiteDatabase myData = this.openOrCreateDatabase("RailData", MODE_PRIVATE, null);
        editText3 = findViewById(R.id.editText3);
        editText5 = findViewById(R.id.editText5);
        editText8=findViewById ( R.id.editText8 );
        String tn ="0";
                tn=editText3.getText().toString();
        String avail ="0";
               avail =editText5.getText().toString();
               String name = editText8.getText ().toString ();
        int t =0;
        t=Integer.valueOf ( tn );
        int a = 0;
          a=      Integer.valueOf ( avail );

        myData.execSQL ( "insert into train values("+t+","+a+",'"+name+"')");
        Cursor c;
       // c = myData.rawQuery ( "select trainNo,availability from train", null );

        /*int numberIndex = c.getColumnIndex ( "trainNo" );
        int Availindex = c.getColumnIndex ( "availability" );
        c.moveToFirst ();
        do{
            Log.i ( "number", c.getString ( numberIndex ) );
            Log.i ( "available", c.getString ( Availindex ) );

        }while( c.moveToNext ());
        c.close ();*/
        Intent intent = new Intent(getApplicationContext(),AdmiinActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        Button button2  =findViewById(R.id.button2);


    }


}
