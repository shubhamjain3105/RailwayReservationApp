package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;

public class ShowList extends AppCompatActivity {

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
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user3, parent, false);
            }
            // Lookup view for data population
             TextView tvid = (TextView) convertView.findViewById(R.id.tvid);
            //  TextView tvsrc = (TextView) convertView.findViewById(R.id.tvsrc);
          //  TextView tvdest = (TextView) convertView.findViewById(R.id.tvdest);
            TextView tvdate = (TextView) convertView.findViewById(R.id.tvdate);
            TextView tvseat = (TextView) convertView.findViewById(R.id.tvseat);
            TextView tvno = (TextView) convertView.findViewById(R.id.tvtno);

            // Populate the data into the template view using the data object
            // tvid.setText ( user.id);
            //tvsrc.setText ( user.sroc );
            tvid.setText(user2.id);
            tvdate.setText(user2.dates);
            tvseat.setText(user2.seat);
            tvno.setText(user2.trainno);

            // Return the completed view to render on screen
            return convertView;
            // Construct the data source

        }// Add item to adapter

    }
    public void goBack(View view)
    {
        Intent backIntent = new Intent ( getApplicationContext (),AdmiinActivity.class );
        startActivity ( backIntent );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_show_list );


        final SQLiteDatabase myData =  this.openOrCreateDatabase ( "RailData",MODE_PRIVATE,null );
        // myData.execSQL ( "drop table ticket" );
    //    myData.execSQL ( "create table if not exists ticket(ticketId INT(10) primary key,srcName TEXT,destName TEXT,datetod TEXT,seat int(3),trainNo INT(10),foreign key(trainNo) references train(trainNo))" );


        final ArrayList<User2> arrayOfUsers2 = new ArrayList<User2>();
// Create the adapter to convert the array to views
        final UsersAdapter1 adapter2 = new UsersAdapter1 (this, arrayOfUsers2);
// Attach the adapter to a ListView
        final ListView listView1 = (ListView) findViewById(R.id.listView1);
        listView1.setAdapter(adapter2);
        Cursor c1;

        c1 = myData.rawQuery ( "select ticketId,datetod,seat,trainNo from ticket", null );
        Log.i("cursor",c1.toString ());
         int ticketIndex = c1.getColumnIndex ( "ticketId" );
        //int srcindex = c1.getColumnIndex ( "src" );
       // int destIndex = c1.getColumnIndex ( "destName" );
        int dateIndex = c1.getColumnIndex ( "datetod" );
        //  Log.i("dates",String.valueOf (  dateIndex));
        int seatindex = c1.getColumnIndex ( "seat" );
        int trainNom = c1.getColumnIndex ( "trainNo" );
        c1.moveToFirst ();
        Log.i("cursor next",c1.toString ());
        while( c1.moveToNext ()){
            Log.i ( "dest", c1.getString (ticketIndex ) );
            Log.i("seat num now",c1.getString ( seatindex ));
            Log.i("train num",c1.getString ( trainNom ));
            Log.i("date aaj ka",c1.getString ( dateIndex ));
            //     Log.i ( "train num", c1.getString ( trainNom) );
            User2 newUser2 = new User2 (
                    "Ticket id: "+c1.getString ( ticketIndex )+"  ","on "+c1.getString ( dateIndex)+"  ",
                    "Seats: "+c1.getString ( seatindex )+"  ","Train No: "+c1.getString ( trainNom )+" "
            );
            adapter2.add(newUser2);
        }
        c1.close ();
    }

    public static class User2 {
          public String id;
        //   public String sroc;
       // public String des;
        public String dates;
        public String seat;
        public  String trainno;


        public User2(String id,String dates,String seat,String trainno) {
            this.id = id;
            //  this.sroc =sroc;
            //this.des =des;
            this.dates=dates;
            this.seat=seat;
            this.trainno=trainno;
        }

        public static void fromJson(JSONArray jsonArray) {

        }
    }}
