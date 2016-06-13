package com.example.devonkinghorn.familymapui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.devonkinghorn.familymapui.container.LifeEvent;
import com.example.devonkinghorn.familymapui.container.Model;
import com.example.devonkinghorn.familymapui.container.Settings;
import com.example.devonkinghorn.familymapui.container.UserInfo;
import com.example.devonkinghorn.familymapui.fragment.MMapFragment;
import com.example.devonkinghorn.familymapui.fragment.PersonFragment;
import com.example.devonkinghorn.familymapui.personPackage.PersonActivity;
import com.example.devonkinghorn.familymapui.search.SearchActivity;
import com.example.devonkinghorn.familymapui.filter.FilterActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{
  private EditText username;
  private EditText password;
  private EditText server;
  private EditText port;
  private Button submit;

  private MMapFragment mapFragment;

  private void updateBottomFragment(){
    //add in text at bottom
    PersonFragment personFragment = new PersonFragment();
    Bundle bundle = new Bundle();
//    if(Model.focusEvent != null) {
//      bundle.putString("eventId", Model.focusEvent.getEventId());
//    }
    personFragment.setArguments(bundle);
    FragmentManager manager = getSupportFragmentManager();
    manager.beginTransaction()
            .replace(R.id.fragment_person,personFragment).commit();
    personFragment.onClickCallback((String eventId)->{
      Intent intent = new Intent(getApplicationContext(),PersonActivity.class);
      startActivity(intent);
    });
  }
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //initializes settings
    Settings.initialize();


    setContentView(R.layout.activity_main);

    // add in googlemap
//    MapFragment mMapFragment = MapFragment.newInstance();
//    android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//    fragmentTransaction.add(R.id.fragment_container, mMapFragment);
//    fragmentTransaction.replace(R.id.fragment_person, personFragment);
//    fragmentTransaction.addToBackStack(null);
//    fragmentTransaction.commit();
    FragmentManager manager = getSupportFragmentManager();
    mapFragment = new MMapFragment();
    manager.beginTransaction().add(R.id.fragment_container, mapFragment).commit();
    mapFragment.setOnMapClickListener(()->{
            Intent intent = new Intent(getApplicationContext(), MapActivity.class);
            startActivity(intent);
    });


    updateBottomFragment();
//    mMapFragment.getMapAsync(this);


  }
//  @Override
//  public void onMapReady(GoogleMap googleMap){
//    Model.map = googleMap;
//    Model.loadIcons();
//    // when marker is clicked
//    Model.map.setOnMarkerClickListener((Marker m)->{
//      System.out.println(m.getTitle());
////      LifeEvent event;
//      for(LifeEvent e: Model.lifeEvents){
//        if(e.getEventId().equals(m.getTitle())){
//          Model.map.animateCamera(CameraUpdateFactory.newLatLng(e.getCoordinates()));
//          Model.focusEvent = e;
//          updateLifeEventFragment();
//          break;
//        }
//      }
//
//      return true;
//    });
//  }

  @Override
  protected void onResume(){
    super.onResume();
    getUserInfo();
  }
  private void getUserInfo(){
    if(UserInfo.userName == null){
      Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
      startActivity(intent);

    }
    loadData();

  }
  private void loadData(){
    new Model().loadData((Object n)->{
      mapFragment.updateMap();
      if(Model.lifeEvents != null) {

//        for (LifeEvent lifeEvent : Model.lifeEvents) {
//          if(Settings.eventFilters.get(lifeEvent.getDescription())) {
//            Model.map.addMarker(new MarkerOptions()
//                            .position(lifeEvent.getCoordinates())
//                            .title(lifeEvent.getEventId())
////                .icon(BitmapDescriptorFactory.defaultMarker(lifeEvent.color))
//                            .visible(lifeEvent.getVisible())
//            );
//          }
//        }
//        if(Model.focusEvent != null ){
//          Model.map.animateCamera(CameraUpdateFactory.newLatLng(Model.focusEvent.getCoordinates()));
//        }
      }
    });
  }
  private void addPerson(JSONObject json){

  }
  private void dataLoaded(JSONObject res){
    System.out.println(res.toString());
    try{
      JSONArray people = res.getJSONArray("people");
      JSONArray events = res.getJSONArray("lifeEvents");
      for(int i = 0; i < people.length(); i++){
        addPerson(people.getJSONObject(i));
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_fragment_menu, menu);
    return true;
//    return super.onCreateOptionsMenu(menu);
  }
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
    switch (item.getItemId()) {
      case R.id.search:
        Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
        startActivity(intent);
        return true;
      case R.id.filter:
        intent = new Intent(getApplicationContext(),FilterActivity.class);
        startActivity(intent);
        return true;
      case R.id.settings:
        intent = new Intent(getApplicationContext(),SettingsActivity.class);
        startActivity(intent);
        return true;
    }
    return true;
  }

  //TODO: do this
//  @Override
//  public boolean onOptionsItemSelected(MenuItem item) {
//    switch(item.getItemId()){
////      case R.id.menu_up_button:
////        Intent intent = new Intent(getActivity(),MainActivity.class);
////        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////        startActivity(intent);
////        getActivity().finish();
////        return true;
////
////      case android.R.id.home:
////        getActivity().finish();
////        return true;
//      default:
//        return super.onOptionsItemSelected(item);
//    }
//  }




}
