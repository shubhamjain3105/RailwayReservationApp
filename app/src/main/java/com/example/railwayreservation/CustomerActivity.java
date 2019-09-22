package com.example.railwayreservation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;

public class CustomerActivity extends AppCompatActivity {
    public void addBooking(View view)
    {

        Intent intent = new Intent(getApplicationContext (),BookActivity.class);
        startActivity ( intent );
    }
    public void goBack(View view)
    {
        Intent intent = new Intent(getApplicationContext (),MainActivity.class);
        startActivity ( intent );
    }
    public void showResult(View view)
    {
        SQLiteDatabase myData =  this.openOrCreateDatabase ( "RailData",MODE_PRIVATE,null );
        EditText editText14 = findViewById ( R.id.editText14 );
        String result = editText14.getText ().toString ();
        try{
            int  intResult;
            try{  intResult = Integer.parseInt ( result );}
            catch (NumberFormatException n)
            {
            intResult = 0;}

    try{

            Cursor c = myData.rawQuery ( "select * from train where trainNo like " + intResult + " or trainName like '" + result + "'" ,null);
            Log.i("cursor",c.toString ());
            int tnom1 = c.getColumnIndex ( "trainNo" );
            int tavl = c.getColumnIndex ( "availability" );
            int tnae  = c.getColumnIndex ( "trainName" );
           c.moveToFirst ();
            if(c.getCount ()==0)
            { Toast.makeText ( getApplicationContext (),"Record Not found",Toast.LENGTH_SHORT ).show ();}
            else {
                Log.i ( "train no", c.getString ( tnom1 ) );
                Log.i ( "avail", c.getString ( tavl ) );
                Log.i ( "name", c.getString ( tnae ) );
                new AlertDialog .Builder(CustomerActivity.this)
                        .setIcon ( android.R.drawable.ic_dialog_info )
                        .setTitle ( "Train Details" )
                        .setMessage (
                                "Train No: "+c.getString ( tnom1 )+"\nSeats Availability: "+c.getString (  tavl)+"\nTrain Name: "+c.getString (  tnae)+"\n"
                        )
                        .setPositiveButton ( "Book Seat", new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent bewIntent  = new Intent ( getApplicationContext (),BookActivity.class );
                                startActivity ( bewIntent );
                            }
                        } )
                        .setNegativeButton ( "Search Other", new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent newIntent = new Intent ( getApplicationContext (),CustomerActivity.class );
                                startActivity ( newIntent );
                            }
                        } ).show ();

            }
            //Toast.makeText ( getApplicationContext (),"Record found",Toast.LENGTH_SHORT ).show ();
        }catch (IllegalStateException  s)
    { Toast.makeText ( getApplicationContext (),"Record Not found",Toast.LENGTH_SHORT ).show ();}
        }
        catch(IllegalStateException e)
        {
            Toast.makeText ( getApplicationContext (),"Record Not found",Toast.LENGTH_SHORT ).show ();
        }


    }
    public class UsersAdapter1 extends ArrayAdapter<User2> {
        public UsersAdapter1(Context context, ArrayList<User2> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            User2 user2 = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user1, parent, false);
            }
            // Lookup view for data population
          //  TextView tvid = (TextView) convertView.findViewById(R.id.tvid);
          //  TextView tvsrc = (TextView) convertView.findViewById(R.id.tvsrc);
            TextView tvdest = (TextView) convertView.findViewById(R.id.tvdest);
            TextView tvdate = (TextView) convertView.findViewById(R.id.tvdate);
            TextView tvseat = (TextView) convertView.findViewById(R.id.tvseat);
            TextView tvno = (TextView) convertView.findViewById(R.id.tvtno);

            // Populate the data into the template view using the data object
           // tvid.setText ( user.id);
            //tvsrc.setText ( user.sroc );
            tvdest.setText(user2.des);
            tvdate.setText(user2.dates);
            tvseat.setText(user2.seat);
            tvno.setText(user2.trainno);

            // Return the completed view to render on screen
            return convertView;
            // Construct the data source

        }// Add item to adapter

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView label = new TextView(this);
        label.setText("Leaks are bad");

        setContentView(label);
        setContentView(R.layout.activity_customer);
        final SQLiteDatabase myData =  this.openOrCreateDatabase ( "RailData",MODE_PRIVATE,null );
  //myData.execSQL ( "drop table ticket" );
        myData.execSQL ( "create table if not exists ticket(ticketId INT(10) primary key,srcName TEXT,destName TEXT,datetod TEXT,seat int(3),trainNo INT(10),foreign key(trainNo) references train(trainNo))" );


        final ArrayList<User2> arrayOfUsers2 = new ArrayList<User2>();
// Create the adapter to convert the array to views
        final UsersAdapter1 adapter2 = new UsersAdapter1 (this, arrayOfUsers2);
// Attach the adapter to a ListView
        final ListView listView1 = (ListView) findViewById(R.id.listView1);
        listView1.setAdapter(adapter2);
        Cursor c1;

        c1 = myData.rawQuery ( "select destName,datetod,seat,trainNo from ticket", null );
    Log.i("cursor",c1.toString ());
      //  int ticketIndex = c1.getColumnIndex ( "ticketId" );
        //int srcindex = c1.getColumnIndex ( "src" );
        int destIndex = c1.getColumnIndex ( "destName" );
       int dateIndex = c1.getColumnIndex ( "datetod" );
     //  Log.i("dates",String.valueOf (  dateIndex));
        int seatindex = c1.getColumnIndex ( "seat" );
      int trainNom = c1.getColumnIndex ( "trainNo" );
        c1.moveToFirst ();
        Log.i("cursor next",c1.toString ());
        while( c1.moveToNext ()){
          Log.i ( "dest", c1.getString ( destIndex ) );
          Log.i("seat num now",c1.getString ( seatindex ));
          Log.i("train num",c1.getString ( trainNom ));
          Log.i("date aaj ka",c1.getString ( dateIndex ));
       //     Log.i ( "train num", c1.getString ( trainNom) );
         User2 newUser2 = new User2 (
                  "Trip to: "+c1.getString ( destIndex )+"  ","on "+c1.getString ( dateIndex)+"  ",
                  "Seats: "+c1.getString ( seatindex )+"  ","Train No: "+c1.getString ( trainNom )+" "
         );
         adapter2.add(newUser2);
        }
        c1.close ();
        int deletedSeat=0;
       // myData.execSQL ( "drop trigger t1" )
        //;myData.execSQL ( "drop trigger t4" );
        myData.execSQL ( "create trigger if not exists t4" +
                " after delete " +
                "on ticket" +" begin"+
                " update train  set availability=availability +old.seat where trainNo= old.trainNo;" +
                "end " );
         listView1.setOnItemLongClickListener ( new AdapterView.OnItemLongClickListener () {
             @Override
             public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    final int itemTodelete=i;
                 new AlertDialog.Builder ( CustomerActivity.this )
                         .setIcon ( android.R.drawable.ic_input_delete )
                         .setTitle ( "Cancel Reservation" )
                         .setMessage ( "Are you Sure!?" )
                         .setPositiveButton ( "Yes", new DialogInterface.OnClickListener () {
                             @Override
                             public void onClick(DialogInterface dialogInterface, int i) {
                                 Log.i("Index",Integer.toString(itemTodelete));
                                 String delete =arrayOfUsers2.get ( itemTodelete ).trainno.toString ();
                                 String intValue = delete.replaceAll("[^0-9]", "");
                                 Log.i("Deleted",intValue);
                                 myData.execSQL ( "delete from ticket where trainNo="+intValue+"" );
                                 adapter2.notifyDataSetChanged ();
                                Intent intent  = new Intent ( getApplicationContext (),CustomerActivity.class );
                                 startActivity ( intent);
                             }
                         } )
                         .setNegativeButton ( "No",null )
                         .show();


                 return false;
             }
         } );

        
    }



    public static class User2 {
      //  public String id;
     //   public String sroc;
       public String des;
        public String dates;
        public String seat;
        public  String trainno;


        public User2(String des,String dates,String seat,String trainno) {
         // this.id = id;
        //  this.sroc =sroc;
          this.des =des;
          this.dates=dates;
          this.seat=seat;
         this.trainno=trainno;
        }

        public static void fromJson(JSONArray jsonArray) {

        }
    }
}
//"Ticket Id: "+c1.getString ( ticketIndex )+"  ",
//
//String sroc,String des,
//" "+ c1.getString ( srcindex )+" to ",
//
//                    " "+c1.getString ( destIndex )+"  ",
/*
*
* <TextView
    android:id="@+id/tvid"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="TrainName" />
    <TextView
        android:id="@+id/tvsrc"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="TrainName" />*/