package Server.Controller;


import Client.Models.Off;
import Client.Models.Product;
import Server.Database;

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
    public ArrayList<Off> getOffs(){
        return Database.getInstance().getAllOffs();
    }
    public ArrayList<Product> getAllOffProducts(){
        return Database.getInstance().getAllOffProducts();
    }
    public void deleteOffs(ArrayList<Off>offs){
        Database.getInstance().deleteOutOfDateOffs(offs);
        for (Off off : offs) {
            off.deleteOff();
        }
    }


}
