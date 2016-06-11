package com.example.devonkinghorn.familymapui.Container;

import android.graphics.Color;

import com.example.devonkinghorn.familymapui.HttpWithCallback;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by devonkinghorn on 6/8/16.
 */
public class Model {
  public static volatile Map<String,Person> people;
  public static volatile Set<LifeEvent> lifeEvents;
  public static volatile Person focusPerson;
  public static volatile LifeEvent focusEvent;
  public static volatile Set<Filter> filters;
  public static volatile LineSettings lineSettings;
  public static volatile int mapType;
  public static GoogleMap map;
  public static Set<String> eventTypes;

//  {  person JSON
//    "descendant":"d",
//          "personID":"491zb6l6-mz82-kton-6asv-nphmjlu81ge6",
//          "firstName":"Ezra",
//          "lastName":"Carmouche",
//          "gender":"m",
//          "spouse":"3bxo60x3-60kp-397x-p248-48h6it34o488"
//  },
//{  event
//  "eventID":"68jy4tg6-67io-8fan-6w2q-10ijtlb5h437",
//        "personID":"491zb6l6-mz82-kton-6asv-nphmjlu81ge6",
//        "latitude":24.4667,
//        "longitude":39.6,
//        "country":"Saudi Arabia",
//        "city":"Medina",
//        "description":"birth",
//        "year":"1910",
//        "descendant":"d"
//},
  private void loadPeople(JSONArray array){
    people = new HashMap();
    try{
      for(int i = 0; i < array.length(); i++){
        Person per = new Person(array.getJSONObject(i));
        people.put(per.personId,per);
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }
  private void loadEvents(JSONArray array){
    eventTypes = new HashSet();
    lifeEvents = new HashSet();
    try{
      for(int i = 0; i < array.length(); i++){
        LifeEvent lifeEvent = new LifeEvent(array.getJSONObject(i));
        eventTypes.add(lifeEvent.description);
        lifeEvents.add(lifeEvent);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    if(map!=null){
      loadIcons();
    }

  }

  public void loadData() {
    try {
      URL people = new URL("http://" +
              UserInfo.url + ":" +
              UserInfo.port + "/person");
      URL event = new URL("http://" +
              UserInfo.url + ":" +
              UserInfo.port + "/event");
      new HttpWithCallback().post(people, new JSONObject(), UserInfo.authorization, (String str) -> {
        System.out.println(str);
        try {
          loadPeople(new JSONObject(str).getJSONArray("data"));
        } catch (Exception e) {
          e.printStackTrace();
        }
        return "";
      });
      new HttpWithCallback().post(event, new JSONObject(), UserInfo.authorization, (String str) -> {
        try {
          System.out.println(str);
          loadEvents(new JSONObject(str).getJSONArray("data"));
        } catch (Exception e) {
          e.printStackTrace();
        }
        System.out.println(str);
        return "";
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
    public static void loadIcons(){
    if(lifeEvents != null) {
      for (LifeEvent lifeEvent : Model.lifeEvents) {
        map.addMarker(new MarkerOptions()
                .position(lifeEvent.getCoordinates())
                .title(lifeEvent.getEventId())
//                .icon(BitmapDescriptorFactory.defaultMarker(lifeEvent.color))
                .visible(lifeEvent.visible)
        );
      }
    }

  }
}
