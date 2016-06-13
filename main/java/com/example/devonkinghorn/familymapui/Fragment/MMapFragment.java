package com.example.devonkinghorn.familymapui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.devonkinghorn.familymapui.R;
import com.example.devonkinghorn.familymapui.container.LifeEvent;
import com.example.devonkinghorn.familymapui.container.Model;
import com.example.devonkinghorn.familymapui.container.Settings;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by devonkinghorn on 6/10/16.
 */
public class MMapFragment extends Fragment {
  SupportMapFragment mapFragment;
  GoogleMap map;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    View myInflatedView = inflater.inflate(R.layout.map, container, false);
    mapFragment = SupportMapFragment.newInstance();
    mapFragment.getMapAsync((GoogleMap map)->{
      Model.map = map;
      this.map = map;
      loadIconsandPolylines();
      if(Model.focusEvent != null){
        map.animateCamera(CameraUpdateFactory.newLatLng(Model.focusEvent.getCoordinates()));
      }
      Model.map.setOnMarkerClickListener((Marker m)-> {
        System.out.println(m.getTitle());
//      LifeEvent event;
        for (LifeEvent e : Model.lifeEvents) {
          if (e.getEventId().equals(m.getTitle())) {
            Model.map.animateCamera(CameraUpdateFactory.newLatLng(e.getCoordinates()));
            Model.focusEvent = e;
            //todo make new activity
            mapClicked();
//            Intent intent = new Intent(getActivity(), MapActivity.class);
//            startActivity(intent);

            break;
          }
        }
        return true;
      });

    });
    FragmentManager manager = getFragmentManager();
    manager.beginTransaction().replace(R.id.mapContainer, mapFragment).commit();
//    fragmentTransaction.addToBackStack(null);
//    fragmentTransaction.commit();
    return myInflatedView;
  }
  private Runnable callback;
  private void mapClicked(){
    if(callback != null)
      callback.run();
  }
  public void setOnMapClickListener(Runnable callback){
    this.callback = callback;
  }

  @Override
  public void onResume() {
    super.onResume();
    loadIconsandPolylines();
  }
  public void updateMap(){
    loadIconsandPolylines();
  }
  private  void loadIconsandPolylines(){
    if(Model.lifeEvents != null && map != null) {
      map.clear();
      for (LifeEvent lifeEvent : Model.lifeEvents) {
        if(Settings.eventFilters.get(lifeEvent.getDescription())) {
          map.addMarker(new MarkerOptions()
                          .position(lifeEvent.getCoordinates())
                          .title(lifeEvent.getEventId())
                          .icon(BitmapDescriptorFactory.defaultMarker(lifeEvent.getColor()))
                          .visible(lifeEvent.getVisible())
          );
        }
      }
      if(Model.focusEvent != null){
        map.animateCamera(CameraUpdateFactory.newLatLng(Model.focusEvent.getCoordinates()));
        drawPolylines();
      }
    }

  }
  private void drawPolylines(){

  }
}
