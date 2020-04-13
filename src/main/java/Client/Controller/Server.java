package Client.Controller;

import Client.Models.Category;
import Client.Models.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    private ArrayList<Product> filterProducts = new ArrayList<>();

    private static Server single_instance = null;
    public static Server getInstance()
    {
        if (single_instance == null)
            single_instance = new Server();

        return single_instance;
    }
    private Category filterCategory;
    private HashMap<String ,String> filterFeature;
    private ArrayList<Product> cart;

    public void setFilterCategory(Category filterCategory) {
        this.filterCategory = filterCategory;
    }

    public void setFilterFeature(HashMap<String, String> filterFeature) {
        this.filterFeature = filterFeature;
    }

    public Category getFilterCategory() {
        return filterCategory;
    }

    public HashMap<String, String> getFilterFeature() {
        return filterFeature;
    }
    public int create(String type,String user,String pass){
        //return -1 invalid User
        //return 1 correct
        //return 0 error type
    }
    public boolean login(String user,String pass){

    }
    public void logout(){

    }




}
