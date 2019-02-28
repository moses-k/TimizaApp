package com.example.billysunday.uwezo.Login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.billysunday.uwezo.R;

import java.util.Timer;
import java.util.TimerTask;

public class Register extends AppCompatActivity {
    EditText name, username, password, confirmpassword ,phonenumber;
    ProgressBar mprogressBar;
    Handler handler;
    Runnable runnable;
    Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );
        name = (EditText) findViewById( R.id.etName );
        username = (EditText) findViewById( R.id.etUserName );
        phonenumber = (EditText) findViewById( R.id.etPassword );
        password = (EditText) findViewById( R.id.etPassword );
        confirmpassword = (EditText) findViewById(R.id.etconfirmPassword);
        mprogressBar = (ProgressBar) findViewById(R.id.progressBar);
        mprogressBar.setVisibility(View.GONE);

    }

    public void OnLogin(View view) {

        if (!isConnected( Register.this )) buildDialog( Register.this ).show();
        else {
            // Toast.makeText( MainActivity.this, "Welcome", Toast.LENGTH_SHORT ).show();

            // setContentView( R.layout.activity_main );


            String str_name = name.getText().toString();
            String str_username = username.getText().toString();
            String str_phonenumber = phonenumber.getText().toString();
            String str_password = password.getText().toString();
            String str_confirmpassword = confirmpassword.getText().toString();
            String type = "register";


            //check name if empty
            if(str_name.isEmpty()){
                name.setError("Name is required");
                name.requestFocus();
                return;
            }
            if ((str_username.isEmpty())){
                username.setError("Username is required");
                username.requestFocus();
                return;
            }
            //check phonenumber if empty

            if ((str_phonenumber.isEmpty())){
                phonenumber.setError("Phonenumner is required");
                phonenumber.requestFocus();
                return;
            }
            //check if passwoed match
            if(!str_confirmpassword.matches(str_password)){
                confirmpassword.setError("Password does not match");
                confirmpassword.findFocus();
                return;
            }

            mprogressBar = (ProgressBar) findViewById(R.id.progressBar);
            mprogressBar.setVisibility(View.VISIBLE);
            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    mprogressBar.setVisibility(View.GONE);
                    timer.cancel();
                }
            };
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(runnable);
                }
            },2000,1000);

            BackgroundWorker backgroundWorker = new BackgroundWorker( this );
            backgroundWorker.execute( type,str_name, str_username,str_phonenumber, str_password ,str_confirmpassword);

        }
    }

    public void Openlogin (View view){
        startActivity( new Intent( this, Login.class ) );
    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService( Context.CONNECTIVITY_SERVICE );
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo( ConnectivityManager.TYPE_WIFI );
            android.net.NetworkInfo mobile = cm.getNetworkInfo( ConnectivityManager.TYPE_MOBILE );

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder( c );
        builder.setTitle( "No Internet Connection" );
        builder.setMessage( "You need to have Mobile Data or wifi to access this. Press ok to Exit" );

        builder.setPositiveButton( "Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                ;

                // finish();
            }
        } );

        return builder;


    }
}
