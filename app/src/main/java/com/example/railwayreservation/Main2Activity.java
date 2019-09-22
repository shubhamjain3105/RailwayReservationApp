package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main2 );
        ImageView imageview5 = (ImageView) findViewById ( R.id.imageView5 );
        //imageview5.setImageResource ( R.drawable.fewg );
      // imageview5.setVisibility ( View.VISIBLE );
       // new Thread{};



        new CountDownTimer (4000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                // do something end times 5s
                Intent intent =new Intent ( getApplicationContext (),MainActivity.class );
                startActivity ( intent );
            }

        }.start();
//imageview5.setVisibility ( View.INVISIBLE );

       // imageview5.setImageResource ( R.drawable.we2 );
       // /imageview5.setVisibility ( View.VISIBLE );



    }
}
