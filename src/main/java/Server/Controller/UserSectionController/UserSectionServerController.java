package Server.Controller.UserSectionController;

import Client.Models.Person.Person;

import java.util.ArrayList;

public abstract class UserSectionServerController {
    public static ArrayList<String> getPersonalInfo(Person person){
        if(person == null)
            throw new NullPointerException();
        //todo return ArrayList of personal info
        ArrayList<String> personalInfo = new ArrayList<>();
        personalInfo.add("UserName: " + person.getUserName());
        personalInfo.add("FirstName: " + person.getFirstName());
        personalInfo.add("LastName: " + person.getLastName());
        personalInfo.add("Credit: " + person.getCredit());
        personalInfo.add("Email: " + person.getEmail());
        personalInfo.add("PhoneNumber: " + person.getPhoneNumber());
        //personalInfo.addAll(loggedInPerson.getDiscountCodes());
        //optional to add show discount code or show trade logs here
        //todo check if needed ot print discountCodes
        //todo check if it needs tradeLogs


        return personalInfo;

    }
    public static void edit(Person person,String field,String editedField) throws Exception {
        if(field.equals("username"))
            throw new Exception("can't change username");
        if(person == null)
            throw new NullPointerException("There is no one logged in");
        switch (field.toLowerCase()){
            case "password":{
                String[] oldNewPasswords = editedField.split(",");
                if(oldNewPasswords.length < 2)
                    throw new Exception("enter passwords like i said");
                if(oldNewPasswords[0].equals(person.getPassword())) {
                    if(editedField.split(",").length != 2)
                        throw new Exception("enter like i said");
                    person.setPassword(editedField.split(",")[1]);
                }
                else
                    throw new Exception("wrong password");

                break;
            }
            case "firstname":{
                person.setFirstName(editedField);
                break;
            }
            case "lastname":{
                person.setLastName(editedField);
                break;
            }
            case "email":{
                person.setEmail(editedField);
                break;
            }
            case "phonenumber":{
                person.setPhoneNumber(editedField);
                break;
            }
            default:{
                throw new Exception("there is no field with this name!");
            }
        }

    }

}
