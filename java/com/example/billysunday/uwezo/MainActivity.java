package com.example.billysunday.uwezo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.billysunday.uwezo.Login.Login;


public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        new Handler().postDelayed( new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(MainActivity.this, Login.class );
                startActivity( homeIntent );
                finish();

            }
        },SPLASH_TIME_OUT);


    }
}


