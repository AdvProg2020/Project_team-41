package Server.Controller.UserSectionController;

import Client.Models.Person.Person;

import java.util.ArrayList;
import java.util.HashMap;

public class ManagerServerController extends UserSectionServerController {
    private static ManagerServerController single_instance = null;
    public static ManagerServerController getInstance()
    {
        if (single_instance == null)
            single_instance = new ManagerServerController();

        return single_instance;
    }

    private ManagerServerController(){
    }

    public Person viewUser(String username){

    }
    public void  deleteUser(String username){
        //returns boolean for error handling
    }
    public void createManagerProfile(ArrayList<String> userInfo){
        //returns String for error handling
    }
    public void  removeProduct(String productId){
        //returns boolean for error handling
    }
    public void createDiscountCode(ArrayList<String> codeInformation){

    }
    public ArrayList<String> viewDiscountCode(String code){

    }
    public void  editDiscountCode(HashMap<String,String> edits, String code){
        //returns String for error handling
    }
    public void  removeDiscountCode(String code){

    }
    public ArrayList<String> showRequest(){

    }
    public ArrayList<String> getRequestDetails(String request){

    }
    public void  acceptRequest(String request){

    }
    public void declineRequest(String request){

    }
    public ArrayList<String> showCategories(){

    }
    public void editCategory(String category,String command){

    }
    public void addCategory(String categoryName,ArrayList<String> categoryInformation){

    }
    public void removeCategory(String categoryName){

    }


}
