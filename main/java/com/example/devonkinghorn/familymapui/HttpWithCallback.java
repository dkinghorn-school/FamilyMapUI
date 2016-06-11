package com.example.devonkinghorn.familymapui;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.devonkinghorn.familymapui.Container.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Created by devonkinghorn on 6/9/16.
 */
public class HttpWithCallback {
  public interface Callback<V> {
    V run(String str);
  }
  Callback callback;
  JSONObject data;
  String authorization;
  protected class performTask extends AsyncTask<URL,Integer,String> {
    @Override
    protected String doInBackground(URL... urls){
      HttpClient client = new HttpClient();
      JSONObject toReturn = new JSONObject();
      long totalSize = 0;
//      String response = client.post(urls[0].first,urls[0].second);
      JSONObject response = new JSONObject();
      try {

        String str = client.post(urls[0],data, authorization);
//        if(str != null){
//          toReturn.put("people", new JSONObject(str));
//        }
        return str;

//        loggedIn(response);

      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println(response.toString());
//      JSONObject toReturn = new JSONObject();
      return "";
    }
    @Override
    protected void onPostExecute(String response){
      callback.run(response);
    }
  }

  public void post(URL url, JSONObject body, String authorization, Callback callback){
    this.callback = callback;
    this.authorization = authorization;
    data = body;
    if (body == null){
      data = new JSONObject();
    }
    new performTask().execute(url);
//    final URL urlf = url;
//    final JSONObject bodyf = body;
//    final String auth = authorization;
//    new Thread(() -> {
//      try {
//        System.out.println(url.toString());
//        System.out.println(body.toString());
//        System.out.println(authorization);
//        HttpURLConnection connection = (HttpURLConnection) urlf.openConnection();
//        connection.setDoOutput(true);
//        connection.setChunkedStreamingMode(0);
//        connection.setRequestProperty ("Authorization", auth);
//        String jsonOutput = bodyf.toString();
//        OutputStream os = connection.getOutputStream();
//        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
//        osw.write(jsonOutput);
//        osw.flush();
//        osw.close();
////      InputStream in = new BufferedInputStream(connection.getInputStream());
////      StringBuilder str = new StringBuilder();
//        if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//          InputStream responseBody = connection.getInputStream();
//          BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody, "UTF-8"));
//          callback.run(reader.readLine());
//        }
//
//      }catch (Exception e){
//        e.printStackTrace();
//      }
//    }).run();
  }
}
