package com.example.devonkinghorn.familymapui.container;

import android.graphics.Color;

import java.util.Map;
import java.util.Set;

/**
 * Created by devonkinghorn on 6/11/16.
 */
public class Settings {

  static int mapType;
  public static LineSetting lifeStoryLineSetting;
  public static LineSetting familyLineSetting;
  public static LineSetting spouseLineSetting;
  static boolean showLifeStoryLines;
  static boolean showFamilyTreeLines;
  static boolean showSpouseLines;
  static boolean initialized;
  public static Map<String,Boolean> eventFilters;
  /**
   * initializes values to the beginning values
   */
  public static void initialize(){
    if(initialized){
      return;
    } else {
      initialized = true;
      showLifeStoryLines = true;
      showFamilyTreeLines = true;
      showSpouseLines = true;
      lifeStoryLineSetting = new LineSetting(Color.GREEN, true);
      familyLineSetting = new LineSetting(Color.RED, true);
      spouseLineSetting = new LineSetting(Color.BLUE, true);
    }
  }




  public static int getMapType() {
    return mapType;
  }

  public static void setMapType(int mapType) {
    Settings.mapType = mapType;
  }

  public static boolean isShowLifeStoryLines() {
    return showLifeStoryLines;
  }

  public static void setShowLifeStoryLines(boolean showLifeStoryLines) {
    Settings.showLifeStoryLines = showLifeStoryLines;
  }

  public static boolean isShowFamilyTreeLines() {
    return showFamilyTreeLines;
  }

  public static void setShowFamilyTreeLines(boolean showFamilyTreeLines) {
    Settings.showFamilyTreeLines = showFamilyTreeLines;
  }

  public static boolean isShowSpouseLines() {
    return showSpouseLines;
  }

  public static void setShowSpouseLines(boolean showSpouseLines) {
    Settings.showSpouseLines = showSpouseLines;
  }
}
