package Client.View.Menus;

public class MainMenu extends Menu {

    public MainMenu() {
        super(null, "Main");
        //todo if for subMenu logged in
        addSubMenu(new AllProducts(this));
        addSubMenu(new Offs(this));


    }

}
singleton
allproduct
