package com.example.billysunday.uwezo.Login;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.billysunday.uwezo.R;
import com.example.billysunday.uwezo.dashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;


public class Login extends AppCompatActivity  implements View.OnClickListener {
    EditText UsernameEt, PasswordEt;
    ProgressBar mprogressBar;
    Handler handler;
    Runnable runnable;
    Timer timer;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // mAuth = FirebaseAuth.getInstance();

        UsernameEt = (EditText) findViewById(R.id.etUserName);
        PasswordEt = (EditText) findViewById(R.id.etPassword);
        mprogressBar = (ProgressBar) findViewById(R.id.progressBar);
        mprogressBar.setVisibility(View.GONE);

        //initialise the onclick buttons
        findViewById(R.id.btnLogin).setOnClickListener(this);
        findViewById(R.id.btnreg).setOnClickListener(this);
        findViewById(R.id.btnhome).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnLogin:
                //  Toast.makeText(getApplicationContext(),"User registered",Toast.LENGTH_SHORT).show();

                Login(view);
                break;

            case R.id.btnreg:
                startActivity(new Intent(this, Register.class));
                break;

            case  R.id.btnhome:
                startActivity(new Intent(this,dashboard.class));

        }

    }

    public void Login(View view) {

        if (!isConnected(Login.this)) buildDialog(Login.this).show();
        else {
            // Toast.makeText( MainActivity.this, "Welcome", Toast.LENGTH_SHORT ).show();

            // setContentView( R.layout.activity_main );

            String username = UsernameEt.getText().toString().trim();
            String password = PasswordEt.getText().toString().trim();
            String type = "login";

            //check if  is empty
            if(username.isEmpty()){
                UsernameEt.setError("username is required");
                UsernameEt.requestFocus();
                return;
            }

            //check if password is empty
            if (password.isEmpty()) {
                PasswordEt.setError("Password is required");
                PasswordEt.requestFocus();
                return;
            }
            //check if password is <6 char
            if(password.length()<6){
                PasswordEt.setError("Minimun length of password should be 6");
                PasswordEt.requestFocus();
                return;
            }


            //progressbar
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
            }, 2000, 1000);


            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, username, password);

/*
        //create register in firebase and add user
        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener <AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task <AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "User registered successfull", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Some thing went wrong", Toast.LENGTH_SHORT).show();


                        }
                    }
                });

               */
        }
    }



    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                ;

                // finish();
            }
        });

        return builder;
    }

}

  //0711852921  mouse
