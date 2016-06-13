package com.example.devonkinghorn.familymapui.container;

import org.json.JSONObject;

/**
 * Created by devonkinghorn on 6/8/16.
 */
public class Person {
  public String personId;
  public String firstName;
  public String lastName;
  public String gender;
  public String father;
  public String mother;
  public String spouse;

  //  {  person JSON
//    "descendant":"d",
//          "personID":"491zb6l6-mz82-kton-6asv-nphmjlu81ge6",
//          "firstName":"Ezra",
//          "lastName":"Carmouche",
//          "gender":"m",
//          "spouse":"3bxo60x3-60kp-397x-p248-48h6it34o488"
//          "father":"491zb6l6-mz82-kton-6asv-nphmjlu81ge6",
//          "mother":"3bxo60x3-60kp-397x-p248-48h6it34o488",
//  },
  public Person(JSONObject json){
    try{
      personId = json.getString("personID");
      firstName = json.getString("firstName");
      lastName = json.getString("lastName");
      gender = json.getString("gender");
      if(json.has("spouse"))
        spouse = json.getString("spouse");
      if(json.has("father")){
        father = json.getString("father");
        mother = json.getString("mother");
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Person)) return false;

    Person person = (Person) o;

    if (!personId.equals(person.personId)) return false;
    if (!firstName.equals(person.firstName)) return false;
    if (!lastName.equals(person.lastName)) return false;
    if (!gender.equals(person.gender)) return false;
    if (father != null ? !father.equals(person.father) : person.father != null) return false;
    if (mother != null ? !mother.equals(person.mother) : person.mother != null) return false;
    return spouse.equals(person.spouse);

  }

  @Override
  public int hashCode() {
    return personId.hashCode();
  }
}
