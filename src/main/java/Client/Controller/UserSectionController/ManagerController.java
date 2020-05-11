package Client.Controller.UserSectionController;

import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Server.Controller.UserSectionController.ManagerServerController;

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

    public Person getUserByUsername(String username) throws Exception {
        return ManagerServerController.getInstance().getUserByUsername(username);
    }
    public void deleteUser(String username) throws Exception {
        ManagerServerController.getInstance().deleteUser(username);
    }
    public void createManagerProfile(ArrayList<String> userInfo){
        ManagerServerController.getInstance().createManagerProfile(userInfo);
    }
    public void removeProduct(String productId){
        ManagerServerController.getInstance().removeProduct(productId);
    }
    public void createDiscountCode(ArrayList<String> codeInformation) throws Exception {
        ManagerServerController.getInstance().createDiscountCode(codeInformation);
    }
    public ArrayList<String> viewDiscountCode(String code) throws Exception {
        return ManagerServerController.getInstance().viewDiscountCode(code);
    }
    public void  editDiscountCode(String code,HashMap<String,String> edits) throws Exception {
        ManagerServerController.getInstance().editDiscountCode(code,edits);
    }
    public void removeDiscountCode(String code){
        ManagerServerController.getInstance().removeDiscountCode(code);
    }
    public ArrayList<String> showRequest(){
        return ManagerServerController.getInstance().showRequest();
    }
    public ArrayList<String> getRequestDetails(String request) throws Exception {
        return ManagerServerController.getInstance().getRequestDetails(request);
    }
    public void acceptRequest(String request) throws Exception {
        ManagerServerController.getInstance().acceptRequest(request);
    }
    public void declineRequest(String request) throws Exception {
        ManagerServerController.getInstance().declineRequest(request);
    }
    public ArrayList<String> showCategories(){
        return ManagerServerController.getInstance().showCategories();
    }
    public void editCategory(String category,String field,String editedField) throws Exception {
        ManagerServerController.getInstance().editCategory(category, field,editedField);
    }
    public void addCategory(String categoryName,String specialFeatures){
        ManagerServerController.getInstance().addCategory(categoryName,specialFeatures);
    }
    public void removeCategory(String categoryName) throws Exception {
        ManagerServerController.getInstance().removeCategory(categoryName);
    }
    public ArrayList<String> viewAllDiscountCodes(){
        return ManagerServerController.getInstance().viewAllDiscountCodes();
    }
}