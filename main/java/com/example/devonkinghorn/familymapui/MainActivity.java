package com.example.devonkinghorn.familymapui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.devonkinghorn.familymapui.Container.UserInfo;
import com.example.devonkinghorn.familymapui.Search.SearchActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
  private EditText username;
  private EditText password;
  private EditText server;
  private EditText port;
  private Button submit;
  private GoogleMap map;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    MapFragment mMapFragment = MapFragment.newInstance();
    android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    fragmentTransaction.add(R.id.fragment_container, mMapFragment);
    fragmentTransaction.commit();
    mMapFragment.getMapAsync(this);

  }
  @Override
  public void onMapReady(GoogleMap googleMap){
    map = googleMap;
  }
  @Override
  protected void onResume(){
    super.onResume();
    getUserInfo();
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
    try {
      URL people = new URL("http://" +
              UserInfo.url + ":" +
              UserInfo.port + "/person");
      URL event = new URL("http://" +
              UserInfo.url + ":" +
              UserInfo.port + "/event");
      new GetData().execute(people,event);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  private void addPerson(JSONObject json){

  }
  private void dataLoaded(JSONObject res){
    System.out.println(res.toString());
    try{
      JSONArray people = res.getJSONArray("people");
      JSONArray events = res.getJSONArray("events");
      for(int i = 0; i < people.length(); i++){
        addPerson(people.getJSONObject(i));
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }
  protected class GetData extends AsyncTask<URL,Integer,JSONObject> {
    @Override
    protected JSONObject doInBackground(URL... urls){
      HttpClient client = new HttpClient();
      JSONObject toReturn = new JSONObject();
      long totalSize = 0;
//      String response = client.post(urls[0].first,urls[0].second);
      JSONObject response = new JSONObject();
      try {

        String str = client.post(urls[0],new JSONObject(),UserInfo.authorization);
        if(str != null){
          toReturn.put("people", new JSONObject(str));
        }
        str = client.post(urls[1],new JSONObject(),UserInfo.authorization);
        if(str != null){
          toReturn.put("events", new JSONObject(str));
        }
//        loggedIn(response);

      } catch (JSONException e) {
        Toast.makeText(getApplicationContext(),"error Loging data",Toast.LENGTH_SHORT).show();
        e.printStackTrace();
      }
      System.out.println(response.toString());
//      JSONObject toReturn = new JSONObject();
      return toReturn;
    }
    @Override
    protected void onPostExecute(JSONObject response){
      dataLoaded(response);
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
