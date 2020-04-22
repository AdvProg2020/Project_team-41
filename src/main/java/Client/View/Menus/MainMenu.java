package Client.View.Menus;

import Client.Controller.UserSectionController.UserSectionController;

public class MainMenu extends Menu {

    public MainMenu() {
        super(null, "Main");

        if (UserSectionController.getLoggedInPerson() != null) {
            this.addSubMenu(new RegisterLoginMenu(this, "Logout"));

        }else{
            this.addSubMenu(new RegisterLoginMenu(this,"Register or Login"));
        }
        addSubMenu(new AllProductsMenu(this));
        addSubMenu(new OffsMenu(this));


    }

}
