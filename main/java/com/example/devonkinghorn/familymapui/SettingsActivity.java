package com.example.devonkinghorn.familymapui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.devonkinghorn.familymapui.container.Callback;
import com.example.devonkinghorn.familymapui.container.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by devonkinghorn on 6/9/16.
 */
public class SettingsActivity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.settings_activity);

  }
//  private static String debugger;
  private int translateColor(String color){
    switch(color){
      case "Red":
        return Color.RED;
      case "Blue":
        return Color.BLUE;
      case "Green":
        return Color.GREEN;
      default:
        return Color.BLACK;
    }
  }
  private String translateColorToString(Integer color){
    switch(color){
      case Color.RED:
        return "Red";
      case Color.BLUE:
        return "Blue";
      case Color.GREEN:
        return "Green";
      default:
        return "Black";
    }
  }
  @Override
  protected void onPostResume() {
    super.onPostResume();
    //life story toggle and color
    setSwitch(R.id.lifeStoryToggle,Settings.isShowLifeStoryLines(),(b)->{
      Settings.lifeStoryLineSetting.setVisible(b);
    });
    setSpinner(R.id.lifeStorySpinner, translateColorToString(Settings.lifeStoryLineSetting.getColor()),(String color)->{
      System.out.println(color);
      Settings.lifeStoryLineSetting.setColor(translateColor(color));
      System.out.println('c');
    });

    //family tree settings
    setSwitch(R.id.familyTreeToggle,Settings.isShowFamilyTreeLines(),(b)->{
      Settings.familyLineSetting.setVisible(b);
    });

    //spouse lines settings
    setSwitch(R.id.spouseLinesToggle,Settings.isShowSpouseLines(),(b)->{
      Settings.spouseLineSetting.setVisible(b);
    });


  }

  private ArrayAdapter<String> getSpinnerList(){
    List<String> list;

    list = new ArrayList<String>();
    list.add("Red");
    list.add("Blue");
    list.add("Green");
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
            android.R.layout.simple_spinner_item,list);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    return adapter;

  }

  private int getIndex(Spinner spinner, String myString)
  {
    int index = 0;

    for (int i=0;i<spinner.getCount();i++){
      if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
        index = i;
        break;
      }
    }
    return index;
  }
  private void setSwitch(int inputId, boolean value, Callback<Boolean> onChange){
    Switch mySwitch = (Switch)findViewById(inputId);
    mySwitch.setChecked(value);
    mySwitch.setOnCheckedChangeListener((CompoundButton b, boolean c)->{
      onChange.run(c);
    });
  }
  private void setSpinner(int inputId, String value, Callback<String> onChange){
    Spinner mySpinner = (Spinner) findViewById(inputId);
    mySpinner.setAdapter(getSpinnerList());
    mySpinner.setSelection(getIndex(mySpinner,value));
    mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

      @Override
      public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                 long arg3) {

        System.out.println(arg0.getItemAtPosition(arg2).toString());
        onChange.run(arg0.getItemAtPosition(arg2).toString());
      }

      @Override
      public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

      }});

  }
}
