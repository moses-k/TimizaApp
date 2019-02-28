package com.example.billysunday.uwezo.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.billysunday.uwezo.Drawer.About;
import com.example.billysunday.uwezo.Drawer.Locate_us;
import com.example.billysunday.uwezo.Drawer.My_account;
import com.example.billysunday.uwezo.R;
import com.example.billysunday.uwezo.Drawer.Settings;

public class Logout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    boolean status = false;

    @Override
    protected void onCreate(Bundle saveInsatanceState){
        super.onCreate(saveInsatanceState);
        setContentView(R.layout.activity_my_account);

        mToolbar = (Toolbar) findViewById(R.id.nav_actionbar);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
                // finish();
            }
        } );

        return builder;
    }



    public AlertDialog.Builder buildDialog2(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder( c );
        builder.setTitle( "Alert" );
        builder.setMessage( "A technical error has occured!!. Our technitian has been contacted please try later... Press ok " );

        builder.setPositiveButton( "Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //finish();
            }
        } );
        return builder;
    }

    public boolean onOptionItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.nav_myaccout){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSeleted(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.nav_myaccout){
            Intent searchIntent = new Intent(Logout.this, My_account.class);
            startActivity(searchIntent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }else if(id == R.id.nav_notifications){
            Intent searchIntent = new Intent(Logout.this, Notification.class);
            startActivity(searchIntent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        }else if(id == R.id.nav_settings){
            Intent searchIntent = new Intent(Logout.this, Settings.class);
            startActivity(searchIntent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }else if(id == R.id.nav_locate) {
            Intent searchIntent = new Intent(Logout.this, Locate_us.class);
            startActivity(searchIntent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }else if(id == R.id.nav_about) {
            Intent searchIntent = new Intent(Logout.this, About.class);
            startActivity(searchIntent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }else if(id == R.id.nav_logout) {
            Intent searchIntent = new Intent(Logout.this, Logout.class);
            startActivity(searchIntent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
