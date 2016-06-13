package com.example.devonkinghorn.familymapui.container;

import android.graphics.Color;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

/**
 * Created by devonkinghorn on 6/8/16.
 */
public class LifeEvent implements Comparable<LifeEvent>{
  String eventId;
  String personId;
  Double latitude;
  Double longitude;
  String country;
  String city;
  String description;
  Integer year;
  String descendant;
  float color;
  LatLng coordinates;
  Boolean visible;

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
  private float getMarkerColor(){
    switch(description){
      case "marriage":
        return BitmapDescriptorFactory.HUE_AZURE;
      case "christening":
        return BitmapDescriptorFactory.HUE_BLUE;
      case "baptism":
        return BitmapDescriptorFactory.HUE_CYAN;
      case "census":
        return BitmapDescriptorFactory.HUE_GREEN;
      case "birth":
        return BitmapDescriptorFactory.HUE_MAGENTA;
      case "death":
        return BitmapDescriptorFactory.HUE_ORANGE;
//      case "ae":
//        return BitmapDescriptorFactory.HUE_RED;
//      case "vb":
//        return BitmapDescriptorFactory.HUE_ROSE;
      default:
        return BitmapDescriptorFactory.HUE_VIOLET;
    }
  }

  public LifeEvent(JSONObject json){
    try{
      eventId = json.getString("eventID");
      personId = json.getString("personID");
      latitude = json.getDouble("latitude");
      longitude = json.getDouble("longitude");
      country = json.getString("country");
      city = json.getString("city");
      description = json.getString("description").toLowerCase();
      year = json.getInt("year");
      coordinates = new LatLng(latitude,longitude);
      color = getMarkerColor();
      visible = true;
//      Model.eventTypes.add(description);
    }catch(Exception e){
      e.printStackTrace();
    }

  }

  @Override
  public int compareTo(LifeEvent lifeEvent){
    if(description.equals("birth") || lifeEvent.description.equals("death"))
      return 1;
    if(lifeEvent.description.equals("birth") || description.equals("death"))
      return -1;

    if(lifeEvent.year != 0 && year != 0){
      if(lifeEvent.year - year != 0){
        return lifeEvent.year - year;
      }
    }
    return this.description.compareTo(lifeEvent.description);

  }
  public Boolean getVisible(){
    return visible;
  }
  public String getEventId() {
    return eventId;
  }

  public String getPersonId() {
    return personId;
  }

  public Double getLatitude() {
    return latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public String getCountry() {
    return country;
  }

  public String getCity() {
    return city;
  }

  public String getDescription() {
    return description;
  }

  public Integer getYear() {
    return year;
  }

  public String getDescendant() {
    return descendant;
  }

  public float getColor() {
    return color;
  }

  public LatLng getCoordinates() {
    return coordinates;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof LifeEvent)) return false;

    LifeEvent lifeEvent = (LifeEvent) o;

    if (!eventId.equals(lifeEvent.eventId)) return false;
    if (!personId.equals(lifeEvent.personId)) return false;
    if (latitude != null ? !latitude.equals(lifeEvent.latitude) : lifeEvent.latitude != null) return false;
    if (longitude != null ? !longitude.equals(lifeEvent.longitude) : lifeEvent.longitude != null)
      return false;
    if (country != null ? !country.equals(lifeEvent.country) : lifeEvent.country != null) return false;
    if (city != null ? !city.equals(lifeEvent.city) : lifeEvent.city != null) return false;
    if (description != null ? !description.equals(lifeEvent.description) : lifeEvent.description != null)
      return false;
    if (year != null ? !year.equals(lifeEvent.year) : lifeEvent.year != null) return false;
    if (descendant != null ? !descendant.equals(lifeEvent.descendant) : lifeEvent.descendant != null)
      return false;
    return color == lifeEvent.color;

  }

  @Override
  public int hashCode() {
    return eventId.hashCode();
  }
}
