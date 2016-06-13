package com.example.devonkinghorn.familymapui.filter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.devonkinghorn.familymapui.R;
import com.example.devonkinghorn.familymapui.container.Settings;

import java.util.List;
import java.util.Set;

/**
 * Created by devonkinghorn on 6/11/16.
 */
public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {

  public static class ViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public Switch mySwitch;
    public TextView eventType;
    public TextView eventTypeDescription;
    public ViewHolder(View myInflatedView) {
      super(myInflatedView);
      eventType = ((TextView)myInflatedView.findViewById(R.id.filterName));
      eventTypeDescription = ((TextView)myInflatedView.findViewById(R.id.filterDescription));
      mySwitch = (Switch)myInflatedView.findViewById(R.id.filterSwitch);

    }
  }

  private List<String> eventDescriptions;
  public FilterAdapter(List<String> eventDescriptions){
    this.eventDescriptions = eventDescriptions;
  }

  @Override
  public FilterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View myInflatedView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.event_filter, parent, false);
    return new ViewHolder(myInflatedView);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    String eventFilter = eventDescriptions.get(position);
    holder.eventType.setText(eventFilter + " event");
    holder.eventTypeDescription.setText("Filter by " + eventFilter + " Events");
    holder.mySwitch.setChecked(Settings.eventFilters.get(eventFilter));
    holder.mySwitch.setOnCheckedChangeListener((CompoundButton b, boolean value)->{
      Settings.eventFilters.put(eventFilter,value);
    });
  }

  @Override
  public int getItemCount() {
    return eventDescriptions.size();
  }
}
