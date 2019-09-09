package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateActivity extends AppCompatActivity {
    int value;
    public void   updateData(View view)
    {
        Intent intent = getIntent ();
        //   int intValue=0;
        String intValue = intent.getStringExtra ( "TrainData");EditText editText7 = findViewById ( R.id.editText7 );
        String avail = editText7.getText ().toString ();
        int availNum = Integer.parseInt ( avail );
        SQLiteDatabase myData = this.openOrCreateDatabase ( "RailData", MODE_PRIVATE, null) ;
        Cursor c;
        ContentValues newValues = new ContentValues();
        //newValues.put("trainNo", trainNum);
        newValues.put("availability", availNum);

        myData.update("train", newValues, "trainNo=?",new String[]{intValue});
        ///myData.update("train", newValues, "trainNo="+intValue+"", null);
       // myData.execSQL ( "update train set  availability  =\"+avail+\" where trainNo=\"+value+\"" );
        //   myData.execSQL ( "update train set availability  =\"+availNum+\"where trainNo=\"+intValue+\""  );
        Intent myintent = new Intent ( getApplicationContext (),AdmiinActivity.class ) ;
        startActivity ( myintent );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_update );
    Button button = findViewById ( R.id.button3 );
        TextView textView4  =(TextView) findViewById ( (R.id.textView4) );
        Intent intent = getIntent ();
        //   int intValue=0;
        String intValue = intent.getStringExtra ( "TrainData");
        final int value = Integer.parseInt ( intValue );
        Log.i("text",intValue);
        textView4.setText ( intValue );

        Log.i("text",intValue);







    }
}
