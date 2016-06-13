package com.example.devonkinghorn.familymapui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.devonkinghorn.familymapui.container.UserInfo;
import com.google.android.gms.maps.GoogleMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

/**
 * Created by devonkinghorn on 6/8/16.
 */
public class LoginActivity extends AppCompatActivity {
  private EditText username;
  private EditText password;
  private EditText server;
  private EditText port;
  private Button submit;
  private GoogleMap myMap;
//  DownloadTask task;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login_layout);//setContentView(R.layout.map);
    username = (EditText) findViewById(R.id.username);
    password = (EditText) findViewById(R.id.password);
    server = (EditText) findViewById(R.id.server);
    port = (EditText) findViewById(R.id.port);

    submit = (Button) findViewById(R.id.submit);
    submit.setOnClickListener((View v)->{
          submitButtonClicked();
    });
    makeToast("welcome");
    resetViews();


    port.setText("8080");
    server.setText("192.168.1.5");
    username.setText("d");
    password.setText("k");
    JSONObject body = new JSONObject();

    try {
      UserInfo.port = port.getText().toString();
      UserInfo.url = server.getText().toString();
      body.put("username", this.username.getText().toString());
      body.put("password", this.password.getText().toString());
      String urlString = "http://"+
              this.server.getText().toString() +
              ":" +
              this.port.getText().toString() +
              "/user/login";
      URL url = new URL(urlString);
      new HttpWithCallback().post(url, body, UserInfo.authorization, (String str) -> {
        System.out.println(str);
        try {
          loggedIn(new JSONObject(str));
        } catch (JSONException e) {
          makeToast("unsuccessful");
          e.printStackTrace();
        }
      });
//      task = new DownloadTask();
//      task.execute(new Pair<URL,JSONObject>(url,body));
//      HttpClient client = new HttpClient();
//      client.post(url,body);
    }catch (Exception e){
      //todo: show error toast
      e.printStackTrace();
      makeToast("error Loging in");
    }
  }

  private void resetViews(){

  }

  //TODO: do this
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch(item.getItemId()){
//      case R.id.menu_up_button:
//        Intent intent = new Intent(getActivity(),MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//        getActivity().finish();
//        return true;
//
//      case android.R.id.home:
//        getActivity().finish();
//        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private void submitButtonClicked(){
//    Intent intent = new Intent(this, HttpClient.class);
//    startActivityForResult(intent, REQ_CODE_ORDER_INFO);
//    String username = this.username.getText().toString();
//    String password =

    JSONObject body = new JSONObject();

    try {
      UserInfo.port = port.getText().toString();
      UserInfo.url = server.getText().toString();
      body.put("username", this.username.getText().toString());
      body.put("password", this.password.getText().toString());
      String urlString = "http://"+
              this.server.getText().toString() +
              ":" +
              this.port.getText().toString() +
              "/user/login";
      URL url = new URL(urlString);
      new HttpWithCallback().post(url, body, UserInfo.authorization, (String str) -> {
        System.out.println(str);
        try {
          loggedIn(new JSONObject(str));
        } catch (JSONException e) {
          makeToast("unsuccessful");
          e.printStackTrace();
        }
      });
//      task = new DownloadTask();
//      task.execute(new Pair<URL,JSONObject>(url,body));
//      HttpClient client = new HttpClient();
//      client.post(url,body);
    }catch (Exception e){
      //todo: show error toast
      e.printStackTrace();
      makeToast("error Loging in");
    }
    System.out.println(username);
  }
  public void makeToast(String message){
    System.out.println("printingToast "+message);
    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
  }
  public void receivedUserInfo(JSONObject json){
    System.out.println(json.toString());
    if(json.has("message")){

      try {
        makeToast(json.getString("message"));
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    else if(json.has("firstName")){
      String message = "welcome ";
      try {
        message += json.getString("firstName") +
                " " + json.getString("lastName");
      } catch (JSONException e) {
        e.printStackTrace();
      }
      makeToast(message);

      finish();
    }else {
      makeToast("error Logging in");
    }
  }
  public void loggedIn(JSONObject json){
    System.out.println(json.toString());
    if(json.has("message")){

      try {
        makeToast(json.getString("message"));
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    else if(json.has("userName")){
      try{
        UserInfo.authorization = json.getString("Authorization");
        UserInfo.userName = json.getString("userName");
        UserInfo.personId = json.getString("personId");
      }catch (Exception e){
        e.printStackTrace();
      }
      try {
        String urlString = "http://"+
                this.server.getText().toString() +
                ":" +
                this.port.getText().toString() +
                "/person/" +
                json.getString("personId");
        URL url = new URL(urlString);
        new HttpWithCallback().post(url, json, UserInfo.authorization, (String str) -> {
          System.out.println(str);
          try {
            receivedUserInfo(new JSONObject(str));
          } catch (JSONException e) {
            e.printStackTrace();
            makeToast("fail");
          }
        });
//        GetUserInfo task = new GetUserInfo();
//        task.execute(new Pair<URL,String>(url,json.getString("Authorization")));
      } catch (Exception e) {
        e.printStackTrace();
      }
//      makeToast(message);
    }else {
      makeToast("error Logging in");
    }
  }

  /**
   *
   */
//  protected class GetUserInfo extends AsyncTask<Pair<URL,String>,Integer,JSONObject> {
//    @Override
//    protected JSONObject doInBackground(Pair<URL,String>... urls){
//      HttpClient client = new HttpClient();
//      long totalSize = 0;
////      String response = client.post(urls[0].first,urls[0].second);
//      JSONObject response = new JSONObject();
//      try {
//        String str = client.post(urls[0].first,new JSONObject(),urls[0].second);
//        if(str != null){
//          return new JSONObject(str);
//        }
//
////        loggedIn(response);
//
//      } catch (JSONException e) {
//        makeToast("error Loging in");
//        e.printStackTrace();
//      }
//      System.out.println(response.toString());
//      JSONObject toReturn = new JSONObject();
//      return toReturn;
//    }
//    @Override
//    protected void onPostExecute(JSONObject response){
//      receivedUserInfo(response);
//    }
//  }
//  public class DownloadTask extends AsyncTask<Pair<URL,JSONObject>,Integer,JSONObject>{
//    @Override
//    protected JSONObject doInBackground(Pair<URL,JSONObject>... urls){
//      HttpClient client = new HttpClient();
//      long totalSize = 0;
////      String response = client.post(urls[0].first,urls[0].second);
//      JSONObject response = new JSONObject();
//      try {
//        String str = client.post(urls[0].first,urls[0].second,"");
//        if(str != null){
//          return new JSONObject(str);
//        }
//
////        loggedIn(response);
//
//      } catch (JSONException e) {
//        makeToast("error Loging in");
//        e.printStackTrace();
//      }
//      System.out.println(response.toString());
//      JSONObject toReturn = new JSONObject();
//      return toReturn;
//    }
//    @Override
//    protected void onPostExecute(JSONObject response){
//      loggedIn(response);
//    }
//    @Override
//    protected void onProgressUpdate(Integer... progress){
//
//    }
//    private void publishProgress(int progress){
//
//    }
//
//  }


}
