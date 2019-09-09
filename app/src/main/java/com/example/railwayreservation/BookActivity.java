package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class BookActivity extends AppCompatActivity {
    public void confirmBook(View view) {
        SQLiteDatabase myData = this.openOrCreateDatabase ( "RailData",MODE_PRIVATE,null );

        TextView name1 = findViewById ( R.id.editText4 );
        TextView num = findViewById ( R.id.editText9 );
        TextView src1 = findViewById ( R.id.editText10 );
        TextView dest1 = findViewById ( R.id.editText11 );
        TextView date1 = findViewById ( R.id.editText12 );
        TextView seats = findViewById ( R.id.editText13 );
        String tName = name1.getText ().toString ();
        String tNum = num.getText ().toString ();
        String sCity = src1.getText ().toString ();
        String dCity  =dest1.getText ().toString ();
        String tDate = date1.getText ().toString ();
        String seat =seats.getText ().toString ();
        int tnum = Integer.parseInt ( tNum );
        int seatTotal =  Integer.parseInt ( seat );
        //Test Case
        //SQLiteDatabase sql = this.getRea
        Cursor c;
        c = myData.rawQuery (  "select trainNo,availability from train where trainNo="+tnum+"",null );
         int answer  =c.getColumnIndex ( "trainNo" );
        int availIndex =  c.getColumnIndex ( "availability" );
        Log.i("trno",Integer.toString (answer));
        Log.i("ai",Integer.toString ( availIndex ));
        Log.i("cursor count",Integer.toString ( c.getCount () ));
        String seatsAvail="";
        String answers="";
       /* String answers = c.getString ( answer );
        String seatsAvail = c.getString ( availIndex );
       */
        if(c.getCount() > 0) {
            c.moveToFirst();
            seatsAvail = c.getString(c.getColumnIndex("availability"));
            answers = c.getString ( c.getColumnIndex ( "trainNo" ) );
            Log.i("tno",seatsAvail);
            Log.i("aval",answers);
        } int result =(Integer.parseInt ( answers ));
        int seatNum = Integer.parseInt ( seatsAvail );
        Random  random = new Random ( );
        int r = random.nextInt (1000);

        if(tnum==result)
        {
            if(seatNum>=seatTotal)
            {
                Log.i("booked","confirm");
                myData.execSQL ( "insert into ticket values("+r+",'"+sCity+"','"+dest1+"','"+date1+"',"+result+")" );
                //Cursor co;
                int temp = seatNum-seatTotal;
                ContentValues newValues = new ContentValues();
                //newValues.put("trainNo", trainNum);
                newValues.put("availability",temp );

                myData.update("train", newValues, "trainNo=?",new String[]{answers});
                Toast.makeText ( getApplicationContext (),"Booking Successfull",Toast.LENGTH_SHORT ).show ();
                c = myData.rawQuery ( "select * from ticket", null );

                int ticketIndex = c.getColumnIndex ( "ticketId" );
                int srcindex = c.getColumnIndex ( "src" );
                int destIndex = c.getColumnIndex ( "dest" );
                int dateIndex = c.getColumnIndex ( "date" );
                int seatindex = c.getColumnIndex ( "seat" );
                int trainNom = c.getColumnIndex ( "trainNo" );
                c.moveToFirst ();
                while( c.moveToNext ()){
                    Log.i ( "number", c.getString ( ticketIndex ) );
                    Log.i ( "train num", c.getString ( trainNom) );
                    Log.i ( "train num", c.getString (srcindex) );
                    Log.i ( "train num", c.getString ( seatindex) );

                    //adapter.add(newUser);
                }
                c.close ();
                Intent intent = new Intent ( getApplicationContext (),CustomerActivity.class );
                startActivity ( intent );
            }
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_book );

    }
}
