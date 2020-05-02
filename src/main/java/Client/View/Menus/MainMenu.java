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

    @Override
    public void execute() {
        super.execute();
        System.out.println("invalid command");
        this.show();
        this.execute();
    }

    private MainMenu() {
        super(null, "Main");
        addSubMenu(new AllProductsMenu(this));
        addSubMenu(new OffsMenu(this));


    }
    public void removeUserSection(){
        this.subMenus.remove(subMenus.size()-1);
    }


}