package com.example.devonkinghorn.familymapui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.devonkinghorn.familymapui.container.Callback;
import com.example.devonkinghorn.familymapui.container.LifeEvent;
import com.example.devonkinghorn.familymapui.container.Model;
import com.example.devonkinghorn.familymapui.container.Person;
import com.example.devonkinghorn.familymapui.R;

/**
 * Created by devonkinghorn on 6/9/16.
 */
public class PersonFragment extends Fragment implements View.OnClickListener{
  String eventId;
  String personId;
  LifeEvent lifeEvent;
  Person person;
  TextView topText;
  TextView bottomText;
  ImageView image;
  Callback<String> callback;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View myInflatedView = inflater.inflate(R.layout.person_fragment, container, false);
    // Inflate the layout for this fragment
    Bundle args = getArguments();
    eventId = args.getString("eventId");
    personId = args.getString("personId");
    lifeEvent = Model.getEvent(eventId);
    person = Model.getPerson(personId);


    System.out.println(args.toString());

    topText = (TextView) myInflatedView.findViewById(R.id.topText);
    bottomText = (TextView) myInflatedView.findViewById(R.id.bottomText);
    image = (ImageView) myInflatedView.findViewById(R.id.imageView);
    setFields();
    myInflatedView.setOnClickListener(this);
    return myInflatedView;
  }

  private void setFields(){
    if(person != null){

    } else if(lifeEvent != null){
      Person person = Model.getPerson(lifeEvent.getPersonId());
      topText.setText(person.firstName + " " + person.lastName);
      bottomText.setText(lifeEvent.getDescription() +
              ": " + lifeEvent.getCity() +
              ", " + lifeEvent.getCountry() +
              " (" + lifeEvent.getYear() + ")");
      if(person.gender.equals("m")){
        image.setImageResource(R.drawable.human_male);
      } else {
        image.setImageResource(R.drawable.ic_human_female_black_36dp);
      }
    } else {
      topText.setText("Click on a marker");
      bottomText.setText("to see event details");
      image.setImageResource(android.R.drawable.sym_def_app_icon);
    }
  }

  @Override
  public void onClick(View v){
    if(callback != null) {
      if (eventId != null) {
        callback.run(eventId);
      } else if (personId != null) {
        callback.run(personId);
      }
    }
  }

  /**
   *
   * @param cb this is the callback form what to do when the fragment is clicked on
   *           and cb is a common name for a callback in languages so don't dock points
   *
   *           returns either the eventId or PersonId or does nothing
   */
  public void onClickCallback(Callback<String> cb){
    callback = cb;
  }
}
