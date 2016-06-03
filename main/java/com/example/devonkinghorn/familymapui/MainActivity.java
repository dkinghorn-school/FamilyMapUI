package com.example.devonkinghorn.familymapui;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
  private EditText username;
  private EditText password;
  private EditText server;
  private EditText port;
  private Button submit;
  DownloadTask task;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login_layout);
    username = (EditText) findViewById(R.id.username);
    password = (EditText) findViewById(R.id.password);
    server = (EditText) findViewById(R.id.server);
    port = (EditText) findViewById(R.id.port);

    submit = (Button) findViewById(R.id.submit);
    submit.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v){
        submitButtonClicked();
      }
    });
    makeToast("welcome");
    resetViews();
  }

  private void resetViews(){

  }

  private void submitButtonClicked(){
//    Intent intent = new Intent(this, HttpClient.class);
//    startActivityForResult(intent, REQ_CODE_ORDER_INFO);
//    String username = this.username.getText().toString();
//    String password =

    JSONObject body = new JSONObject();

    try {
      body.put("username", this.username.getText().toString());
      body.put("password", this.password.getText().toString());
      String urlString = "http://"+
              this.server.getText().toString() +
              ":" +
              this.port.getText().toString() +
              "/user/login";
      URL url = new URL(urlString);
      task = new DownloadTask();
      task.execute(new Pair<URL,JSONObject>(url,body));
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
      String message = "welcome ";
      try {
        message += json.getString("userName");
      } catch (JSONException e) {
        e.printStackTrace();
      }
      makeToast(message);
    }else {
      makeToast("error Logging in");
    }
  }
  public class DownloadTask extends AsyncTask<Pair<URL,JSONObject>,Integer,JSONObject>{
    @Override
    protected JSONObject doInBackground(Pair<URL,JSONObject>... urls){
      HttpClient client = new HttpClient();
      long totalSize = 0;
//      String response = client.post(urls[0].first,urls[0].second);
      JSONObject response = new JSONObject();
      try {
        String str = client.post(urls[0].first,urls[0].second);
        if(str != null){
          return new JSONObject(str);
        }

//        loggedIn(response);

      } catch (JSONException e) {
        makeToast("error Loging in");
        e.printStackTrace();
      }
      System.out.println(response.toString());
      JSONObject toReturn = new JSONObject();
      return toReturn;
    }
    @Override
    protected void onPostExecute(JSONObject response){
      loggedIn(response);
    }
    @Override
    protected void onProgressUpdate(Integer... progress){

    }
    private void publishProgress(int progress){

    }

  }


}
