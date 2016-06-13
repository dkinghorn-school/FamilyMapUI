package com.example.devonkinghorn.familymapui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.devonkinghorn.familymapui.container.Model;
import com.example.devonkinghorn.familymapui.container.Settings;
import com.example.devonkinghorn.familymapui.fragment.MMapFragment;
import com.example.devonkinghorn.familymapui.fragment.PersonFragment;
import com.example.devonkinghorn.familymapui.personPackage.PersonActivity;

/**
 * Created by devonkinghorn on 6/9/16.
 */
public class MapActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //initializes settings]
    setContentView(R.layout.activity_main);

    // add in googlemap
//    MapFragment mMapFragment = MapFragment.newInstance();
//    android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//    fragmentTransaction.add(R.id.fragment_container, mMapFragment);
//    fragmentTransaction.replace(R.id.fragment_person, personFragment);
//    fragmentTransaction.addToBackStack(null);
//    fragmentTransaction.commit();

    MMapFragment mapFragment = new MMapFragment();
    mapFragment.setOnMapClickListener(()->{
      updateEventView();
    });
    FragmentManager manager = getSupportFragmentManager();
    manager.beginTransaction().add(R.id.fragment_container, mapFragment).commit();
    updateEventView();

//    PersonFragment personFragment = new PersonFragment();
//    Bundle bundle = new Bundle();
//    if(Model.focusEvent != null) {
//      bundle.putString("eventId", Model.focusEvent.getEventId());
//    }
//    personFragment.setArguments(bundle);
//    manager = getSupportFragmentManager();
//    manager.beginTransaction()
//            .replace(R.id.fragment_person,personFragment).commit();
//    personFragment.onClickCallback((String eventId)->{
//      Intent intent = new Intent(getApplicationContext(),PersonActivity.class);
//      startActivity(intent);
//    });
//    mMapFragment.getMapAsync(this);
  }
  private void updateEventView(){
    FragmentManager manager = getSupportFragmentManager();
    PersonFragment personFragment = new PersonFragment();
    Bundle bundle = new Bundle();
    if(Model.focusEvent != null) {
      bundle.putString("eventId", Model.focusEvent.getEventId());
    }
    personFragment.setArguments(bundle);
    manager = getSupportFragmentManager();
    manager.beginTransaction()
            .replace(R.id.fragment_person,personFragment).commit();
    personFragment.onClickCallback((String eventId)->{
      Intent intent = new Intent(getApplicationContext(),PersonActivity.class);
      startActivity(intent);
    });
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.go_up_menu, menu);
    return true;
//    return super.onCreateOptionsMenu(menu);
  }
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
    switch (item.getItemId()) {
      case R.id.chevronUp:{
        super.onBackPressed();
      }
      default:
        finish();
    }
    return true;
  }

}
