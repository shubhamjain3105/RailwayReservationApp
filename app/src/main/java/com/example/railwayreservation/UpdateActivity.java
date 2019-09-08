package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
    public void   updateData(View view)
    {
        Intent intent = getIntent ();
     //   int intValue=0;
        String intValue = intent.getStringExtra ( "TrainData");
         int value = Integer.parseInt ( intValue );
        EditText editText6 = findViewById ( R.id.editText6 );
        EditText editText7 = findViewById ( R.id.editText7 );
        String  train = editText6.getText ().toString ();
        int trainNum = Integer.parseInt ( train );
        String avail = editText7.getText ().toString ();
        int availNum = Integer.parseInt ( avail );
        SQLiteDatabase myData = this.openOrCreateDatabase ( "RailData", MODE_PRIVATE, null) ;
        Cursor c;
        //ContentValues newValues = new ContentValues();
       // newValues.put("trainNo", trainNum);
      //  newValues.put("availability", availNum);

      //  myData.update("train", newValues, "trainNo=\"+intValue+\"", null);
       ///myData.update("train", newValues, "trainNo="+intValue+"", null);
         myData.execSQL ( "update train set trainNo = \"+train+\" , availability  =\"+avail+\" where trainNo=\"+value+\"" );
      //   myData.execSQL ( "update train set availability  =\"+availNum+\"where trainNo=\"+intValue+\""  );
        Intent myintent = new Intent ( getApplicationContext (),AdmiinActivity.class ) ;
        startActivity ( myintent );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_update );
    Button button = findViewById ( R.id.button3 );
    }
}
