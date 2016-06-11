package com.example.devonkinghorn.familymapui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.devonkinghorn.familymapui.Container.Model;
import com.example.devonkinghorn.familymapui.Container.UserInfo;

import org.json.JSONObject;

import java.net.URL;

/**
 * Created by devonkinghorn on 6/9/16.
 */
public class SettingsActivity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.settings_activity);
    try {
      new HttpWithCallback().post(new URL("http://192.168.1.6:8080/person"), new JSONObject(), UserInfo.authorization, (String str) -> {
        Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
        return "";
      });
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
