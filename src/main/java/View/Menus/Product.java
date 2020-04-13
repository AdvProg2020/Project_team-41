package View.Menus;

public class Product extends Menu {
    private int id;
    public Product(Menu superMenu) {
        super(superMenu, "Product");
        addSubMenu(addDigest());
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public Menu addDigest(){
        return new Menu(this,"Digest") {
            @Override
            public void show() {
                super.show();
            }

            @Override
            public void execute() {
                super.execute();
            }
        };
    }
}
