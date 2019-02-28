package com.example.billysunday.uwezo;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.billysunday.uwezo.Dashboard.Contact;
import com.example.billysunday.uwezo.Dashboard.Elearning;
import com.example.billysunday.uwezo.Dashboard.Examination;
import com.example.billysunday.uwezo.Dashboard.Fee_statement;
import com.example.billysunday.uwezo.Dashboard.Library;
import com.example.billysunday.uwezo.Dashboard.Management;
import com.example.billysunday.uwezo.Dashboard.News;
import com.example.billysunday.uwezo.Dashboard.Portal;
import com.example.billysunday.uwezo.Dashboard.Programs;
import com.example.billysunday.uwezo.Dashboard.website;
import com.example.billysunday.uwezo.Fragments.AboutFragment;
import com.example.billysunday.uwezo.Fragments.LocateUsFragment;
import com.example.billysunday.uwezo.Fragments.My_acountFragment;
import com.example.billysunday.uwezo.Fragments.NotificationFragment;
import com.example.billysunday.uwezo.Fragments.SettingsFragment;
import com.example.billysunday.uwezo.Fragments.Homefragment;
import com.example.billysunday.uwezo.Login.Login;

public class dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    boolean status = false;
    private WebView webView;
    //private ActionBar actionBar;
    private ActionBar actionbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mToolbar = (Toolbar) findViewById(R.id.nav_actionbar);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        //display the supportActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Load home fragment on the empty containeri dashboaed
        Homefragment homefragment= new Homefragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.container,homefragment,homefragment.getTag())
                .commit();

    }

    //mToggle onclick
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //3dots onclick
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    //@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,
                        new Homefragment()).commit();
                break;
            case R.id.nav_myaccout:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,
                        new My_acountFragment()).commit();
                break;
            case R.id.nav_notifications:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,
                        new NotificationFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,
                        new SettingsFragment()).commit();
                break;
            case R.id.nav_locate:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,
                        new LocateUsFragment()).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,
                        new AboutFragment()).commit();
                break;
            case R.id.nav_logout:
                AlertDialog.Builder ab = new AlertDialog.Builder(dashboard.this);

                ab.setTitle("confirm");
                ab.setIcon(R.drawable.ic_launch_black_24dp);
                ab.setMessage("Are you sure you want to logout?");

                ab.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(dashboard.this, "ok", Toast.LENGTH_SHORT).show();
                        Login();
                    }
                });

                ab.setNegativeButton("cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(dashboard.this, "cancle", Toast.LENGTH_SHORT).show();
                    }
                });
                ab.show();                break;
            case R.id.share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.send:
                Toast.makeText(this, "Send successful", Toast.LENGTH_SHORT).show();
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }



    public void Onwebsite(View view) {

        if (!isConnected(dashboard.this)) buildDialog(dashboard.this).show();
        else {
            startActivity(new Intent(this, website.class));
        }
    }

    public void Onprograms(View view) {
        if (!isConnected(dashboard.this)) buildDialog(dashboard.this).show();
        else {
            startActivity(new Intent(this, Programs.class));
        }
    }

    public void Onportal(View view) {
        if (!isConnected(dashboard.this)) buildDialog(dashboard.this).show();
        else {
            startActivity(new Intent(this, Portal.class));
        }
    }

    public void Onfee_statement(View view) {
        if (!isConnected(dashboard.this)) buildDialog(dashboard.this).show();
        else {
            startActivity(new Intent(this, Fee_statement.class));
        }
    }

    public void Onelearning(View view) {
        if (!isConnected(dashboard.this)) buildDialog(dashboard.this).show();
        else {
            startActivity(new Intent(this, Elearning.class));
        }
    }

    public void Onlibrary(View view) {
        if (!isConnected(dashboard.this)) buildDialog(dashboard.this).show();
        else {
            startActivity(new Intent(this, Library.class));
        }
    }

    public void Onexamination(View view) {
        if (!isConnected(dashboard.this)) buildDialog(dashboard.this).show();
        else {
            startActivity(new Intent(this, Examination.class));
        }
    }

    public void Oncontact(View view) {
        if (!isConnected(dashboard.this)) buildDialog(dashboard.this).show();
        else {
            startActivity(new Intent(this, Contact.class));
        }
    }

    public void Onsocial(View view) {
        if (!isConnected(dashboard.this)) buildDialog(dashboard.this).show();
        else {
            Toast.makeText(dashboard.this, "Sorry this information is temporarily unavailabe!!! Our Technician is working on it. Try latter",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void Onmanagement(View view) {
        if (!isConnected(dashboard.this)) buildDialog(dashboard.this).show();
        else {
            startActivity(new Intent(this, Management.class));

        }
    }

    public void Onnews(View view) {
        if (!isConnected(dashboard.this)) buildDialog(dashboard.this).show();
        else {
            startActivity(new Intent(this, News.class));
        }
    }

    public void Onsatuk(View view) {
        if (!isConnected(dashboard.this)) buildDialog(dashboard.this).show();
        else {
            buildDialog2(dashboard.this).show();
        }
    }

    //check for internet connectivity
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
                // finish();
            }
        });

        return builder;
    }

    public AlertDialog.Builder buildDialog2(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Alert");
        builder.setMessage("A technical error has occured!!. Our technitian has been contacted please try later... Press ok ");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //finish();
            }
        });
        return builder;
    }
    public void Login () {
        startActivity(new Intent(this, Login.class));
    }


}









