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

public class CustomerActivity extends AppCompatActivity {
    public void addBooking(View view)
    {

        Intent intent = new Intent(getApplicationContext (),BookActivity.class);
        startActivity ( intent );
    }
    public class UsersAdapter extends ArrayAdapter<User> {
        public UsersAdapter(Context context, ArrayList<User> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            User user = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user1, parent, false);
            }
            // Lookup view for data population
            TextView tvid = (TextView) convertView.findViewById(R.id.tvid);
            TextView tvsrc = (TextView) convertView.findViewById(R.id.tvsrc);
            TextView tvdest = (TextView) convertView.findViewById(R.id.tvdest);
            TextView tvdate = (TextView) convertView.findViewById(R.id.tvdate);
            TextView tvseat = (TextView) convertView.findViewById(R.id.tvseat);
            TextView tvno = (TextView) convertView.findViewById(R.id.tvtno);

            // Populate the data into the template view using the data object
            tvid.setText ( user.id);
            tvsrc.setText ( user.sroc );
            tvdest.setText(user.des);
            tvdate.setText(user.dates);
            tvseat.setText(user.seat);
            tvno.setText(user.trainno);

            // Return the completed view to render on screen
            return convertView;
            // Construct the data source

        }// Add item to adapter

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        SQLiteDatabase myData =  this.openOrCreateDatabase ( "RailData",MODE_PRIVATE,null );
        myData.execSQL ( "drop table ticket" );
        myData.execSQL ( "create table if not exists ticket(ticketId INT(10) primary key,src TEXT,dest TEXT,date TEXT,trainNo INT(10),foreign key(trainNo) references train(trainNo))" );

        final ArrayList<User> arrayOfUsers = new ArrayList<>();
// Create the adapter to convert the array to views
        final UsersAdapter adapter1 = new UsersAdapter (this, arrayOfUsers);
// Attach the adapter to a ListView
        final ListView listView1 = (ListView) findViewById(R.id.listView1);
        listView1.setAdapter(adapter1);
        //final SQLiteDatabase myData = this.openOrCreateDatabase("RailData", MODE_PRIVATE, null);
        //SQLiteDatabase myData = this.openOrCreateDatabase("RailData", MODE_PRIVATE, null)) {
        //myData.execSQL ( "drop table train" );
        Cursor c=null;
       // myData.execSQL ( "create table if not exists train(trainNo INT(10) primary key,availability INT(3),trainName TEXT)" );
        //  myData.execSQL ( "delete from train" );
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
          User newUser = new User ("Ticket Id: "+c.getString ( ticketIndex )+"  ",
                    " "+ c.getString ( srcindex )+" to ",
                    ""+c.getString ( destIndex )+"  ",
                    "Date: "+c.getString ( dateIndex )+"  ",
                    "Seats: "+c.getString ( seatindex )+"  ",
                    "Train No: "+c.getString ( trainNom )+" ");
            adapter1.add(newUser);
        }
        c.close ();


    }



    public static class User {
        public String id;
        public String sroc;
        public String des;
        public String dates;
        public String seat;
        public  String trainno;


        public User(String id,String sroc,String des,String dates,String seat,String trainno) {
          this.id = id;
          this.sroc =sroc;
          this.des =des;
          this.dates=dates;
          this.seat=seat;
          this.trainno=trainno;
        }

        public static void fromJson(JSONArray jsonArray) {

        }
    }
}
