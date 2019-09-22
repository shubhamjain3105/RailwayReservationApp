package com.example.railwayreservation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class BookActivity extends AppCompatActivity {
    EditText name1,num,src1,dest1,date1,seats;
    public void confirmBook(View view) {
        SQLiteDatabase myData = this.openOrCreateDatabase ( "RailData", MODE_PRIVATE, null );

       EditText name1 = findViewById ( R.id.editText4 );
        EditText num = findViewById ( R.id.editText9 );
        EditText src1 = findViewById ( R.id.editText10 );
        EditText dest1 = findViewById ( R.id.editText11 );
        EditText date1 =
                (EditText)findViewById ( R.id.editText12 );
        EditText seats = findViewById ( R.id.editText13 );
        String tName = name1.getText ().toString ();
        String tNum = num.getText ().toString ();
        String sCity = src1.getText ().toString ();
        String dCity = dest1.getText ().toString ();
        dest1.setText ( dCity );
        Log.i("DEstination city",dCity);
        String tDate =   date1.getText ().toString ();
        Log.i("DATE NOW", String.valueOf ( date1 ) );
        String seat = seats.getText ().toString ();
        int tnum = Integer.parseInt ( tNum );
        Log.i("train number",String.valueOf ( tnum ));
        int seatTotal = Integer.parseInt ( seat );
        //Test Case
        //SQLiteDatabase sql = this.getRea
         Cursor c=null;

        try {
            c = myData.rawQuery ( "select trainNo,availability from train where trainNo=" + tnum + "", null );
          // c = myData.rawQuery ( "select trainNo,availability from train where trainNo=" + tnum + "", null );
            int answer = c.getColumnIndex ( "trainNo" );
            int availIndex = c.getColumnIndex ( "availability" );

            Log.i ( "trno", Integer.toString ( answer ) );
            Log.i ( "ai", Integer.toString ( availIndex ) );
            Log.i ( "cursor count", Integer.toString ( c.getCount () ) );
            String seatsAvail = "";
            String answers = "";
       /* String answers = c.getString ( answer );
        String seatsAvail = c.getString ( availIndex );
       */
            if (c.getCount () > 0) {
                c.moveToFirst ();
                seatsAvail = c.getString ( c.getColumnIndex ( "availability" ) );
                answers = c.getString ( c.getColumnIndex ( "trainNo" ) );
                Log.i ( "tno", seatsAvail );
                Log.i ( "aval", answers );
            }
            int result = (Integer.parseInt ( answers ));
            int seatNum = Integer.parseInt ( seatsAvail );
            Random random = new Random ();
            int r = random.nextInt ( 1000 );

            if (tnum == result) {
                if (seatNum >= seatTotal) {
                    Log.i ( "booked", "confirm" );
                    int temp = seatNum - seatTotal;
                    Log.i ( "destination is ", dCity );
                    myData.execSQL ( "insert into ticket values(" + r + ",'" + sCity + "','" + dCity + "','" + tDate + "'," + seatTotal + "," + result + ")" );
                    Cursor c1;
                    // int temp = seatNum - seatTotal;
                    ContentValues newValues = new ContentValues ();
                    //newValues.put("trainNo", trainNum);
                    newValues.put ( "availability", temp );

                    myData.update ( "train", newValues, "trainNo=?", new String[]{answers} );
                    Toast.makeText ( getApplicationContext (), "Booking Successfull", Toast.LENGTH_SHORT ).show ();
                    c1 = myData.rawQuery ( "select * from ticket", null );
                    Log.i ( "Check", c1.toString () );
                    int ticketIndex = c1.getColumnIndex ( "ticketId" );
                    int srcindex = c1.getColumnIndex ( "srcName" );
                    int destIndex = c1.getColumnIndex ( "destName" );
                    int dateIndex = c1.getColumnIndex ( "datetod" );
                    int seatindex = c1.getColumnIndex ( "seat" );
                    int trainNom = c1.getColumnIndex ( "trainNo" );
                    c1.moveToFirst ();
                    Log.i ( "SeatIndex", String.valueOf ( seatindex ) );
                    do {
                        Log.i ( "number", c1.getString ( ticketIndex ) );
                        Log.i ( "train num", c1.getString ( trainNom ) );
                        Log.i ( "train num", c1.getString ( srcindex ) );
                        Log.i ( "train num", c1.getString ( seatindex ) );

                        //adapter.add(newUser);
                    } while (c1.moveToNext ());
                    c1.close ();
                    c.close ();
                    Intent intent = new Intent ( getApplicationContext (), CustomerActivity.class );
                    startActivity ( intent );
                } else
                {
                    Toast.makeText (getApplicationContext () ,"Required Seats are not available",Toast.LENGTH_LONG ).show ();
                }
            }

        }



        catch(IllegalStateException e)
        {
            Log.i("message:",e.getMessage ().toString ());
           new  AlertDialog.Builder(BookActivity.this)
                   .setIcon ( android.R.drawable.ic_menu_close_clear_cancel )
                   .setTitle (  "Train no.:"+tnum+" not Available!" )
                   .setMessage ( "Book other train?" )
                   .setPositiveButton ( "Yes", new DialogInterface.OnClickListener () {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           Intent customerIntent = new Intent(getApplicationContext (),BookActivity.class);
                           startActivity ( customerIntent );
                       }
                   } )
                   .setNegativeButton ( "No", new DialogInterface.OnClickListener () {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           Intent bookIntent = new Intent(getApplicationContext (),CustomerActivity.class);
                           startActivity ( bookIntent );
                       }
                   } ).show ();
        }
        catch(NumberFormatException s)
        {    Log.i("message:",s.getMessage ().toString ());
            new  AlertDialog.Builder(BookActivity.this)
                    .setIcon ( android.R.drawable.ic_menu_close_clear_cancel )
                    .setTitle (  "Train no.:"+tnum+" not Available!" )
                    .setMessage ( "Book other train?" )
                    .setPositiveButton ( "Yes", new DialogInterface.OnClickListener () {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent customerIntent = new Intent(getApplicationContext (),BookActivity.class);
                            startActivity ( customerIntent );
                        }
                    } )
                    .setNegativeButton ( "No", new DialogInterface.OnClickListener () {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent bookIntent = new Intent(getApplicationContext (),CustomerActivity.class);
                            startActivity ( bookIntent );
                        }
                    } ).show ();

        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_book );

    }
}
