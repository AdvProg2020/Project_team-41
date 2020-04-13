package View.Menus;

public class Products extends Menu {
    public Products(Menu superMenu) {
        super(superMenu, "Products");
        addSubMenu(new Product(this));
        addSubMenu(new Filter(this));
        addSubMenu(new Sort(this));
        addSubMenu(addViewCategory());
        addSubMenu(addShowProducts());
    }

    //todo getter for unnamed subclasses

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }
    private Menu addViewCategory() {
        return new Menu(this, "ViewCategory") {
            @Override
            public void show() {
                //todo
            }

            @Override
            public void execute() {
                //todo
            }
        };
    }
    private Menu addShowProducts(){
        return new Menu(this,"ShowProducts") {
            @Override
            public void show() {
                //todo
            }

            @Override
            public void execute() {
                //todo
            }
        };
    }


}
