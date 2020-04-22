package Client.Controller.UserSectionController;

import Client.Models.Person.Manager;
import Client.Models.Person.Person;

import java.util.ArrayList;
import java.util.HashMap;

public class ManagerController extends UserSectionController{
    private static ManagerController single_instance = null;
    public static ManagerController getInstance()
    {
        if (single_instance == null)
            single_instance = new ManagerController();

        return single_instance;
    }
    private ManagerController(){
    }

    public Person viewUser(String username){
        System.err.println("fail");
        return new Manager();
    }
    public boolean deleteUser(String username){
        //returns boolean for error handling
        System.err.println("fail");
        return true;
    }
    public void createManagerProfile(ArrayList<String> userInfo){
        //returns String for error handling

    }
    public boolean removeProduct(String productId){
        //returns boolean for error handling
        System.err.println("fail");
        return true;
    }
    public void createDiscountCode(ArrayList<String> codeInformation){

    }
    public ArrayList<String> viewDiscountCode(String code){
        System.err.println("fail");
        return new ArrayList<>();
    }
    public void  editDiscountCode(String code,HashMap<String,String> edits){
        //returns String for error handling
    }
    public boolean removeDiscountCode(String code){
        System.err.println("fail");
        return true;
    }
    public ArrayList<String> showRequest(){
        System.err.println("fail");
        return new ArrayList<>();
    }
    public ArrayList<String> getRequestDetails(String request){
        System.err.println("fail");
        return new ArrayList<>();
    }
    public boolean acceptRequest(String request){
        System.err.println("fail");
        return true;
    }
    public boolean declineRequest(String request){
        System.err.println("fail");
        return true;
    }
    public ArrayList<String> showCategories(){
        System.err.println("fail");
        return new ArrayList<>();
    }
    public boolean editCategory(String category,HashMap<String,String> edits){
        System.err.println("fail");
        return true;
    }
    public boolean addCategory(String categoryName,ArrayList<String> categoryInformation){
        System.err.println("fail");
        return true;
    }
    public boolean removeCategory(String categoryName){
        System.err.println("fail");
        return true;
    }


}
