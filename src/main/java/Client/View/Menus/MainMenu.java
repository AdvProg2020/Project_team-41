package Client.View.Menus;

public class MainMenu extends Menu {

    public MainMenu() {
        super(null, "Main");
        //todo if for subMenu logged in
        addSubMenu(new AllProductsMenu(this));
        addSubMenu(new OffsMenu(this));


    }

}
