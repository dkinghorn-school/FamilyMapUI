package com.example.devonkinghorn.familymapui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by devonkinghorn on 6/9/16.
 */
public class SettingsActivity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.settings_activity);
    HttpWithCallback.post((String str) ->{
      Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG);
      return "";
    });
  }
}
