package com.example.sam.testing2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import static com.example.sam.testing2.R.layout.activity_main;
import static com.example.sam.testing2.R.layout.nav_header_main;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;
    private UserInfo userInfo;
    private DatabaseReference databaseReference;
    private TextView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inflaters are instantiated indirectly.
        //Return the handle to a system-level service by class.
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Inflate a new view hierarchy from the specified xml node.
        //View vi = inflater.inflate(R.layout.nav_header_main, null); //log.xml is your file.
        //TextView tv = (TextView)vi.findViewById(R.id.textView);
        //tv.setText("BOO");

        //authenticate firebase database, and get the current user by unique id.
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //Set the activity content from a layout resource.
        //The resource will be inflated, adding all top-level views to the activity.
        setContentView(activity_main);

        //A toolbar is a dropdown menu
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //A drawer is the thing that slides out from the left side when the triple-line icon is pushed.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        //Synchronize the state of the drawer indicator/affordance with the linked DrawerLayout.
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //Listener for handling events on navigation items.
        navigationView.setNavigationItemSelectedListener(this);

        //Called when an item in the navigation menu is selected.
        onNavigationItemSelected(navigationView.getMenu().getItem(0).setChecked(true));
        Fragment fragment = new homeScreen();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    //opens or closes the drawer when necessary after a user pushes the back button
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //checks if the current drawer is open
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        //opens the correct screen on a tap from the user.
        displaySelectedScreen(item.getItemId());

        return true;
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_rewards:
                fragment = new rewardsClass();
                break;
            case R.id.nav_menu:
                fragment = new menuClass1();
                break;
            case R.id.nav_locations:
                fragment = new locationsClass();
                break;
            case R.id.nav_settings:
                fragment = new settingActivity();
                break;
            case R.id.nav_home:
                fragment = new homeScreen();
                break;
            case R.id.nav_maps:
                fragment = new MapsActivity();
                break;
        }
        //replacing the fragment if we were already in one
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        //we need to close the drawer after the user opens a fragment.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
