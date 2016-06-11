package com.example.devonkinghorn.familymapui.Search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.devonkinghorn.familymapui.R;

/**
 * Created by devonkinghorn on 6/8/16.
 */
public class SearchActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.search_layout);

  }

  public void search(String input){
    //get all people
    //person.toString.toLower().contains(input.toLower())

    //repeat for lifeEvents

  }
  private void onFinish(){

  }

}
