package View.Menus;

public class Products extends Menu {
    public Products(Menu superMenu) {
        super(superMenu, "Products");
        addSubMenu(new Product(this));
    }
    private void filter(){

    }

    //todo getter for unnamed subclasses
}
