package com.example.railwayreservation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashSet;

public class AdmiinActivity extends AppCompatActivity {
    SQLiteDatabase myData;
    private Object adapterView;
    public  int intValue=0;

    public void  AddDetails(View view)
    {    Cursor c;
        try (SQLiteDatabase myData = this.openOrCreateDatabase("RailData", MODE_PRIVATE, null)) {
            myData.execSQL ( "create table if not exists train(trainNo INT(10),availability INT(3))" );

            // myData.execSQL("insert into train values(25421,500)");
            //  Intent intent = new Intent(getApplicationContext(),AddDetailsActivity.class);
            //startActivity(intent);
            // myData.execSQL("insert into train values(63454,399)");

        }
        Intent intent = new Intent(getApplicationContext(),AddDetailsActivity.class);
        startActivity(intent);

    }  public class UsersAdapter extends ArrayAdapter<User> {
        public UsersAdapter(Context context, ArrayList<User> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            User user = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
            }
            // Lookup view for data population
            TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
            TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
            // Populate the data into the template view using the data object
            tvName.setText(user.name);
            tvHome.setText(user.hometown);
            // Return the completed view to render on screen
            return convertView;
            // Construct the data source

        }// Add item to adapter

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admiin);
        Cursor c;
        final Intent intent = new Intent(getApplicationContext(),AdmiinActivity.class);
        final ArrayList<User> arrayOfUsers = new ArrayList<User>();
// Create the adapter to convert the array to views
        final UsersAdapter adapter = new UsersAdapter(this, arrayOfUsers);
// Attach the adapter to a ListView
       final  ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        final SQLiteDatabase myData = this.openOrCreateDatabase("RailData", MODE_PRIVATE, null);
        //myData.execSQL ( "delete from train" );
        c = myData.rawQuery ( "select trainNo,availability from train", null );

        int numberIndex = c.getColumnIndex ( "trainNo" );
        int Availindex = c.getColumnIndex ( "availability" );
        c.moveToFirst ();
        while( c.moveToNext ()){
            Log.i ( "number", c.getString ( numberIndex ) );
            Log.i ( "available", c.getString ( Availindex ) );
            User newUser = new User("Train no: "+c.getString ( numberIndex )+"          ","Availability: "+ c.getString ( Availindex ));
            adapter.add(newUser);
        }
        c.close ();
        listView.setOnItemLongClickListener ( new AdapterView.OnItemLongClickListener () {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("Index",Integer.toString(i));
            final int itemTodelete = i;
            new AlertDialog.Builder (AdmiinActivity.this)
                    .setIcon ( android.R.drawable.ic_menu_edit )
                    .setMessage ( "Choose Option" )
                    .setPositiveButton ( "Delete", new DialogInterface.OnClickListener () {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          Log.i("Index",Integer.toString(itemTodelete));
                             String delete =arrayOfUsers.get ( itemTodelete ).name.toString ();
                            String intValue = delete.replaceAll("[^0-9]", "");
                            Log.i("Deleted",intValue);
                            myData.execSQL ( "delete from train where trainNo="+intValue+"" );
                            adapter.notifyDataSetChanged ();
                            startActivity ( intent);
                        }
                    } )
                    .setNegativeButton ( "Update", new DialogInterface.OnClickListener () {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.i("Index",Integer.toString(itemTodelete));
                            String delete =arrayOfUsers.get ( itemTodelete ).name.toString ();
                            String intValue = delete.replaceAll("[^0-9]", "");
                            Log.i("Deleted",intValue);
                           // myData.execSQL ( "delete from train where trainNo="+intValue+"" );
                           // adapter.notifyDataSetChanged ();
                           // startActivity ( intent);

                            Intent intent = new Intent(getApplicationContext (),UpdateActivity.class);
                            intent.putExtra ( "TrainData",intValue );
                           // intent.putExtra ( "data",delete );
                            startActivity ( intent );
                            adapter.notifyDataSetChanged ();
                           // startActivity ( intent);
                        }
                    } )
                    .show ();

                return false;
            }
        } );
        // Or even append an entire new collection
// Fetching some data, data has now returned
// If data was JSON, convert to ArrayList of User objects.

    }
    public static class User {
        public String name;
        public String hometown;

        public User(String name, String hometown) {
            this.name = name;
            this.hometown = hometown;
        }

        public static void fromJson(JSONArray jsonArray) {

        }
    }

}
