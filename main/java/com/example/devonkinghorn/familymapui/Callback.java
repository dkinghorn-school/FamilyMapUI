package com.example.devonkinghorn.familymapui;

import android.os.AsyncTask;

/**
 * Created by devonkinghorn on 6/9/16.
 */


public class Callback extends AsyncTask<Void, Void, String> {

  public interface TaskListener {
    public void onFinished(String result);
  }

  // This is the reference to the associated listener
  private final TaskListener taskListener;

  public Callback(TaskListener listener) {
    // The listener reference is passed in through the constructor
    this.taskListener = listener;
  }

  @Override
  protected String doInBackground(Void... params) {
//    return doSomething();
    return "";
  }

  @Override
  protected void onPostExecute(String result) {
    super.onPostExecute(result);

    // In onPostExecute we check if the listener is valid
    if(this.taskListener != null) {

      // And if it is we call the callback function on it.
      this.taskListener.onFinished(result);
    }
  }
}