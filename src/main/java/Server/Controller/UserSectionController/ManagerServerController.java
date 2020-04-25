package Server.Controller.UserSectionController;

import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Server.Database;

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

    public Person getUserByUsername(String username){
        for (Person user : Database.getAllUsers()) {
            if(user.getUserName().equals(username)){
                return user;
            }
        }
        return null;
    }
    public void  deleteUser(String username){
    }
    public void createManagerProfile(ArrayList<String> userInfo){
    }
    public void  removeProduct(String productId){
    }
    public void createDiscountCode(ArrayList<String> codeInformation){

    }
    public ArrayList<String> viewDiscountCode(String code){
        System.err.println("fail");
        return new ArrayList<>();
    }
    public void  editDiscountCode(String code,HashMap<String,String> edits) {
    }
    public void  removeDiscountCode(String code){

    }
    public ArrayList<String> showRequest(){
        System.err.println("fail");
        return new ArrayList<>();
    }
    public ArrayList<String> getRequestDetails(String request){
        System.err.println("fail");
        return new ArrayList<>();
    }
    public void  acceptRequest(String request){

    }
    public void declineRequest(String request){

    }
    public ArrayList<String> showCategories(){
        System.err.println("fail");
        return new ArrayList<>();
    }
    public void editCategory(String category,String field,String editedField){

    }
    public void addCategory(String categoryName,ArrayList<String> categoryInformation){

    }
    public void removeCategory(String categoryName){

    }


}
