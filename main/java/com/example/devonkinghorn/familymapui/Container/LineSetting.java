package com.example.devonkinghorn.familymapui.container;

/**
 * Created by devonkinghorn on 6/8/16.
 */
public class LineSetting {
  private int color;
  private Boolean visible;

  public LineSetting(int color, boolean visible){
    this.color = color;
    this.visible = visible;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public Boolean getVisible() {
    return visible;
  }

  public void setVisible(Boolean visible) {
    this.visible = visible;
  }
}
