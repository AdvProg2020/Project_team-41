package Server.Controller;

import Client.Models.Category;
import Client.Models.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class Server {

    private static Server single_instance = null;
    public static Server getInstance()
    {
        if (single_instance == null)
            single_instance = new Server();

        return single_instance;
    }



}
