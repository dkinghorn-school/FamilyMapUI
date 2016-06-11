package com.example.devonkinghorn.familymapui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.devonkinghorn.familymapui.Container.LifeEvent;
import com.example.devonkinghorn.familymapui.Container.Model;
import com.example.devonkinghorn.familymapui.Container.UserInfo;
import com.example.devonkinghorn.familymapui.Fragment.PersonFragment;
import com.example.devonkinghorn.familymapui.Search.SearchActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
  private EditText username;
  private EditText password;
  private EditText server;
  private EditText port;
  private Button submit;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
//    Fragment
    PersonFragment personFragment = new PersonFragment();
    Bundle bundle = new Bundle();
    bundle.putString("title","yes");
    personFragment.setArguments(bundle);
    MapFragment mMapFragment = MapFragment.newInstance();
    android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//    fragmentTransaction.add(R.id.fragment_container, mMapFragment);
//    fragmentTransaction.replace(R.id.fragment_person, personFragment);
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
    FragmentManager manager = getSupportFragmentManager();
    manager.beginTransaction()
            .add(R.id.fragment_person,personFragment).commit();
    mMapFragment.getMapAsync(this);

  }
  @Override
  public void onMapReady(GoogleMap googleMap){
    Model.map = googleMap;
    Model.loadIcons();
    Model.map.setOnMarkerClickListener((Marker m)->{
      System.out.println(m.getTitle());
//      LifeEvent event;
      for(LifeEvent e: Model.lifeEvents){
        if(e.getEventId().equals(m.getTitle())){
          Model.map.animateCamera(CameraUpdateFactory.newLatLng(e.getCoordinates()));
          Model.focusEvent = e;
          break;
        }
      }

      return true;
    });
  }

  @Override
  protected void onResume(){
    super.onResume();
    getUserInfo();
    Model.loadIcons();
  }
  private void getUserInfo(){
    if(UserInfo.userName == null){
      Intent intent = new Intent(getApplicationContext(),LoginActivity.class);

      startActivity(intent);
//        Intent intent = new Intent(getActivity(),MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////        startActivity(intent);
////        getActivity().finish();
////        return true;
    } else {
      loadData();
    }

  }
  private void loadData(){
    new Model().loadData();
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
//  protected class GetData extends AsyncTask<URL,Integer,JSONObject> {
//    @Override
//    protected JSONObject doInBackground(URL... urls){
//      HttpClient client = new HttpClient();
//      JSONObject toReturn = new JSONObject();
//      long totalSize = 0;
////      String response = client.post(urls[0].first,urls[0].second);
//      JSONObject response = new JSONObject();
//      try {
//
//        String str = client.post(urls[0],new JSONObject(),UserInfo.authorization);
//        if(str != null){
//          toReturn.put("people", new JSONObject(str));
//        }
//        str = client.post(urls[1],new JSONObject(),UserInfo.authorization);
//        if(str != null){
//          toReturn.put("lifeEvents", new JSONObject(str));
//        }
////        loggedIn(response);
//
//      } catch (JSONException e) {
//        Toast.makeText(getApplicationContext(),"error Loging data",Toast.LENGTH_SHORT).show();
//        e.printStackTrace();
//      }
//      System.out.println(response.toString());
////      JSONObject toReturn = new JSONObject();
//      return toReturn;
//    }
//    @Override
//    protected void onPostExecute(JSONObject response){
//      dataLoaded(response);
//    }
//  }

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

        return true;
      case R.id.settings:
        intent = new Intent(getApplicationContext(),SettingsActivity.class);
        startActivity(intent);
        return true;
    }
    return false;
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
