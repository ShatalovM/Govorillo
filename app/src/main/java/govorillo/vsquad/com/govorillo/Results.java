package govorillo.vsquad.com.govorillo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Results extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int errors_in_text;
    private double textspeed;
    private int repeats;
    private int textlength;
    private double result;
    private TextView tvpoints;
    private TextView famouspersonsspeak;
    private TextView stattv;
    private int xp=0;
    private ImageView butWhatP;
    private AlertDialog.Builder ad;
    private int whatButton=1;
    private ImageView MainButton;
    private String SAVED_XP = "saved_xp";
    private SharedPreferences sPref;
    private int level = 1;
    private int newlevel = 1;
    private boolean isfirst = true;
    private int newxp;
    private String s = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvpoints = (TextView) findViewById(R.id.tvpoints);
        famouspersonsspeak = (TextView) findViewById(R.id.famouspersonspeak);
        MainButton = (ImageView) findViewById(R.id.mbut);

        final Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            xp = bundle.getInt("xp");
            errors_in_text = bundle.getInt("errors_in_text");
            textspeed = bundle.getDouble("textspeed");
            repeats = bundle.getInt("repeats");
            textlength = bundle.getInt("textlength");
            isfirst = bundle.getBoolean("isfirst");
            s = bundle.getString("text");
        }


        if (xp >= 0 && xp < 50){
            level = 1;
        } else
        if (xp >= 50 && xp < 500){
            level = 2;
        } else
        if (xp >= 500 && xp < 2500){
            level = 3;
        } else
        if (xp >= 2500 && xp < 7500){
            level = 4;
        } else
        if (xp >= 7500){
            level = 5;
        }

        int result1 = (100-(int)Math.ceil(errors_in_text/textlength)-10);
        int result2 = (100-(int) Math.ceil(repeats/textlength)-10);
        double result3 = ((1.3-(Math.abs(1.3-textspeed)))*65);
        result = (int)Math.ceil(result1+result2+(int)result3)/3;
        tvpoints.setText("+ " + (int)Math.ceil(Math.abs(result)) + " баллов!");
        if (isfirst == true) {
            xp+=(int)Math.ceil(Math.abs(result));
            isfirst = false;
        }
        if (xp >= 0 && xp < 50){
            newlevel = 1;
        } else
        if (xp >= 50 && xp < 500){
            newlevel = 2;
        } else
        if (xp >= 500 && xp < 2500){
            newlevel = 3;
        } else
        if (xp >= 2500 && xp < 7500){
            newlevel = 4;
        } else
        if (xp >= 7500){
            newlevel = 5;
        }
        if(level < newlevel){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Вы достигли "+newlevel+"-го уровня!",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        if (result >= 90) famouspersonsspeak.setText("ЛИНКОЛЬН");
        if (result >= 70 && result < 90) famouspersonsspeak.setText("ЧЕРЧИЛЬ");
        if (result >= 55 && result < 70) famouspersonsspeak.setText("ДОНАЛЬД ТРАМП");
        if (result >= 35 && result < 55) famouspersonsspeak.setText("ЖИРИНОВСКИЙ");
        if (result < 35) famouspersonsspeak.setText("ВИТАЛИЙ КЛИЧКО");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt(SAVED_XP, xp);
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
