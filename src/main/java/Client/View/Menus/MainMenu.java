package Client.View.Menus;

import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Person.Buyer;
import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;
import Client.View.Menus.UserSectionMenus.BuyerAccount.BuyerSection;
import Client.View.Menus.UserSectionMenus.ManagerSection;
import Client.View.Menus.UserSectionMenus.SellerSection;

public class MainMenu extends Menu {

    public MainMenu() {
        super(null, "Main");
        Person person=UserSectionController.getLoggedInPerson();
        if (person != null) {
            this.addSubMenu(new RegisterLoginMenu(this, "Logout"));
            if(person instanceof Manager){
                this.addSubMenu(new ManagerSection(this));
            }else if(person instanceof Seller){
                this.addSubMenu(new SellerSection(this));
            }else if(person instanceof Buyer){
                this.addSubMenu(new BuyerSection(this));
            }

        }else{
            this.addSubMenu(new RegisterLoginMenu(this,"Register or Login"));
        }
        addSubMenu(new AllProductsMenu(this));
        addSubMenu(new OffsMenu(this));


    }


}