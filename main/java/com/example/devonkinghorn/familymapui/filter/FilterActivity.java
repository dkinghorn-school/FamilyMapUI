package com.example.devonkinghorn.familymapui.filter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.devonkinghorn.familymapui.R;
import com.example.devonkinghorn.familymapui.container.Model;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by devonkinghorn on 6/11/16.
 */
public class FilterActivity extends AppCompatActivity {
  private RecyclerView mRecyclerView;
  private RecyclerView.Adapter mAdapter;
  private RecyclerView.LayoutManager mLayoutManager;

  private int filterCount;
  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.filter_activity);
    filterCount = Model.eventTypes.size();

    mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
    mLayoutManager = new LinearLayoutManager(this);
    ((LinearLayoutManager)mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(mLayoutManager);
    List<String> eventTypes = new LinkedList();
    for(String str:Model.eventTypes){
      eventTypes.add(str);
      System.out.println(str);
    }
    mRecyclerView.setAdapter(new FilterAdapter(eventTypes));
  }
  @Override
  public void onResume(){
    super.onResume();

  }
}
