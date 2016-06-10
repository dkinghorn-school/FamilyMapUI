package com.example.devonkinghorn.familymapui;

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
  interface Callback<V> {
    V run(String str);
  }
  public static void post(/*URL url, JSONObject body, String authorization, */Callback callback){
    new Thread(() -> {
      callback.run("");
//      try {
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setDoOutput(true);
//        connection.setChunkedStreamingMode(0);
//        connection.setRequestProperty ("Authorization", authorization);
//        String jsonOutput = body.toString();
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
    });
  }
}
