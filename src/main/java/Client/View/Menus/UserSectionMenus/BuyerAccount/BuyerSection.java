package Client.View.Menus.UserSectionMenus.BuyerAccount;

import Client.Models.Person.ViewPersonalInfo;
import Client.View.Menus.Menu;
import Client.View.Menus.UserSectionMenus.UserSection;

public class BuyerSection extends UserSection {
    public BuyerSection(Menu superMenu, String name) {
        super(superMenu, name);
        this.addSubMenu(new ViewPersonalInfo(this));
        this.addSubMenu(new ViewCart(this));
    }

    private Menu viewCart(){
            return new Menu(this , "view cart") {
                @Override
                public void show() {

                }

                @Override
                public void execute() {

                }
            };
    }

}
