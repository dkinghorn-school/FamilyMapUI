package com.example.devonkinghorn.familymapui;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by devonkinghorn on 6/1/16.
 */
public class HttpClient {
  public String post(URL url, JSONObject body){
    try {
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//      connection.setRequestMethod("POST");
      connection.setDoOutput(true);
      connection.setChunkedStreamingMode(0);
      String jsonOutput = body.toString();
      OutputStream os = connection.getOutputStream();
      OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
      osw.write(jsonOutput);
      osw.flush();
      osw.close();
//      InputStream in = new BufferedInputStream(connection.getInputStream());
//      StringBuilder str = new StringBuilder();
      if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
        InputStream responseBody = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody, "UTF-8"));
        return reader.readLine();
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }
  public String getUrl(URL url){
    try{
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.connect();

//      OutputStream requestBody = connection.getOutputStream();
//      requestBody.write(postData.getBytes);
//      requestBody.close();

      if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
        InputStream responseBody = connection.getInputStream();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while((length = responseBody.read(buffer)) != -1){
          baos.write(buffer,0,length);
        }
        String responseBodyData = baos.toString();
        return responseBodyData;
      }
    } catch (Exception e){
      e.printStackTrace();
    }
    return "";
  }
}
