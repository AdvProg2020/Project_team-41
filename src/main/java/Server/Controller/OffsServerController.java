package Server.Controller;


import Client.Models.Product;

import java.util.ArrayList;

public class OffsServerController {
    private static OffsServerController single_instance = null;
    public static OffsServerController getInstance()
    {
        if (single_instance == null)
            single_instance = new OffsServerController();

        return single_instance;
    }
    private OffsServerController(){
    }
    public ArrayList<Product> getOffs(){

    }


}
