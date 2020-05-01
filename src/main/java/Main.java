import Client.Controller.StartProgram;
import Client.Models.Category;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;
import Client.Models.Product;
import Client.Models.SpecialFeature;
import Client.View.Menus.MainMenu;
import Server.Database;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args)  {
        StartProgram.startProgram();

        MainMenu.getInstance().show();
        MainMenu.getInstance().execute();

    }
}
