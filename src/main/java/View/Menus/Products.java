package View.Menus;

public class Products extends Menu {
    public Products(Menu superMenu) {
        super(superMenu, "Products");
        addSubMenu(new Product(this));
    }

    //todo getter for unnamed subclasses
}
