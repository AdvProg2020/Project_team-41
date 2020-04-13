package View.Menus;

public class Products extends Menu {
    public Products(Menu superMenu) {
        super(superMenu, "Products");
        addSubMenu(new Product(this));
        addSubMenu(new Filter(this));
        addSubMenu(new Sort(this));
    }

    //todo getter for unnamed subclasses
    //todo show and execute override
}
