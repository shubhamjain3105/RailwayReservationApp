package com.example.railwayreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_name );

        TextView t1 = findViewById ( R.id.textView7 );
        TextView t2 = findViewById ( R.id.textView8 );
        TextView t3 = findViewById ( R.id.textView9 );
        TextView t4 = findViewById ( R.id.textView10 );
        TextView t5 = findViewById ( R.id.textView11 );
        TextView t6 = findViewById ( R.id.textView12 );
        TextView t7 = findViewById ( R.id.textView13 );
        TextView t8 = findViewById ( R.id.textView14 );
        TextView t9 = findViewById ( R.id.textView15 );
        TextView t10 = findViewById ( R.id.textView16 );

        new CountDownTimer (5000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent ( getApplicationContext (),MainActivity.class );
                startActivity ( intent );
            }

        }.start();
    }
}
