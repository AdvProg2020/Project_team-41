package View.Menus.UserSectionMenus;

import Controller.UserSectionController;
import Models.Person.ViewPersonalInfo;
import View.Menus.Menu;

import java.util.ArrayList;

public abstract class UserSection extends Menu {

    public UserSection(Menu superMenu, String name) {
        super(superMenu, name);
        addSubMenu(new ViewPersonalInfo(this));
    }




}
