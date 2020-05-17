import Client.Controller.EndProgram;
import Client.Controller.StartProgram;
import Client.Models.Category;
import Client.Models.Off;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;
import Client.Models.Product;
import Client.Models.SpecialFeature;
import Client.View.Menus.MainMenu;
import Server.Database;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        try {
            StartProgram.startProgram();
            MainMenu.getInstance().show();
            MainMenu.getInstance().execute();
        } catch (Exception e) {
            e.printStackTrace();
            EndProgram.endProgram();
        }
    }


}
//todo: check every over ride to ensure super is called first