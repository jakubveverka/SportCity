package com.hackprague.sportcity;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hackprague.sportcity.models.Event;
import com.hackprague.sportcity.models.EventHolder;
import com.hackprague.sportcity.utilities.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DashboardActivity extends AppCompatActivity
  implements NavigationView.OnNavigationItemSelectedListener {

  FirebaseRecyclerAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dashboard);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //  .setAction("Action", null).show();
        Intent i = new Intent(DashboardActivity.this, NewEventActivity.class);
        startActivity(i);
      }
    });

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
      this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    //ListView lwEvents = (ListView) findViewById(R.id.lw_events);

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("events");

    String key = ref.push().getKey();
    Map<String, String> map = new HashMap<>();
    map.put("sport", "hokej");
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd.MM.yyyy");
    String currentDateandTime = sdf.format(new Date());
    map.put("time", currentDateandTime);
    map.put("place", "Prague");

    ref.child(key).setValue(map);

    /*mAdapter = new FirebaseListAdapter<Event>(this, Event.class, android.R.layout.two_line_list_item, ref) {
      @Override
      protected void populateView(EventHolder eventHolder, Event event, int position) {
        ((TextView)view.findViewById(android.R.id.text1)).setText(event.getSport());
        ((TextView)view.findViewById(android.R.id.text2)).setText(event.getPlace() + " " + event.getTime());

      }
    };
    lwEvents.setAdapter(mAdapter);*/

    Logger.d("teest");

    RecyclerView recycler = (RecyclerView) findViewById(R.id.events_recycler);
    recycler.setHasFixedSize(true);
    recycler.setLayoutManager(new LinearLayoutManager(this));

    mAdapter = new FirebaseRecyclerAdapter<Event, EventHolder>(Event.class, android.R.layout.two_line_list_item, EventHolder.class, ref) {
      @Override
      public void populateViewHolder(EventHolder eventHolder, Event event, int position) {
        eventHolder.setSport(event.getSport());
        eventHolder.setPlaceAndTime(event.getPlace() + " " + event.getTime());
      }
    };
    recycler.setAdapter(mAdapter);
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
    getMenuInflater().inflate(R.menu.dashboard, menu);
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

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mAdapter.cleanup();
  }

}
