package com.example.devonkinghorn.familymapui.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.devonkinghorn.familymapui.Container.Event;
import com.example.devonkinghorn.familymapui.R;

/**
 * Created by devonkinghorn on 6/9/16.
 */
public class LifeEventFragment extends Fragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.life_event, container, false);
  }

}
