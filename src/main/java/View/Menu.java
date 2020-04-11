package View;

import java.util.ArrayList;

public abstract class Menu {
    private Menu superMenu;
    private ArrayList<Menu> subMenus;
    private String name;

    public Menu(Menu superMenu, String name) {
        this.superMenu = superMenu;
        this.name = name;
    }

}
