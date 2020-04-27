package Client.Controller;


import Client.Models.Off;
import Client.Models.Product;
import Server.Controller.OffsServerController;

import java.util.ArrayList;

public class OffsController {
    private static OffsController single_instance = null;
    public static OffsController getInstance()
    {
        if (single_instance == null)
            single_instance = new OffsController();

        return single_instance;
    }
    private OffsController(){
    }
    public ArrayList<Off> getOffs(){

        return  OffsServerController.getInstance().getOffs();
    }
    public Product getProductId(String id){
        System.err.println("fail");
        return new Product();
    }

}
