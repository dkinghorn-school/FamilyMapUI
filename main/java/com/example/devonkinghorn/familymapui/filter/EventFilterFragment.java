package com.example.devonkinghorn.familymapui.filter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.devonkinghorn.familymapui.R;
import com.example.devonkinghorn.familymapui.container.Settings;

/**
 * Created by devonkinghorn on 6/11/16.
 */
public class EventFilterFragment extends Fragment {
  @Override
  /**
   * put in bundle a string of the Event to filter called eventType
   */
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState){
    Bundle args = getArguments();
    String eventType = args.getString("eventType");
    if(!Settings.eventFilters.containsKey(eventType)){
      return null;
    }
    View myInflatedView = inflater.inflate(R.layout.event_filter, container, false);


    ((TextView)myInflatedView.findViewById(R.id.filterName)).setText(eventType + " Events");
    ((TextView)myInflatedView.findViewById(R.id.filterDescription)).setText("Filter by " + eventType + " Events");
    Switch mySwitch = (Switch)myInflatedView.findViewById(R.id.filterSwitch);
    mySwitch.setChecked(Settings.eventFilters.get(eventType));
    mySwitch.setOnCheckedChangeListener((CompoundButton b, boolean filter)->{
      Settings.eventFilters.put(eventType,filter);
    });

    return myInflatedView;
  }
}
