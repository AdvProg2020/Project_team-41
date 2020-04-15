package Client.Controller;


import Client.Models.Product;

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
    public ArrayList<Product> getOffs(){

    }
    public Product getProductId(String id){

    }

}
