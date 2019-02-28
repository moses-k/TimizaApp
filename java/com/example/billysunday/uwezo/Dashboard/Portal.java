package com.example.billysunday.uwezo.Dashboard;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.billysunday.uwezo.R;

import java.util.Timer;
import java.util.TimerTask;

public class Portal extends AppCompatActivity{
    private WebView webView;
    private Toolbar mToolbar;
    ProgressBar mprogressBar;
    Handler handler;
    Runnable runnable;
    Timer timer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);

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
        },4000,1000);
        webView = (WebView)findViewById(R.id.portal);
        //to load web on app not on browser
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://portal.tukenya.ac.ke/");
        //add ToolBar
        mToolbar = (Toolbar) findViewById(R.id.nav_actionbar);
        setSupportActionBar(mToolbar);


         //add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if( id== android.R.id.home){
                //end this activity
                this.finish();
            }
            return super.onOptionsItemSelected(item);
        }

}
