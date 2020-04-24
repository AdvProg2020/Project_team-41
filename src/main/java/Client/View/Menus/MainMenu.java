package Client.View.Menus;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Person.Buyer;
import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;
import Client.View.Menus.UserSectionMenus.BuyerAccount.BuyerSection;
import Client.View.Menus.UserSectionMenus.ManagerSection;
import Client.View.Menus.UserSectionMenus.SellerSection;
import Server.Controller.LoginRegisterServerController;

public class MainMenu extends Menu {
    private static MainMenu single_instance = null;
    public static MainMenu getInstance()
    {
        if (single_instance == null)
            single_instance = new MainMenu();

        return single_instance;
    }


    private MainMenu() {
        super(null, "Main");
        addSubMenu(new AllProductsMenu(this));
        addSubMenu(new OffsMenu(this));


    }


}