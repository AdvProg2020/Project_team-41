package Client.View.Menus.UserSectionMenus;

import Client.Models.Person.ViewPersonalInfo;
import Client.View.Menus.Menu;

public abstract class UserSection extends Menu {

    public UserSection(Menu superMenu, String name) {
        super(superMenu, name);
        addSubMenu(new ViewPersonalInfo(this));
    }

    @Override
    public void execute() {
        super.execute();
    }
}
