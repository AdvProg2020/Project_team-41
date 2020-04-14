package Client.Controller.UserSectionController;

import Client.Models.Person.Person;

import java.util.ArrayList;

public class ManagerController extends UserSectionController{
    public Person viewUser(String username){

    }
    public boolean deleteUser(String username){
        //returns boolean for error handling
    }
    public String createManagerProfile(ArrayList<String> userInfo){
        //returns String for error handling
    }
    public boolean removeProduct(String productId){
        //returns boolean for error handling
    }
    public void createDiscountCode(ArrayList<String> codeInformation){

    }
    public ArrayList<String> viewDiscountCode(String code){

    }
    public String editDiscountCode(String command,String code){
        //returns String for error handling
    }
    public boolean removeDiscountCode(String code){

    }
    public ArrayList<String> showRequest(){

    }
    public ArrayList<String> getRequestDetails(String request){

    }
    public boolean acceptRequest(String request){

    }
    public boolean declineRequest(String request){

    }
    public ArrayList<String> showCategories(){

    }
    public boolean editCategory(String category,String command){

    }
    public boolean addCategory(String categoryName,ArrayList<String> categoryInformation){

    }
    public boolean removeCategory(String categoryName){

    }


}
